package com.parking.service.impl;

import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.entity.ParkingLot;
import com.parking.service.ParkingLotSearchService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ParkingLotSearchServiceImpl implements ParkingLotSearchService {

    private MultiLayerNetwork neuralNetwork;
    private List<ParkingLot> parkingLotDataList;

    public void setParkingLotSearchServiceImpl(List<ParkingLot> parkingLotDataList) {
        this.parkingLotDataList = parkingLotDataList;
        setNeuralNetwork();
    }

    public void setNeuralNetwork() {
        // Khởi tạo và cấu hình mạng neural
        int numInputFeatures = 6; // Số lượng đặc trưng đầu vào (latitude, longitude, area, reviewPoint, hasRoof, cleanLevel)
        int numOutputClasses = parkingLotDataList.size();

        MultiLayerConfiguration config = new NeuralNetConfiguration.Builder()
                .updater(new Adam(0.01))
                .list()
                .layer(new DenseLayer.Builder()
                        .nIn(numInputFeatures)
                        .nOut(64)
                        .activation(Activation.RELU)
                        .build())
                .layer(new OutputLayer.Builder()
                        .nIn(64)
                        .nOut(numOutputClasses)
                        .activation(Activation.IDENTITY)
                        .lossFunction(LossFunctions.LossFunction.MEAN_SQUARED_LOGARITHMIC_ERROR)
                        .build())
                .build();

        neuralNetwork = new MultiLayerNetwork(config);
        neuralNetwork.init();
    }

    @Override
    public int findNearestParkingLot(double latitude, double longitude) {
        double minDistance = Double.MAX_VALUE;
        int nearestParkingLotId = -1;

        for (int i = 0; i < parkingLotDataList.size(); i++) {
            ParkingLot parkingLot = parkingLotDataList.get(i);
            double parkingLotLat = parkingLot.getLat();
            double parkingLotLng = parkingLot.getLng();

            // Tính khoảng cách giữa điểm hiện tại và bãi đỗ xe trong danh sách
            double distance = calculateDistance(latitude, longitude, parkingLotLat, parkingLotLng);

            // Nếu khoảng cách nhỏ hơn khoảng cách nhỏ nhất hiện tại, cập nhật giá trị
            if (distance < minDistance) {
                minDistance = distance;
                nearestParkingLotId = i;
            }
        }

        return nearestParkingLotId;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Đổi độ sang radian
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Tính độ chênh lệch giữa các vị trí
        double latDiff = lat2Rad - lat1Rad;
        double lonDiff = lon2Rad - lon1Rad;

        // Áp dụng công thức Haversine
        double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2) +
                   Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                   Math.sin(lonDiff / 2) * Math.sin(lonDiff / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Tính khoảng cách
        double earthRadius = 6371000; // Đường kính trái đất, đơn vị mét
        double distance = earthRadius * c;

        return distance;
    }


    @Override
    public List<Integer> suggestParkingLots(double latitude, double longitude) {
        int nearestParkingLotId = findNearestParkingLot(latitude, longitude);
        ParkingLot nearestParkingLot = parkingLotDataList.get(nearestParkingLotId);

        double[] features = {
                nearestParkingLot.getLat(),
                nearestParkingLot.getLng(),
                nearestParkingLot.getArea(),
                nearestParkingLot.getReviewPoint(),
                nearestParkingLot.getHasRoof() ,
                nearestParkingLot.getCleanLevel()
        };

        INDArray inputArray = Nd4j.create(features).reshape(1, -1);
        INDArray predictions = neuralNetwork.output(inputArray, false);
        double[] predictionsArray = predictions.toDoubleVector();

        List<ParkingLotWithScore> parkingLotsWithScores = new ArrayList<>();
        for (int i = 0; i < predictionsArray.length; i++) {
            parkingLotsWithScores.add(new ParkingLotWithScore(i, predictionsArray[i]));
        }

        // Sắp xếp danh sách bãi đỗ xe theo điểm số giảm dần
        Collections.sort(parkingLotsWithScores, Collections.reverseOrder());

        // Lấy ra 5 bãi đỗ xe tốt nhất
        List<Integer> recommendedParkingLotIds = new ArrayList<>();
        for (int i = 0; i < Math.min(5, parkingLotsWithScores.size()); i++) {
            recommendedParkingLotIds.add(parkingLotsWithScores.get(i).getId());
        }

        return recommendedParkingLotIds;
    }

    private static class ParkingLotWithScore implements Comparable<ParkingLotWithScore> {
        private final int id;
        private final double score;

        public ParkingLotWithScore(int id, double score) {
            this.id = id;
            this.score = score;
        }

        public int getId() {
            return id;
        }

        public double getScore() {
            return score;
        }

        @Override
        public int compareTo(ParkingLotWithScore other) {
            return Double.compare(this.score, other.score);
        }
    }
    
//    private List<double[]> loadDataset(String csvFilePath) {
//        List<double[]> dataset = new ArrayList<>();
//
//        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
//            String[] nextLine;
//            while ((nextLine = reader.readNext()) != null) {
//                double[] features = new double[nextLine.length - 1];
//                for (int i = 0; i < features.length; i++) {
//                    features[i] = Double.parseDouble(nextLine[i]);
//                }
//                dataset.add(features);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return dataset;
//    }
//}
}
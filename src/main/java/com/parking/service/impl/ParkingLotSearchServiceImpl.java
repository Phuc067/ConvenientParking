import io.deepforest.DeepForest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.service.ParkingLotSearchService;

import java.util.List;

@Service
public class ParkingLotSearchServiceImpl implements ParkingLotSearchService {

    private final DeepForest deepForest;
    private final List<ParkingLot> parkingLotDataList;

    @Autowired
    public ParkingLotService(DeepForest deepForest, List<ParkingLot> parkingLotDataList) {
        this.deepForest = deepForest;
        this.parkingLotDataList = parkingLotDataList;
    }

    @Override
    public int suggestParkingLot(double latitude, double longitude, double customerRating, boolean hasRoof, double cleanliness, double area) {
        int nearestParkingLotId = findNearestParkingLot(latitude, longitude);
        double[] features = {latitude, longitude, customerRating, hasRoof ? 1 : 0, cleanliness, area};
        double[] predictions = deepForest.predict(features);
        int recommendedParkingLotId = selectParkingLotWithHighestScore(nearestParkingLotId, predictions);
        return recommendedParkingLotId;
    }

    private int findNearestParkingLot(double latitude, double longitude) {
        double minDistance = Double.MAX_VALUE;
        int nearestParkingLotId = -1;
        for (ParkingLotData parkingLot : parkingLotDataList) {
            double distance = calculateDistance(latitude, longitude, parkingLot.getLatitude(), parkingLot.getLongitude());
            if (distance < minDistance) {
                minDistance = distance;
                nearestParkingLotId = parkingLot.getId();
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
        double distance = EARTH_RADIUS * c;

        return distance;
    }

    private int selectParkingLotWithHighestScore(int nearestParkingLotId, double[] predictions) {
        int recommendedParkingLotId = nearestParkingLotId;
        double highestScore = predictions[nearestParkingLotId];
        for (int i = 0; i < predictions.length; i++) {
            if (predictions[i] > highestScore) {
                highestScore = predictions[i];
                recommendedParkingLotId = i;
            }
        }
        return recommendedParkingLotId;
    }
}
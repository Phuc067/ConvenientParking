//package com.parking.model;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.Reader;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.parking.entity.ParkingLot;
//
//public class CsvDataReader {
//
//    public List<ParkingLot> readCsvFile(String csvFilePath) throws IOException {
//        List<ParkingLot> parkingLotDataList = new ArrayList<>();
//
//        try (Reader reader = new FileReader(csvFilePath);
//             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
//
//            for (CSVRecord csvRecord : csvParser) {
//                double latitude = Double.parseDouble(csvRecord.get("latitude"));
//                double longitude = Double.parseDouble(csvRecord.get("longitude"));
//                double area = Double.parseDouble(csvRecord.get("area"));
//                double reviewPoint = Double.parseDouble(csvRecord.get("reviewPoint"));
//                boolean hasRoof = Boolean.parseBoolean(csvRecord.get("hasRoof"));
//                double cleanliness = Double.parseDouble(csvRecord.get("cleanliness"));
//
//                ParkingLot parkingLot = new ParkingLot(latitude, longitude, area, reviewPoint, hasRoof, cleanliness);
//                parkingLotDataList.add(parkingLot);
//            }
//        }
//
//        return parkingLotDataList;
//    }
//}
//package com.parking.utils;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//public class Distance {
//	public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
//        double theta = lon1 - lon2;
//        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
//        dist = Math.acos(dist);
//        dist = rad2deg(dist);
//        dist = dist * 60 * 1.1515;
//        dist = dist * 1.609344;
//        dist = Math.round(dist * 10.0) / 10.0;
//        return (dist);
//    }
//
//    static double deg2rad(double deg) {
//        return (deg * Math.PI / 180.0);
//    }
//
//    static double rad2deg(double rad) {
//        return (rad * 180.0 / Math.PI);
//    }
//
//    public static String getDistanceAndTimeGgApi(double lat1, double lon1, double lat2, double lon2) throws IOException {
//
//        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?";
//        url = url + "destinations=" + lat1 + "," + lon1;
//        url = url + "&origins=" + lat2 + "," + lon2;
//        url = url + "&key=AIzaSyDH75bfzU1Vy1VqSOAPBrVZ_OUCOlnLE8E";
//
//        URL uri = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) uri.openConnection();
//        con.setRequestMethod("GET");
//
//        con.setRequestProperty("Content-Type", "application/json");
//        int status = con.getResponseCode();
////        System.out.println(status);
//
//        if (status == 200) {
//            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer content = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//            in.close();
//            JSONObject result = new JSONObject(content.toString());
//            if (result.get("status").equals("OK")) {
//
//                JSONArray rows = result.getJSONArray("rows");
//                JSONObject r = rows.getJSONObject(0);
//                JSONArray elements = r.getJSONArray("elements");
//                JSONObject e = elements.getJSONObject(0);
//                JSONObject distance = new JSONObject (e.get("distance").toString());
//                JSONObject duration = new JSONObject(e.get("duration").toString());
//                Integer dt = Integer.parseInt(distance.get("value").toString());
//                double dtDB = dt/1000;
//                dtDB = Math.round(dtDB * 10.0) / 10.0;
//                Integer dr = Integer.parseInt(duration.get("value").toString())/60;
//
//                return Double. toString(dtDB) + ',' + dr;
//             }
//            else{
//
//                return "0.0,0";
//            }
//
//        } else {
//            return "0.0,0";
//        }
//    }
//}

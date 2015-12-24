package com.share.utils;

/**
 * Created by wahyuoi on 12/24/15.
 */
public class Location {
    public static boolean inRange(Double outLon, Double outLat, Double userLon, Double userLat){
        Double lat2 = outLat;
        Double lon2 = outLon;
        Double lat1 = userLat;
        Double lon1 = userLon;
        if (lat2 == null || lon2 == null)
            return false;
        final int R = 6371;
        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;
        return  (distance*1000 <= 100);
    }
}

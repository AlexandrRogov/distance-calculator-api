package gmail.rohaualexandr.distancecalculatorapi.service;

import gmail.rohaualexandr.distancecalculatorapi.controller.dto.ResponseDistance;
import org.springframework.stereotype.Service;

@Service
public class DistanceService {
    public static final int RADIUS_EARTH = 6371;

    public ResponseDistance getDistance(double lat1, double lon1, double lat2, double lon2) {
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return ResponseDistance.builder()
                .distance(RADIUS_EARTH * c)
                .build();
    }
}

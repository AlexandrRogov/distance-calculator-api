package gmail.rohaualexandr.distancecalculatorapi.service;

import gmail.rohaualexandr.distancecalculatorapi.controller.dto.ResponseDistance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DistanceServiceTest {

    private DistanceService distanceService = new DistanceService();

    @Test
    public void getDistanceBerlinFrance() {
        double lat1 = 52.5200;  // Berlin, Germany
        double lon1 = 13.4050;
        double lat2 = 48.8566;  // Paris, France
        double lon2 = 2.3522;

        ResponseDistance distance = distanceService.getDistance(lat1, lon1, lat2, lon2);

        Assertions.assertTrue(Math.abs(877 - distance.getDistance()) < 1);
    }

    @Test
    public void getDistanceBerlinSidney() {
        double lat1 = 52.5200;  // Berlin, Germany
        double lon1 = 13.4050;
        double lat2 = -33.8688;  // Sydney, Australia
        double lon2 = 151.2093;

        ResponseDistance distance = distanceService.getDistance(lat1, lon1, lat2, lon2);

        Assertions.assertTrue(Math.abs(16094 - distance.getDistance()) < 1);
    }
}

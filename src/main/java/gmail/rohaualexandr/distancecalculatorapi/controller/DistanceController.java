package gmail.rohaualexandr.distancecalculatorapi.controller;

import gmail.rohaualexandr.distancecalculatorapi.controller.dto.ResponseDistance;
import gmail.rohaualexandr.distancecalculatorapi.service.DistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(value = DistanceController.URL)
@RestController
public class DistanceController {

    public static final String URL = "/distance";

    private final DistanceService distanceService;

    @GetMapping()
    public ResponseDistance getDistance(
            @RequestParam("latitude1") double lat1,
            @RequestParam("longitude1") double lon1,
            @RequestParam("latitude2") double lat2,
            @RequestParam("longitude2") double lon2
    ) {
        return distanceService.getDistance(lat1, lon1, lat2, lon2);
    }
}

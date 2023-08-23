package gmail.rohaualexandr.distancecalculatorapi.controller;

import gmail.rohaualexandr.distancecalculatorapi.controller.dto.ResponseDistance;
import gmail.rohaualexandr.distancecalculatorapi.service.DistanceService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(value = DistanceController.URL)
@RestController
public class DistanceController {

    public static final String URL = "/distance";

    private final DistanceService distanceService;

    @Operation(summary = "Get a distance two point by latitude and longitude ", description = "Returns a distance in km")

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

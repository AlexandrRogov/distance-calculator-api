package gmail.rohaualexandr.distancecalculatorapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseDistance {
    @Schema(name = "Distance", example = "1", required = true)
    private double distance;
}

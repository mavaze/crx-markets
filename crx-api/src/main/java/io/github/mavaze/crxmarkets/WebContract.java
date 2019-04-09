package io.github.mavaze.crxmarkets;

import io.github.mavaze.crxmarkets.dto.RainWaterDetailedResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api("Web serving api operations")
public interface WebContract {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/www/algorithm/find-water-summary",
            consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Evaluates water volume blocked in an uneven surface",
            consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE,
            response = RainWaterDetailedResponseDto.class)
    RainWaterDetailedResponseDto getTrappedWaterSummary(
            @ApiParam(value = "Takes integer array representing surface profile", required = true) int[] contour
    );
}

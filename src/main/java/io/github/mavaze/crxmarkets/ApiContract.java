package io.github.mavaze.crxmarkets;

import io.github.mavaze.crxmarkets.dto.RainWaterVolumeResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api("Api operations")
public interface ApiContract {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/api/algorithm/find-water-volume",
            consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Evaluates water volume blocked in an uneven surface",
            consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE,
            response = RainWaterVolumeResponseDto.class)
    RainWaterVolumeResponseDto findWaterVolume(
            @ApiParam(value = "Takes integer array representing surface contour", required = true) int[] contour
    );
}

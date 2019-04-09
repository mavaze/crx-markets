package io.github.mavaze.crxmarkets.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
@RequiredArgsConstructor
@ApiModel("Represents response with volume of trapped rain water")
public class RainWaterVolumeResponseDto implements Serializable {

    @ApiModelProperty("Total volume of trapped water in all holes")
    private final Integer volume;
}

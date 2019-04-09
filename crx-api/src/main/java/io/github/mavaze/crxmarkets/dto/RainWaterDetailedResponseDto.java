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
@ApiModel("Represents response with trapped water at individual holes and total volume")
public class RainWaterDetailedResponseDto implements Serializable {

    @ApiModelProperty("Total volume of trapped water in all holes")
    private final Integer total;

    @ApiModelProperty("Actual water volume of rain water in each hole")
    private final int[] volume;
}

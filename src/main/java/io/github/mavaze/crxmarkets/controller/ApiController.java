package io.github.mavaze.crxmarkets.controller;

import io.github.mavaze.crxmarkets.ApiContract;
import io.github.mavaze.crxmarkets.dto.RainWaterVolumeResponseDto;
import io.github.mavaze.crxmarkets.services.RainWaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"", "/v1"})
public class ApiController implements ApiContract {

    private final RainWaterService rainWaterService;

    @Override
    public RainWaterVolumeResponseDto findWaterVolume(@Valid @NotNull @RequestBody final int[] contour) {
        return rainWaterService.findWaterVolume(contour);
    }
}

package io.github.mavaze.crxmarkets.controller;

import io.github.mavaze.crxmarkets.ApiContract;
import io.github.mavaze.crxmarkets.dto.RainWaterVolumeResponseDto;
import io.github.mavaze.crxmarkets.services.RainWaterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"", "/v1"})
public class ApiController implements ApiContract {

    private final RainWaterServiceImpl rainWaterService;

    @Override
    public RainWaterVolumeResponseDto findWaterVolume(@RequestBody final int[] contour) {
        return rainWaterService.findWaterVolume(contour);
    }
}

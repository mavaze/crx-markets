package io.github.mavaze.crxmarkets.controller;

import io.github.mavaze.crxmarkets.WebContract;
import io.github.mavaze.crxmarkets.dto.RainWaterDetailedResponseDto;
import io.github.mavaze.crxmarkets.services.RainWaterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/")
public class WebApiController implements WebContract {

    private final RainWaterServiceImpl rainWaterService;

    @Override
    public RainWaterDetailedResponseDto getTrappedWaterSummary(@RequestBody int[] contour) {
        return rainWaterService.getTrappedWaterDetails(contour);
    }
}

package io.github.mavaze.crxmarkets.services;

import io.github.mavaze.crxmarkets.dto.RainWaterDetailedResponseDto;
import io.github.mavaze.crxmarkets.dto.RainWaterVolumeResponseDto;

public interface RainWaterService {
    
    RainWaterVolumeResponseDto findWaterVolume(int[] contour);

    RainWaterDetailedResponseDto getTrappedWaterDetails(int[] contour);
}

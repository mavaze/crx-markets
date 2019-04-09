package io.github.mavaze.crxmarkets.converter;

import io.github.mavaze.crxmarkets.dto.RainWaterDetailedResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VolumeToSummaryResponseConverter implements Converter<int[], RainWaterDetailedResponseDto> {

    @Override
    public RainWaterDetailedResponseDto convert(final int[] volumeArray) {

        int totalVolume = 0;
        for(int volumeAtEachPeak : volumeArray) {
            totalVolume += volumeAtEachPeak;
        }
        return new RainWaterDetailedResponseDto(totalVolume, volumeArray);
    }
}

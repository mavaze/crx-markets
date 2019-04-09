package io.github.mavaze.crxmarkets.converter;

import io.github.mavaze.crxmarkets.dto.RainWaterVolumeResponseDto;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VolumeToResponseConverter implements Converter<Integer, RainWaterVolumeResponseDto> {

    @Override
    public RainWaterVolumeResponseDto convert(@NonNull final Integer volume) {
        return new RainWaterVolumeResponseDto(volume);
    }
}

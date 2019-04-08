package io.github.mavaze.crxmarkets.services;

import io.github.mavaze.crxmarkets.dto.RainWaterVolumeResponseDto;
import io.github.mavaze.crxmarkets.processor.Algorithm;
import io.github.mavaze.crxmarkets.processor.AlgorithmResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodType;

import static io.github.mavaze.crxmarkets.processor.Algorithm.FIND_TRAPPED_WATER_VOLUME;

@Slf4j
@Service
@RequiredArgsConstructor
public class RainWaterService {

    private final AlgorithmResolver resolver;
    private final ConversionService conversionService;

    public RainWaterVolumeResponseDto findWaterVolume(int[] contour) {

        final MethodType methodType = MethodType.methodType(Integer.class, Object[].class);
        final Integer volume = (Integer) resolver
                .resolve(FIND_TRAPPED_WATER_VOLUME, methodType)
                .execute(contour);
        return conversionService.convert(volume, RainWaterVolumeResponseDto.class);
    }
}

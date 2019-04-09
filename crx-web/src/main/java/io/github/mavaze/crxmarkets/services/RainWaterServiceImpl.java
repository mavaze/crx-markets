package io.github.mavaze.crxmarkets.services;

import io.github.mavaze.crxmarkets.dto.RainWaterDetailedResponseDto;
import io.github.mavaze.crxmarkets.dto.RainWaterVolumeResponseDto;
import io.github.mavaze.crxmarkets.processor.AlgorithmResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodType;

import static io.github.mavaze.crxmarkets.processor.Algorithm.FIND_TRAPPED_WATER_VOLUME;
import static io.github.mavaze.crxmarkets.processor.Algorithm.GET_TRAPPED_WATER_SUMMARY;

@Slf4j
@Service
@RequiredArgsConstructor
public class RainWaterServiceImpl implements RainWaterService {

    private final AlgorithmResolver resolver;
    private final ConversionService conversionService;

    @Override
    public RainWaterVolumeResponseDto findWaterVolume(int[] contour) {
        log.info("Request received to calculate water volume in profile {}", contour);
        final MethodType methodType = MethodType.methodType(Integer.class, Object[].class);
        final Integer volume = (Integer) resolver
                .resolve(FIND_TRAPPED_WATER_VOLUME, methodType)
                .execute(contour);
        return conversionService.convert(volume, RainWaterVolumeResponseDto.class);
    }

    @Override
    public RainWaterDetailedResponseDto getTrappedWaterDetails(int[] contour) {
        log.info("Request received to calculate water volume in individual holes in profile {}", contour);
        final MethodType methodType = MethodType.methodType(int[].class, Object[].class);
        final int[] volume = (int[]) resolver
                .resolve(GET_TRAPPED_WATER_SUMMARY, methodType)
                .execute(contour);
        return conversionService.convert(volume, RainWaterDetailedResponseDto.class);
    }
}

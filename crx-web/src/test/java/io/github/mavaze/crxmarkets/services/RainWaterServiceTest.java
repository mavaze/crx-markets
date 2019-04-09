package io.github.mavaze.crxmarkets.services;

import io.github.mavaze.crxmarkets.dto.RainWaterVolumeResponseDto;
import io.github.mavaze.crxmarkets.processor.Algorithm;
import io.github.mavaze.crxmarkets.processor.AlgorithmProcessor;
import io.github.mavaze.crxmarkets.processor.AlgorithmResolver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;

import java.lang.invoke.MethodType;

import static io.github.mavaze.crxmarkets.processor.Algorithm.FIND_TRAPPED_WATER_VOLUME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RainWaterServiceTest {

    @Mock
    private AlgorithmResolver resolver;

    @Mock
    private AlgorithmProcessor<Integer> processor;

    @Captor
    private ArgumentCaptor<Algorithm> algorithmArgumentCaptor;

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private RainWaterServiceImpl serviceUnderTest;

    @Test
    public void shouldFindAndExecuteProcessorAndWrapResponse() {
        int[] contour = {7, 1, 8, 11, 7, 9, 12};
        int expectedVolume = 12;
        final RainWaterVolumeResponseDto expectedResponse = new RainWaterVolumeResponseDto(expectedVolume);

        // given
        when(processor.execute(contour)).thenReturn(expectedVolume);
        when(resolver.resolve(algorithmArgumentCaptor.capture(), ArgumentMatchers.any(MethodType.class)))
                .thenReturn(processor);
        when(conversionService.convert(expectedVolume, RainWaterVolumeResponseDto.class))
                .thenReturn(expectedResponse);

        // when
        final RainWaterVolumeResponseDto waterVolume = serviceUnderTest.findWaterVolume(contour);

        // then
        assertEquals(algorithmArgumentCaptor.getValue(), FIND_TRAPPED_WATER_VOLUME);
        assertSame(expectedResponse, waterVolume);
    }

}

package io.github.mavaze.crxmarkets.processor;

import io.github.mavaze.crxmarkets.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.invoke.MethodType;

import static io.github.mavaze.crxmarkets.processor.Algorithm.FIND_TRAPPED_WATER_VOLUME;
import static io.github.mavaze.crxmarkets.utils.StubUtils.mockIntegerReturningProcessor;
import static java.lang.invoke.MethodType.methodType;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AlgorithmResolverTest {

    private AlgorithmResolver resolver;

    private AlgorithmProcessor<Integer> processor;

    @Before
    public void setup() {
        processor = mockIntegerReturningProcessor();
        resolver = new AlgorithmResolver(singletonList(processor));
    }

    @Test
    public void shouldReturnProcessorWithMatchingNameAndExecuteSignature() {

        // given
        when(processor.supports(anyString())).thenReturn(true);
        final MethodType methodType = methodType(Integer.class, Object[].class);

        // when
        AlgorithmProcessor resolvedProcessor = resolver.resolve(FIND_TRAPPED_WATER_VOLUME, methodType);

        // then
        assertEquals(processor, resolvedProcessor);
    }

    @Test(expected = NotFoundException.class)
    public void shouldFailToFindProcessorIfNoProcessorSupportsTrue() {
        // given
        when(processor.supports(anyString())).thenReturn(false);
        MethodType methodType = methodType(Integer.class, Object[].class);

        // when
        resolver.resolve(FIND_TRAPPED_WATER_VOLUME, methodType);
    }

    @Test(expected = NotFoundException.class)
    public void shouldFailToFindProcessorWithNoMatchingExecuteSignature() {
        // given
        when(processor.supports(anyString())).thenReturn(true);
        MethodType methodType = methodType(Long.class, Object[].class);

        // when
        resolver.resolve(FIND_TRAPPED_WATER_VOLUME, methodType);
    }
}

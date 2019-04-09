package io.github.mavaze.crxmarkets.utils;

import io.github.mavaze.crxmarkets.processor.AlgorithmProcessor;
import org.mockito.Mockito;

public class StubUtils {

    public static AlgorithmProcessor<Integer> mockIntegerReturningProcessor() {
        return Mockito.mock(DummyIntegerReturningProcessor.class);
    }

    private interface DummyIntegerReturningProcessor extends AlgorithmProcessor<Integer> { }
}

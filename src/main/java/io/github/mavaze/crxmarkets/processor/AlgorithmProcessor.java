package io.github.mavaze.crxmarkets.processor;

public interface AlgorithmProcessor<R> {

    boolean supports(String name);

    R execute(Object... args);
}

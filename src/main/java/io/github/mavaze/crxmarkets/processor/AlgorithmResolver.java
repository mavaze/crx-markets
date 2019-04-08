package io.github.mavaze.crxmarkets.processor;

import io.github.mavaze.crxmarkets.exception.NotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.util.List;
import java.util.function.Predicate;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlgorithmResolver {

    private final List<AlgorithmProcessor> algorithms;

    public AlgorithmProcessor resolve(@NonNull final Algorithm algorithm,
                                      @NonNull final MethodType methodType) throws NotFoundException {
        return algorithms.stream()
                .filter(a -> a.supports(algorithm.name()))
                .filter(matchExecuteSignature(methodType))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No matching algorithm processor found for requested " + algorithm));
    }

    private Predicate<AlgorithmProcessor> matchExecuteSignature(@NonNull final MethodType methodType) {
        final Lookup publicLookup = MethodHandles.publicLookup();

        return processor -> {
            try {
                publicLookup.findVirtual(processor.getClass(), "execute", methodType);
                return true;
            } catch (NoSuchMethodException | IllegalAccessException e) {
                return false;
            }
        };
    }
}

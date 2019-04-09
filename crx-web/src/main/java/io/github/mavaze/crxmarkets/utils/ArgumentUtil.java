package io.github.mavaze.crxmarkets.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import static java.util.Optional.ofNullable;

@UtilityClass
public class ArgumentUtil {

    public static int[] readFirstArgAsIntArray(@NonNull final Object arg) {
        return ofNullable(arg)
                .filter(int[].class::isInstance)
                .map(int[].class::cast)
                .orElseThrow(IllegalArgumentException::new);
    }
}

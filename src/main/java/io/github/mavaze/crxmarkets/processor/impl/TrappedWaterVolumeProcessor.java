package io.github.mavaze.crxmarkets.processor.impl;

import io.github.mavaze.crxmarkets.processor.AlgorithmProcessor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static io.github.mavaze.crxmarkets.processor.Algorithm.FIND_TRAPPED_WATER_VOLUME;
import static java.util.Optional.ofNullable;

@Slf4j
@Service
public class TrappedWaterVolumeProcessor implements AlgorithmProcessor<Integer> {

    @Override
    public boolean supports(@NonNull final String algorithmIdentifier) {
        return FIND_TRAPPED_WATER_VOLUME.name().equals(algorithmIdentifier);
    }

    @Override
    public Integer execute(@NonNull final Object[] args) {
        Assert.isTrue(args.length == 1,
                String.format("Expecting only one argument but found %s", args));
        final int[] contour = readFirstArgAsIntArray(args[0]);
        log.info("Finding volume of trapped water in the surface plotted as {}", contour);
        return findTrappedRainWater(contour);
    }

    private int[] readFirstArgAsIntArray(@NonNull Object arg) {
        return ofNullable(arg)
                    .filter(int[].class::isInstance)
                    .map(int[].class::cast)
                    .orElseThrow(IllegalArgumentException::new);
    }

    private int findTrappedRainWater(final int[] contour) {
        final int length = contour.length;

        int volume = 0;
        int leftPeak = 0, rightPeak = 0;
        int leftIndex = 0, rightIndex = length - 1;

        while (leftIndex <= rightIndex) {
            if (contour[leftIndex] < contour[rightIndex]) {
                if (contour[leftIndex] > leftPeak)
                    leftPeak = contour[leftIndex];
                else
                    volume += leftPeak - contour[leftIndex];
                leftIndex++;
            } else {
                if (contour[rightIndex] > rightPeak)
                    rightPeak = contour[rightIndex];
                else
                    volume += rightPeak - contour[rightIndex];
                rightIndex--;
            }
        }

        return volume;
    }
}

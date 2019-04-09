package io.github.mavaze.crxmarkets.processor.impl;

import io.github.mavaze.crxmarkets.processor.AlgorithmProcessor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static io.github.mavaze.crxmarkets.processor.Algorithm.GET_TRAPPED_WATER_SUMMARY;
import static io.github.mavaze.crxmarkets.utils.ArgumentUtil.readFirstArgAsIntArray;

@Slf4j
@Service
public class TrappedWaterDetailedProcessor implements AlgorithmProcessor<int[]> {

    @Override
    public boolean supports(@NonNull final String algorithmIdentifier) {
        return GET_TRAPPED_WATER_SUMMARY.name().equals(algorithmIdentifier);
    }

    @Override
    public int[] execute(Object... args) {
        Assert.isTrue(args.length == 1,
                String.format("Expecting only one argument but found %s", args));
        final int[] contour = readFirstArgAsIntArray(args[0]);
        log.info("Finding trapped water at individual indices in the surface plotted as {}", contour);
        return findTrappedRainWaterAtIndices(contour);
    }

    /**
     * A particular hole can store water equivalent to minimum of highest peak of either sides of the hole.
     * We can find the highest peak relative to individual hole by traversing the array in either direction.
     * We can run a single loop and prepare 2 arrays of left max and right max, by traversing array in both directions.
     * Now a hole can store water above its own height equivalent to minimum of relative right and left peak height.
     *
     * The requirement is to return sum of trapped water at each hole, but I have changed it bit to return the array
     * instead of sum of the array to let web ui plot a graph of its own, thus I am providing 2 solutions, one to meet
     * the requirements and the other to provide an additional web feature by my very own.
     *
     * Space Complexity: O(n)
     * Time Complexity: O(n)
     *
     * @param contour
     * @return water volume in each hole
     */
    private int[] findTrappedRainWaterAtIndices(int[] contour) {
        int length = contour.length;

        int[] leftPeaks = new int[length];
        int[] rightPeaks = new int[length];
        int[] waterVolume = new int[length];

        // int totalVolume = 0;

        leftPeaks[0] = contour[0];
        rightPeaks[length - 1] = contour[length - 1];

        for (int i = 1; i < length; i++) {
            leftPeaks[i] = Math.max(leftPeaks[i - 1], contour[i]);
            rightPeaks[length - i - 1] = Math.max(rightPeaks[length - i], contour[length - i - 1]);
        }
        for (int i = 0; i < length; i++) {
            waterVolume[i] = Math.min(leftPeaks[i], rightPeaks[i]) - contour[i];
            // totalVolume += water[i];
        }

        return waterVolume;
        // return totalVolume;
    }
}

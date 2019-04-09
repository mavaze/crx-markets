package io.github.mavaze.crxmarkets.processor.impl;

import io.github.mavaze.crxmarkets.processor.AlgorithmProcessor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static io.github.mavaze.crxmarkets.processor.Algorithm.FIND_TRAPPED_WATER_VOLUME;
import static io.github.mavaze.crxmarkets.utils.ArgumentUtil.readFirstArgAsIntArray;

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

    /**
     * Let's learn flow from example : 7, 3, 9, 3, 4, 2, 5, 4, 8, 9, 11
     * 1. Find all individual peaks from given contour - 7, 9, 4, 5, 8, 11
     *    A peak is peak when the element is taller than its immediate previous and next elements.
     * 2. Discard all smaller peaks between two taller peaks
     *    here discard 4, 5, 8 as they are in between two taller 9 and 11 elements.
     *    How would you do that?
     *    a. After finding a peak '9', keep subsequent smaller peaks 4 & 5 in temp array.
     *    b. Since next element 8 is taller than last element in temp i.e. 5, clear temp array
     *       and initialize it with 8.
     *    c. Continue this till you find peak taller than 9.
     *       11 then becomes your next peak and discard whole temp array.
     *    d. Had 8 & 11 been not there, join your temp array at the end of your array of peaks
     *       i.e. temp peaks 4 & 5 become legitimate peaks.
     * 3. Once you have peaks, sum the difference of height of elements from the
     *    minimum of adjacent peaks, which then becomes your volume.
     *
     * Space Complexity: O(n)
     * Time Complexity: O(n)
     *
     * @param contour
     * @return volume
     */
    protected Integer findTrappedRainWater(int[] contour) {
        final Integer[] peakIndices = findIndicesAtPeaks(contour);
        return peakIndices.length < 2 ? 0 : calculateVolumeBetweenPeaks(contour, peakIndices);
    }

    private Integer calculateVolumeBetweenPeaks(final int[] contour,
                                                @NonNull final Integer[] peakIndices) {

        int volume = 0;

        log.debug("Calculating water volume trapped between peak indices {}", peakIndices);
        // These 2 loops combined will be executed 'n' number of times
        for(int i = 1; i < peakIndices.length; i++) {
            for(int j = peakIndices[i-1] + 1; j < peakIndices[i]; j++) {
                volume += Math.max(0, Math.min(
                        contour[peakIndices[i - 1]],
                        contour[peakIndices[i]]
                ) - contour[j]);
            }
        }
        log.debug("Total volume of water trapped in {} is {}", contour, volume);
        return volume;
    }

    private Integer[] findIndicesAtPeaks(int[] contour) {
        log.debug("Finding peak indices from given contour {}", contour);

        final List<Integer> peakIndices = new ArrayList<>(); // size n/2
        final LinkedList<Integer> subPeakIndices = new LinkedList<>(); // size n/2 * k

        int relativeLeftPeak = 0;   // size 1

        for(int i = 0; i < contour.length; i++) { // size 1, time O(n)

            int current = contour[i];   // size 1
            int prev = i > 0 ? contour[i-1] : 0; // size 1
            int next = i < contour.length -1 ? contour[i+1] : 0; // size 1

            // If taller than previous and next element
            if ( current > prev && current >= next ) {
                if (!subPeakIndices.isEmpty() && current > contour[subPeakIndices.peekLast()]) {
                    subPeakIndices.clear();
                }

                relativeLeftPeak = Math.max(relativeLeftPeak, current);
                if(current < relativeLeftPeak) {
                    subPeakIndices.add(i);  // time O(1)
                } else {
                    peakIndices.add(i); // time O(1)
                    subPeakIndices.clear();
                }
            }
        }
        peakIndices.addAll(subPeakIndices);
        log.debug("Found peaks at indices represented by {}", peakIndices);

        return peakIndices.toArray(new Integer[]{});
    }
}

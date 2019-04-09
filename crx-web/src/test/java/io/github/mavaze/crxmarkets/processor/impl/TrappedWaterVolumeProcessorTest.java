package io.github.mavaze.crxmarkets.processor.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class TrappedWaterVolumeProcessorTest {

    @SpyBean
    private TrappedWaterVolumeProcessor processor;

    @Test
    public void shouldComputeWaterVolumeSuccess() {
        // given
        final int[] contour = {7, 4, 3, 11, 8, 9, 6, 4, 5, 7, 6};
        // when
        final Integer volume = processor.findTrappedRainWater(contour);
        // then
        assertEquals(14, volume.intValue());
    }

    @Test
    public void shouldComputeWaterVolumeWithNewTallerEndNode() {
        // given
        final int[] contour = {7, 4, 3, 11, 8, 9, 6, 4, 5, 7, 6, 12};
        // when
        final Integer volume = processor.findTrappedRainWater(contour);
        // then
        assertEquals(39, volume.intValue());
    }

    @Test
    public void shouldComputeWaterVolumeWithNewSmallerEndNode() {
        // given
        final int[] contour = {7, 4, 3, 11, 8, 9, 6, 4, 5, 7, 6, 10};
        // when
        final Integer volume = processor.findTrappedRainWater(contour);
        // then
        assertEquals(32, volume.intValue());
    }

    @Test
    public void shouldComputeWaterVolumeWithNewWaterTrappingRegion() {
        // given
        final int[] contour = {7, 4, 3, 11, 8, 9, 6, 4, 5, 7, 6, 12, 8, 9};
        // when
        final Integer volume = processor.findTrappedRainWater(contour);
        // then
        assertEquals(40, volume.intValue());
    }

    @Test
    public void shouldComputeWaterVolumeWithAlternatePeaks() {
        // given
        final int[] contour = {5, 3, 5, 3, 5, 3, 5, 3, 5, 3};
        // when
        final Integer volume = processor.findTrappedRainWater(contour);
        // then
        assertEquals(8, volume.intValue());
    }
}

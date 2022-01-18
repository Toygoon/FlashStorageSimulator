package adaptive;

import flash.*;

import java.util.Arrays;

public class AdaptiveMain {
    public static int getExtremumCount(FlashStorage flashStorage, boolean isMin) {
        int[] counts = new int[flashStorage.block.length], orig;

        for (int i = 0; i < flashStorage.block.length; i++)
            counts[i] = flashStorage.block[i].getCount();

        Arrays.sort(counts);

        if (isMin)
            return counts[0];
        return counts[counts.length - 1];
    }

    public static int diffState(FlashStorage flashStorage) {
        double diff;
        int max = getExtremumCount(flashStorage, false), min = getExtremumCount(flashStorage, true);

        diff = (double) (max - min) / FlashStorage.MAX_ERASURE_LIMIT;

        System.out.println(diff);
        return 0;
    }

    public static void main(String[] args) {
        FlashStorage flashStorage = new FlashStorage();

        for (int i = 0; i < FlashStorage.BLOCK_COUNT; i++) {
            for (int j = 0; j < Math.random() * (FlashStorage.MAX_ERASURE_LIMIT) * 100; j++) {
                try {
                    flashStorage.block[i].setData(String.valueOf(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        flashStorage.printStat();
        System.out.println(diffState(flashStorage));
    }
}

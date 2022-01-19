package algorithms;

import flash.*;

import static utils.WriteRandom.writeRandom;

public class AdaptiveMain {
    public static int getExtremumCount(FlashStorage flashStorage, boolean isMin) {
        /*int[] counts = new int[flashStorage.block.length], orig;

        for (int i = 0; i < flashStorage.block.length; i++)
            counts[i] = flashStorage.block[i].getEraseCount();

        Arrays.sort(counts);

        if (isMin)
            return counts[0];
        return counts[counts.length - 1];

         */
        return 0;
    }

    public static double diffState(FlashStorage flashStorage) {
        double diff;
        int max = getExtremumCount(flashStorage, false), min = getExtremumCount(flashStorage, true);
        diff = (double) (max - min) / FlashStorage.MAX_ERASURE_LIMIT;

        return diff;
    }

    public static void adaptiveMain(String[] args) {
        FlashStorage flashStorage = new FlashStorage();
        writeRandom(flashStorage);

        flashStorage.printStat();
        System.out.println("Diff state : " + diffState(flashStorage));
    }
}

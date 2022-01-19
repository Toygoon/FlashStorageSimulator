/* Simulation of Adaptive Leveling Algorithms
 * Adaptive Wear-leveling in Flash-based Memory
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

package algorithms;

import flash.*;
import java.util.*;

import static utils.SortList.*;

public class Adaptive {
    // The value for Count(max:init), this is a threshold for new adaptive settings
    private static int countMaxInit = -1;
    // Parameter n is 10% of MAX_ERASURE_LIMIT in Experiments
    // This is probably set by system designer
    private static int param = FlashStorage.MAX_ERASURE_LIMIT / 10;

    // Diff state, and bound should be set from main
    private static double diff, bound;

    private static void setThreshold() {
        // Set predefined Count(max;init) from system designer.
        System.out.println("Current limit is " + FlashStorage.MAX_ERASURE_LIMIT + ", Set the Count(max;init).");

        Scanner s = new Scanner(System.in);
        // The range of threshold should be limited.
        while (countMaxInit > FlashStorage.MAX_ERASURE_LIMIT || countMaxInit <= -1) {
            System.out.print("Threshold >>> ");
            countMaxInit = s.nextInt();
        }
    }

    public static double getdiffState(FlashStorage flashStorage) {
        // Difference state from equations
        double diff;
        int max = getExtremumCountBlock(flashStorage, false).getEraseCount(), min = getExtremumCountBlock(flashStorage, true).getEraseCount();
        diff = (double) (max - min) / FlashStorage.MAX_ERASURE_LIMIT;

        return diff;
    }

    public static double getBound(FlashStorage flashStorage) {
        // Exponential + linear growth trend from equations
        double expon = (getExtremumCountBlock(flashStorage, true).getEraseCount() / (double) FlashStorage.MAX_ERASURE_LIMIT) - 1;
        double linear = countMaxInit / (double) FlashStorage.MAX_ERASURE_LIMIT;
        double bound = Math.pow(param, expon) + linear;

        return bound;
    }

    public static void swapData(FlashStorage flashStorage) throws Exception {
        // Swap minimum data with maximum data.
        Block max, min;
        int maxIndex, minIndex;
        String tmp;

        // Get extremum count block to swap them
        max = getExtremumCountBlock(flashStorage, false);
        min = getExtremumCountBlock(flashStorage, true);

        maxIndex = flashStorage.block.indexOf(max);
        minIndex = flashStorage.block.indexOf(min);

        // Swap operations
        tmp = min.getData();
        flashStorage.block.get(minIndex).setData(flashStorage.block.get(maxIndex).getData());
        flashStorage.block.get(maxIndex).setData(tmp);

        System.out.print("(" + maxIndex + ", " + minIndex + ") ");
        System.out.println("diff : " + diff + ", bound : " + bound);

        // Update diff and bounds
        diff = getdiffState(flashStorage);
        bound = getBound(flashStorage);
    }

    public static void adaptiveMain(FlashStorage flashStorage) throws Exception {
        // Reset values
        countMaxInit = -1;
        diff = -1;
        bound = -1;

        // Set Count(max;init)
        setThreshold();

        // Calculate diff, and bound
        diff = getdiffState(flashStorage);
        bound = getBound(flashStorage);

        System.out.println("diff : " + diff + ", bound : " + bound);
        swapData(flashStorage);
    }
}

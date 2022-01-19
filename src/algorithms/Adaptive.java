/* Simulation of Adaptive Leveling Algorithms
 * Adaptive Wear-leveling in Flash-based Memory
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

package algorithms;

import flash.*;
import java.util.*;
import static utils.SortList.sortList;

public class Adaptive {
    public static int getExtremumCount(FlashStorage flashStorage, boolean isMin) {
        // To prevent modification, copy the current blocks into the tmp
        List<Block> tmp = new ArrayList<>();
        tmp.addAll(flashStorage.block);
        sortList(tmp);

        if (isMin)
            return tmp.get(0).getEraseCount();
        return tmp.get(tmp.size() - 1).getEraseCount();
    }

    public static double diffState(FlashStorage flashStorage) {
        double diff;
        int max = getExtremumCount(flashStorage, false), min = getExtremumCount(flashStorage, true);
        diff = (double) (max - min) / FlashStorage.MAX_ERASURE_LIMIT;

        return diff;
    }

    public static void adaptiveMain(FlashStorage flashStorage) {
        System.out.println(diffState(flashStorage));
    }
}

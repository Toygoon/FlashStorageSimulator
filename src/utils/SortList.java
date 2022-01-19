/* Sort the Blocks for Erase Count
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

package utils;

import flash.Block;
import flash.FlashStorage;

import java.util.*;

public class SortList {
    public static void sortList(List<Block> list) {
        // Override with new Comparator to sort erase count
        Collections.sort(list, new Comparator<Block>() {
            @Override
            public int compare(Block o1, Block o2) {
                if (o1.getEraseCount() > o2.getEraseCount())
                    return 1;
                else if (o1.getEraseCount() < o2.getEraseCount())
                    return -1;

                return 0;
            }
        });
    }

    public static Block getExtremumCountBlock(FlashStorage flashStorage, boolean isMin) {
        // To prevent modification, copy the current blocks into the tmp
        List<Block> tmp = new ArrayList<>();
        tmp.addAll(flashStorage.block);
        sortList(tmp);

        if (isMin)
            return tmp.get(0);
        return tmp.get(tmp.size() - 1);
    }
}

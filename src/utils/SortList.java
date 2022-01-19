/* Sort the Blocks for Erase Count
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

package utils;

import flash.Block;

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
}

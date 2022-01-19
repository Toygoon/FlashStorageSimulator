/* Simulation of Hot Cold Wear Leveling Algorithms
 * Efficient Management for Large-Scale Flash-Memory Storage Systems with Resource Conservation, 401p
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

package algorithms;

import flash.*;
import java.util.*;

import static utils.SortList.sortList;

public class HotCold {
    private static int threshold = -1;
    private static List<Block> hotData, coldData;

    private static void setThreshold() {
        // Set predefined threshold from system designer.
        System.out.println("Current limit is " + FlashStorage.MAX_ERASURE_LIMIT + ", Set the threshold.");

        Scanner s = new Scanner(System.in);
        // The range of threshold should be limited.
        while (threshold > FlashStorage.MAX_ERASURE_LIMIT || threshold <= -1) {
            System.out.print("Threshold >>> ");
            threshold = s.nextInt();
        }
    }

    private static void virtualGroup(FlashStorage flashStorage) {
        // Dividing two virtual groups to determine which block is hot or cold.
        hotData = new ArrayList<>();
        coldData = new ArrayList<>();

        // Separate into the two groups.
        for (Block block : flashStorage.block) {
            if (block.getEraseCount() >= threshold)
                hotData.add(block);
            else
                coldData.add(block);
        }

        // Sort into the ascending order for erasing count.
        sortList(hotData);
        sortList(coldData);
    }

    private static void swapData(FlashStorage flashStorage) throws Exception {
        int hotDatas = hotData.size(), coldDatas = coldData.size(), min;

        // Calculate which data is having minimum counts
        if (hotDatas > coldDatas)
            min = coldDatas;
        else
            min = hotDatas;

        // Swapping each data for minimum count times
        String tmp1, tmp2;
        System.out.print("Swap operation : ");
        for (int i=0; i<min; i++) {
            int hotBlockIndex = hotData.get(min - i - 1).getBlockNum(), coldBlockIndex = coldData.get(i).getBlockNum();

            tmp1 = flashStorage.block.get(hotBlockIndex).getData();
            tmp2 = flashStorage.block.get(coldBlockIndex).getData();
            flashStorage.block.get(hotBlockIndex).setData(tmp2);
            flashStorage.block.get(coldBlockIndex).setData(tmp1);

            System.out.print("(" + hotBlockIndex + ", " + coldBlockIndex + ") ");
        }

        System.out.println("\nSwapping hot and cold " + min + " data complete.");
    }

    public static void hotcoldMain(FlashStorage flashStorage) throws Exception {
        // Set threshold
        setThreshold();
        // Make two virtual groups
        virtualGroup(flashStorage);

        // Print each data
        System.out.print("Hot data : ");
        for (Block b : hotData)
            System.out.print(b.getBlockNum() + " ");
        System.out.println();

        System.out.print("Cold data : ");
        for (Block b : coldData)
            System.out.print(b.getBlockNum() + " ");
        System.out.println();

        // Do the swapping algorithm
        swapData(flashStorage);

    }
}

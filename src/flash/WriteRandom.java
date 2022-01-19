/* Writing Random Counts for Blocks
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

package flash;

import java.util.Random;

public class WriteRandom {
    public static void writeRandom(FlashStorage flashStorage) {
        Random random = new Random();
        // Seed for more randomly generated integer
        random.setSeed(System.currentTimeMillis());

        for (int i = 0; i < FlashStorage.BLOCK_COUNT; i++) {
            // Set current block number
            flashStorage.block.get(i).setBlockNum(i);
            // Generate random integer for counts
            int rand = (int) ((Math.random() * (FlashStorage.MAX_ERASURE_LIMIT - 0) + 0));

            // Write the data for block randomly
            for (int j = 0; j < rand; j++) {
                try {
                    flashStorage.block.get(i).setData("Initial " + i + " block data.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

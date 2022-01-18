package flash;

import java.util.Random;

public class WriteRandom {
    public static void writeRandom(FlashStorage flashStorage) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        for (int i = 0; i < FlashStorage.BLOCK_COUNT; i++) {
            flashStorage.block.get(i).setBlockNum(i);
            int rand = (int) ((Math.random() * (FlashStorage.MAX_ERASURE_LIMIT - 0) + 0));
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

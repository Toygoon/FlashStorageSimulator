package flash;

import java.util.ArrayList;
import java.util.List;

public class FlashStorage {
    public List<Block> block;

    public static final int BLOCK_COUNT = 10;
    public static final int MAX_ERASURE_LIMIT = 100;

    public FlashStorage() {
        this.block = new ArrayList<>();

        for (int i = 0; i < BLOCK_COUNT; i++) {
            this.block.add(new Block());
        }
    }

    public void printStat() {
        System.out.println("[STORAGE] Erasure limit : " + MAX_ERASURE_LIMIT);
        System.out.println("[STORAGE] Total blocks : " + BLOCK_COUNT);

        for (int i = 0; i < BLOCK_COUNT; i++)
            System.out.println("[STORAGE] Block " + i + " erase count : " + block.get(i).getEraseCount() + ", data : " + block.get(i).getData());
    }

    public void printSimple() {
        for (int i = 0; i < BLOCK_COUNT; i++)
            System.out.print("[" + i + "]:" + block.get(i).getEraseCount() + "  ");
    }
}

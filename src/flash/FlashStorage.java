package flash;

public class FlashStorage {
    public Block[] block;

    public static final int BLOCK_COUNT = 10;
    public static final int MAX_ERASURE_LIMIT = 100;

    public FlashStorage() {
        this.block = new Block[BLOCK_COUNT];

        for (int i = 0; i < this.block.length; i++) {
            block[i] = new Block();
        }
    }

    public void printStat() {
        System.out.println("[STORAGE] Erasure limit : " + MAX_ERASURE_LIMIT);
        System.out.println("[STORAGE] Total blocks : " + BLOCK_COUNT);

        for (int i = 0; i < BLOCK_COUNT; i++)
            System.out.println("[STORAGE] Block " + i + " erase count : " + block[i].getCount());
    }
}

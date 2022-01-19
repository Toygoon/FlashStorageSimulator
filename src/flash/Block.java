package flash;

public class Block {
    private int blockNum;
    private boolean isDiabled;
    private int eraseCount;
    private String data;

    public Block() {
        this.blockNum = -1;
        this.isDiabled = false;
        this.eraseCount = 0;
        this.data = "";
    }

    public void setBlockNum(int blockNum) {
        this.blockNum = blockNum;
    }

    public int getBlockNum() {
        return blockNum;
    }

    public int getEraseCount() {
        // System.out.println("[BLOCK] Erase count : " + this.eraseCount);
        return this.eraseCount;
    }

    public String getData() {
        // System.out.println("[BLOCK] Data : " + this.data);
        return this.data;
    }

    public void setData(String data) throws Exception {
        if (checkBad()) {
            // System.out.println("[BLOCK] Erasure limit(" + FlashStorage.MAX_ERASURE_LIMIT + ") exceeded.");
            throw new Exception("Erasure limit exceeded");
        }

        this.data = data;
        eraseCount++;

        // System.out.println("[BLOCK] Data written (" + this.eraseCount + " times) : " + data);
    }

    public boolean checkBad() {
        return this.eraseCount == FlashStorage.MAX_ERASURE_LIMIT;
    }
}

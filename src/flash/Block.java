/* Simulation of Block for Flash Storage
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

package flash;

public class Block {
    /* Block simulation class
    blockNum : current index number of block
    isDisabled : erase limit exceeded block
    eraseCount : the count of erasures
    data : the data block saves
     */

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
        return this.eraseCount;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) throws Exception {
        if (checkBad())
            throw new Exception("Erasure limit exceeded");

        this.data = data;
        eraseCount++;
    }

    public boolean checkBad() {
        // Checking the block limit exceed
        return this.eraseCount == FlashStorage.MAX_ERASURE_LIMIT;
    }
}

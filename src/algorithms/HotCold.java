package algorithms;

import flash.*;

import java.util.*;

public class HotCold {
    private static int threshold = -1;
    private static List<Block> hotData, coldData;

    private static void setThreshold() {
        System.out.println("Current limit is " + FlashStorage.MAX_ERASURE_LIMIT + ", Set the threshold.");

        Scanner s = new Scanner(System.in);
        while (threshold > FlashStorage.MAX_ERASURE_LIMIT || threshold <= -1) {
            System.out.print("Threshold >>> ");
            threshold = s.nextInt();
        }
    }

    private static void virtualGroup(FlashStorage flashStorage) {
        hotData = new ArrayList<>();
        coldData = new ArrayList<>();

        for (Block block : flashStorage.block) {
            if (block.getEraseCount() >= threshold)
                hotData.add(block);
            else
                coldData.add(block);
        }
        sortList(hotData);
        sortList(coldData);
    }

    private static void sortList(List<Block> list) {
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

    private static void swapData(FlashStorage flashStorage) throws Exception {
        int hotDatas = hotData.size(), coldDatas = coldData.size(), min;

        if (hotDatas > coldDatas)
            min = coldDatas;
        else
            min = hotDatas;

        String tmp1, tmp2;
        for (int i=0; i<min; i++) {
            tmp1 = flashStorage.block.get(hotData.get(min - i - 1).getBlockNum()).getData();
            tmp2 = flashStorage.block.get(coldData.get(i).getBlockNum()).getData();
            flashStorage.block.get(hotData.get(min - i - 1).getBlockNum()).setData(tmp2);
            flashStorage.block.get(coldData.get(i).getBlockNum()).setData(tmp1);
        }

        System.out.println("Swapping hot and cold " + min + " data complete.");

    }

    public static void hotcoldMain(FlashStorage flashStorage) throws Exception {
        setThreshold();
        virtualGroup(flashStorage);

        System.out.print("Hot data : ");
        for (Block b : hotData)
            System.out.print(b.getBlockNum() + " ");
        System.out.println();

        System.out.print("Cold data : ");
        for (Block b : coldData)
            System.out.print(b.getBlockNum() + " ");
        System.out.println();

        swapData(flashStorage);

    }
}

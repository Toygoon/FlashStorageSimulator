/* Simulation of Wear Leveling Algorithms for Flash Storage
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

import algorithms.HotCold;
import flash.FlashStorage;

import java.util.Scanner;
import static utils.WriteRandom.writeRandom;

public class WearLevelingMain {
    public static void main(String[] args) throws Exception {
        FlashStorage flashStorage = new FlashStorage();
        writeRandom(flashStorage);

        Scanner s = new Scanner(System.in);
        String input = "";

        //flashStorage.printSimple();
        //System.out.println();
        System.out.println("Commands : write, reset, status, hotcold");
        while (true) {
            System.out.print(">>> ");
            input = s.nextLine();

            switch (input) {
                case "write":
                    System.out.print("Block >>> ");
                    String tmp = s.nextLine();
                    System.out.print("Data >>> ");
                    flashStorage.block.get(Integer.parseInt(tmp)).setData(s.nextLine());
                    break;
                case "hotcold":
                    HotCold.hotcoldMain(flashStorage);
                    break;
                case "status":
                    flashStorage.printStat();
                    break;
                case "reset":
                    flashStorage = new FlashStorage();
                    writeRandom(flashStorage);
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Invalid Command.");
                    break;
            }
        }
    }
}

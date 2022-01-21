/* Simulation of Wear Leveling and Garbage Collection Algorithms for Flash Storage
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

import algorithms.*;
import flash.FlashStorage;

import java.util.Scanner;
import static utils.WriteRandom.writeRandom;

public class SimulatorMain {
    public static void main(String[] args) throws Exception {
        // New flash storage
        FlashStorage flashStorage = new FlashStorage();
        Scanner s = new Scanner(System.in);
        String input = "";

        // If wear leveling algorithm needs a block for setting
        int currentBlock = -1;

        // Show command list
        System.out.println("Operations : status, random, write");
        System.out.println("Algorithms : hotcold, adaptive");

        // Input line
        while (true) {
            System.out.print(">>> ");
            input = s.nextLine();

            switch (input) {
                // Operations
                case "write":
                    System.out.print("Block >>> ");
                    String tmp = s.nextLine();
                    System.out.print("Data >>> ");
                    flashStorage.block.get(Integer.parseInt(tmp)).setData(s.nextLine());
                    break;

                case "status":
                    flashStorage.printStat();
                    break;

                case "random":
                    flashStorage = new FlashStorage();
                    writeRandom(flashStorage);
                    break;

                case "reset":
                    flashStorage = new FlashStorage();
                    break;

                // Algorithms
                case "hotcold":
                    HotCold.hotcoldMain(flashStorage);
                    break;

                case "adaptive":
                    Adaptive.adaptiveMain(flashStorage);
                    break;

                /*
                case "rejuv":
                    Rejuvenator.rejuvMain(flashStorage);
                    break;

                 */

                case "exit":
                    return;

                default:
                    System.out.println("Invalid Command.");
                    break;
            }
        }
    }
}

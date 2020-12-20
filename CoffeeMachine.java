package machine;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CoffeeMachine {
    private static Scanner scan = new Scanner(System.in);
    private static int waterPerCup = 200;
    private static int milkPerCup = 50;
    private static int beansPerCup = 15;

    public static void main(String[] args) {
        System.out.println("Write how many ml of water the coffee machine has:");
        var waterIn = scan.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        var milkIn = scan.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        var beansIn = scan.nextInt();
        System.out.println("Write how many cups of coffee you will need:");
        var cups = scan.nextInt();
        var ableToMake = Collections.min(
                List.of(waterIn / waterPerCup, milkIn / milkPerCup, beansIn / beansPerCup));
        if (cups == ableToMake) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (cups < ableToMake) {
            System.out.printf(
                    "Yes, I can make that amount of coffee (and even %d more than that)\n",
                    ableToMake - cups);
        } else {
            System.out.printf("No, I can make only %d cup(s) of coffee", ableToMake);
        }
    }
}

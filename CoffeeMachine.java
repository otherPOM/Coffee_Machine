package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private static Scanner scan = new Scanner(System.in);
    private static int waterPerCup = 200;
    private static int milkPerCup = 50;
    private static int beansPerCup = 15;

    public static void main(String[] args) {
        System.out.println("Write how many cups of coffee you will need:");
        var cups = scan.nextInt();
        System.out.printf("For %d cups of coffee you will need:\n" +
                "%d ml of water\n" +
                "%d ml of milk\n" +
                "%d g of coffee beans\n", cups, cups * waterPerCup,
                cups * milkPerCup, cups * beansPerCup);
    }
}

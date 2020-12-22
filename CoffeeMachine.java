package machine;

import java.util.Locale;
import java.util.Scanner;

public class CoffeeMachine {
    private static Scanner scan = new Scanner(System.in);

    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;

    public CoffeeMachine(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
    }

    private void printState() {
        System.out.printf("The coffee machine has:\n" +
                "%d of water\n" +
                "%d of milk\n" +
                "%d of coffee beans\n" +
                "%d of disposable cups\n" +
                "%d of money\n\n", water, milk, beans, cups, money);
    }

    private void buy(int choice) {
        switch (choice) {
            case 1:
                water -= 250;
                beans -= 16;
                money += 4;
                break;
            case 2:
                water -= 350;
                milk -= 75;
                beans -= 20;
                money += 7;
                break;
            case 3:
                water -= 200;
                milk -= 100;
                beans -= 12;
                money += 6;
                break;
            default:
                cups++;
        }
        cups--;
    }

    private void fill(int water, int milk, int beans, int cups) {
        this.water += water;
        this.milk += milk;
        this.beans += beans;
        this.cups += cups;
    }

    private int collect() {
        var x = money;
        money = 0;
        return x;
    }

    public static void main(String[] args) {
        var machine = new CoffeeMachine(400, 540, 120, 9, 550);

        machine.printState();
        System.out.println("Write action (byu, fill, take):");
//        while (true) {
            var action = scan.nextLine();
            switch (action.toLowerCase(Locale.ROOT)) {
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                    var choice = scan.nextInt();
                    machine.buy(choice);
                    scan.nextLine();
                    break;
                case "fill":
                    System.out.println("Write how many ml of water do you want to add:");
                    var waterAdd = scan.nextInt();
                    System.out.println("Write how many ml of milk do you want to add:");
                    var milkAdd = scan.nextInt();
                    System.out.println("Write how many grams of coffee beans do you want to add:");
                    var beansAdd = scan.nextInt();
                    System.out.println("Write how many disposable cups of coffee do you want to add:");
                    var cupsAdd = scan.nextInt();
                    machine.fill(waterAdd, milkAdd, beansAdd, cupsAdd);
                    scan.nextLine();
                    break;
                case "take":
                    System.out.println("I gave you $" + machine.collect());
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
            machine.printState();
//        }
//        var waterIn = scan.nextInt();
//        System.out.println("Write how many ml of milk the coffee machine has:");
//        var milkIn = scan.nextInt();
//        System.out.println("Write how many grams of coffee beans the coffee machine has:");
//        var beansIn = scan.nextInt();
//        System.out.println("Write how many cups of coffee you will need:");
//        var cups = scan.nextInt();
//        var ableToMake = Collections.min(
//                List.of(waterIn / waterPerCup, milkIn / milkPerCup, beansIn / beansPerCup));
//        if (cups == ableToMake) {
//            System.out.println("Yes, I can make that amount of coffee");
//        } else if (cups < ableToMake) {
//            System.out.printf(
//                    "Yes, I can make that amount of coffee (and even %d more than that)\n",
//                    ableToMake - cups);
//        } else {
//            System.out.printf("No, I can make only %d cup(s) of coffee", ableToMake);
//        }
    }
}

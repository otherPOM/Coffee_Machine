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

    private boolean buy(String choice) {
        switch (choice) {
            case "1":
                if (water >= 250 && beans >= 16) {
                    water -= 250;
                    beans -= 16;
                    money += 4;
                    cups--;
                    return true;
                }
                break;
            case "2":
                if (water >= 350 && milk >= 75 && beans >= 20) {
                    water -= 350;
                    milk -= 75;
                    beans -= 20;
                    money += 7;
                    cups--;
                    return true;
                }
                break;
            case "3":
                if (water >= 200 && milk >= 100 && beans >= 12) {
                    water -= 200;
                    milk -= 100;
                    beans -= 12;
                    money += 6;
                    cups--;
                    return true;
                }
                break;
        }
        return false;
    }

    private String whatsMissing(String choice) {
        switch (choice) {
            case "1":
                return water < 250 ? "water" : "beans";
            case "2":
                return water < 350 ? "water" : milk < 75 ? "milk" : "beans";
            case "3":
                return water < 200 ? "water" : milk < 100 ? "milk" : "beans";

        }
        return null;
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

        while (true) {
        System.out.println("Write action (byu, fill, take, remaining, exit):");
            var action = scan.nextLine();
            switch (action.toLowerCase(Locale.ROOT)) {
                case "buy":
                    System.out.println("What do you want to buy? " +
                            "1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    var choice = scan.next();

                    if (!choice.toLowerCase(Locale.ROOT).equals("1") &&
                    !choice.toLowerCase(Locale.ROOT).equals("2") &&
                    !choice.toLowerCase(Locale.ROOT).equals("3") &&
                    !choice.toLowerCase(Locale.ROOT).equals("back")) {
                        System.out.println("Invalid input");
                    }

                    if (choice.toLowerCase(Locale.ROOT).equals("back")) { break; }

                    if (machine.buy(choice)) {
                        System.out.println("I have enough resources, making you a coffee!");
                    } else {
                        System.out.println("Sorry not enough " + machine.whatsMissing(choice) + "!");
                    }
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
                case "remaining":
                    machine.printState();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Invalid input");
                    break;
            }

        }
    }
}

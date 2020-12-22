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
    private State state;

    public CoffeeMachine(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
        state = State.MAIN_MENU;
        mainMenu();
    }

    private void mainMenu() {
        System.out.println("Write action (byu, fill, take, remaining, exit):");
    }

    private void buyMenu() {
        System.out.println("What do you want to buy? " +
                "1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
    }

    private void fillingMenu() {
        switch (state) {
            case FILLING_WATER:
                System.out.println("Write how many ml of water do you want to add:");
                break;
            case FILLING_MILK:
                System.out.println("Write how many ml of milk do you want to add:");
                break;
            case FILLING_BEANS:
                System.out.println("Write how many grams of coffee beans do you want to add:");
                break;
            case FILLING_CUPS:
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                break;
            case MAIN_MENU:
                mainMenu();
                break;
        }
    }

    private void interact(String action) {
        switch (state) {
            case MAIN_MENU:
                switch (action.toLowerCase(Locale.ROOT)) {
                    case "buy":
                        buyMenu();
                        state = State.CHOOSING_PRODUCT;
                        return;
                    case "fill":
                        state = State.FILLING_WATER;
                        fillingMenu();
                        break;
                    case "take":
                        System.out.println("I gave you $" + collect());
                        mainMenu();
                        break;
                    case "remaining":
                        printState();
                        mainMenu();
                        break;
                    case "exit":
                        state = State.TURNED_OFF;
                        return;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
                break;
            case FILLING_WATER:
            case FILLING_MILK:
            case FILLING_BEANS:
            case FILLING_CUPS:
                fill(action);
                fillingMenu();
                break;
            case CHOOSING_PRODUCT:
                if (action.toLowerCase(Locale.ROOT).equals("back")) {
                    mainMenu();
                    state = State.MAIN_MENU;
                    return;
                }
                if (buy(action)) System.out.println("I have enough resources, making you a coffee!");
                else System.out.println("Sorry not enough " + whatsMissing(action) + "!");
                mainMenu();
                state = State.MAIN_MENU;
                break;
        }
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

    private void fill(String action) {
        var x = Integer.parseInt(action);
        switch (state) {
            case FILLING_WATER:
                water += x;
                state = State.FILLING_MILK;
                break;
            case FILLING_MILK:
                milk += x;
                state = State.FILLING_BEANS;
                break;
            case FILLING_BEANS:
                beans += x;
                state = State.FILLING_CUPS;
                break;
            case FILLING_CUPS:
                cups += x;
                state = State.MAIN_MENU;
                break;
        }
    }

    private int collect() {
        var x = money;
        money = 0;
        return x;
    }

    public static void main(String[] args) {
        var machine = new CoffeeMachine(400, 540, 120, 9, 550);
        while (machine.state != State.TURNED_OFF) {
            var action = scan.nextLine();
            machine.interact(action);
        }
    }
}

package Sorting;

import java.util.Scanner;

public class SortingMenu { //Consists of a repeatable menu that will interact with the user

    private SortableList list;
    private Scanner scan;

    public SortingMenu() {
        list = new SortableList(1000); //The list's size will be 1000 by default, this can be altered by the user in the menu settings
        scan = new Scanner(System.in);
    }

    public void showMenu() {
        char option = ' ';
        int numberOfElements = 0;

        do { //The main loop will show the user the options and receive their input, all the option are self explanatory
            System.out.println("***************List Sorter***************");
            System.out.println("[1] Print list.");
            System.out.println("[2] Randomize list");
            System.out.println("[3] Sort with insertion sort.");
            System.out.println("[4] Sort with merge sort.");
            System.out.println("[5] Sort with quick sort.");
            System.out.println("[6] Settings.");
            System.out.println("[Q] Quit.");
            System.out.println("******************************************");
            System.out.println("\n Type in your option: ");
            option = this.scan.next().charAt(0);

            switch (option) {
                case '1':
                    System.out.println("Type in how many elements should be printed to the screen: ");
                    numberOfElements = this.scan.nextInt();
                    clearScreen();
                    this.list.printNElements(numberOfElements);
                    System.out.print("\n");
                    break;
                case '2':
                    this.list.randomizeList();
                    clearScreen();
                    System.out.println("List randomized succesfully!\n");
                    break;
                case '3':
                    clearScreen();
                    this.list.insertionSort();
                    System.out.print("\n");
                    break;
                case '4':
                    clearScreen();
                    this.list.mergeSort();
                    System.out.print("\n");
                    break;
                case '5':
                    clearScreen();
                    this.list.quickSort();
                    System.out.print("\n");
                    break;
                case '6':
                    clearScreen();
                    this.settingsMenu();
                    break;
                default:
                    clearScreen();
                    if (option != 'Q') {
                        System.out.println("Please, type in a valid character.\n");
                    }
            }
        } while (option != 'Q');
    }

    private void settingsMenu() { //The settings menu is a sub menu inside the main menu. It allows the user to change the size of the list and it's bounds

        char option = ' ';

        do {
            System.out.println("*****************Settings*****************");
            System.out.println("[1] Change list size. (The current size is " + this.list.getSize() + ")");
            System.out.println("[2] Change lower bounds/minimum number that can be generated. (The current lower bound is " + this.list.getLowerBounds() + ")");
            System.out.println("[3] Change upper bounds/maximum number that can be generated. (The current upper bound is " + this.list.getUpperBounds() + ")");
            System.out.println("[Q] Quit.");
            System.out.println("******************************************");
            System.out.println("\n Type in your option: ");
            option = this.scan.next().charAt(0);

            switch (option) {
                case '1':
                    System.out.println("Inform the new size: ");
                    int size = this.scan.nextInt();
                    this.list.changeSize(size);
                    clearScreen();
                    System.out.println("Size changed succesfully!\n");
                    break;
                case '2':
                    System.out.println("Inform the new lower bound: ");
                    int lowerBound = this.scan.nextInt();
                    this.list.setLowerBounds(lowerBound);
                    clearScreen();
                    System.out.println("Lower bound changed succesfully!\n");
                    break;
                case '3':
                    System.out.println("Inform the new upper bound: ");
                    int upperBound = this.scan.nextInt();
                    this.list.setUpperBounds(upperBound);
                    clearScreen();
                    System.out.println("Upper bound changed succesfully!\n");
                    break;
                default:
                    clearScreen();
                    if (option != 'Q') {
                        System.out.println("Please, type in a valid character.\n");
                    }
            }
        } while (option != 'Q');
    }

    private void clearScreen() { //Not ideal but it will work with any OS. TODO: ADD FIX
        for (int i = 0; i < 30; i++) {
            System.out.print("\n");
        }
    }

}

package com.bptn.rpg.utils;

import java.util.Scanner;

public class InputUtil {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Reusable method to get a valid integer from the user.
     *
     * @param prompt the message displayed to the user
     * @return a valid integer input from the user
     */
    public static int getInt(String prompt) {
        System.out.println(prompt);

        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a number.");
            scanner.next(); // consume the invalid input
            System.out.print(prompt); // prompt again
        }

        int value = scanner.nextInt();
        scanner.nextLine(); // consume the remaining newline
        return value;
    }
}

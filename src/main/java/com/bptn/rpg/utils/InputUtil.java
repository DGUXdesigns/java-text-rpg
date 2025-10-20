package com.bptn.rpg.utils;

import java.util.Scanner;

public class InputUtil {

    private static final Scanner scanner = new Scanner(System.in);

    public static int getInt(String prompt) {
        int value;

        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                Messages.emptyInput();
                continue;
            }

            try {
                value = Integer.parseInt(input);
                return value;
            } catch (NumberFormatException e) {
                Messages.invalidInput();
            }
        }
    }
}

package com.utils;


import java.util.Scanner;

/**
 * The class is Scanner Utils
 *
 * @author TranANhTuan
 * @create_date August 13,2021
 */
public class ScannerUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputEmail(String errorMessage) {
        while (true) {
            String email = ScannerUtils.inputString(errorMessage);
            if (email == null || !email.contains("@gmail.com")) {
                System.err.println(errorMessage);
                System.out.print("Retype your email: ");
            } else {
                return email;
            }
        }
    }

    public static int inputFunction(int a, int b, String errorMessage) {
        while (true) {
            int number = ScannerUtils.inputInt(errorMessage);
            if (number >= a && number <= b) {
                return number;
            } else {
                System.err.println(errorMessage);
            }
        }
    }

    public static String inputPassword(String errorMessage) {
        while (true) {
            String password = ScannerUtils.inputString(errorMessage);
            if (password.length() < 6 || password.length() > 12) {
                System.err.println(errorMessage);
//                System.out.println();
                System.out.println("Retype your password: ");
                continue;
            }

            boolean hasAtLeast1Character = false;

            for (int i = 0; i < password.length(); i++) {
                if (Character.isUpperCase(password.charAt(i))) {
                    hasAtLeast1Character = true;
                    break;
                }
            }
            if (hasAtLeast1Character) {
                return password;
            } else {
                System.err.println(errorMessage);
//                System.out.println();
                System.out.println("Retype your password: ");
            }
        }
    }

    public static int inputPositiveInt(String errorMessage) {
        while (true) {
            int id = ScannerUtils.inputInt(errorMessage);
            if (id < 0) {
                System.err.println(errorMessage);
            } else {
                return id;
            }
        }
    }

    public static int inputInt(String errorMessage) {
        while (true) {
            try {
                String input = scanner.nextLine();
                input = input.trim();
                int output = Integer.parseInt(input);
                return output;
            } catch (Exception e) {
                System.err.println(errorMessage);
            }
        }
    }

    public static String inputString(String errorMessage) {
        while (true) {

            String inputString = scanner.nextLine();
            inputString = inputString.trim();
            inputString = inputString.replaceAll("\\s+", " ");

            return inputString;
        }
    }
}

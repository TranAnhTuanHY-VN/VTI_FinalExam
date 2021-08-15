package com.frontend;

import com.backend.presentationlayer.UserController;
import com.utils.JDBCUtils;
import com.utils.ScannerUtils;


/**
 * The class execute final exam
 *
 * @author TranAnhTuan
 * @create_date August 13,2021
 */

public class Program {
    public static void main(String[] args) throws Exception {
        UserController userController = new UserController();
        int choose;
        while (true) {
            //Main menu to choose
            System.out.println();
            System.out.println("============================================");
            System.out.println("================FUNCTION LIST===============");
            System.out.println("==||1: Print users from input projectID ||==");
            System.out.println("==||2: Print manager list               ||==");
            System.out.println("==||3: LOGIN                            ||==");
            System.out.println("==||0: LOGOUT, EXIT                     ||==");
            System.out.println("============================================");
            System.out.println();

            System.out.print("Input your choose: ");
            choose = ScannerUtils.inputFunction(0, 3, "You must choose 0 -> 3. Retype!");
            switch (choose) {
                case 1:
                    System.out.println();
                    System.out.println("============|| LIST USER ||=============");
                    try {
                        System.out.println("Enter projectID:");
                        int id = ScannerUtils.inputPositiveInt("Input a positive integer!");
                        System.out.println();
                        System.out.println("Result: ");
                        userController.getListUserByProjectID(id);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println();
                        System.out.println("============|| GET USER  ||=============");
                        userController.getListProjectManager();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Nhập Email: ");
                    String email = ScannerUtils.inputEmail("Email is not in the correct format!");
                    System.out.println("Nhập Passwors:");
                    String password = ScannerUtils.inputPassword("Password must be between 6-12 characters and at least one upper letter!");
                    try {
                        userController.login(email, password);
                        System.out.println(JDBCUtils.getMessage().getProperty("user.login.complete"));
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                        System.err.println(JDBCUtils.getMessage().getProperty("user.login.false"));
                    }
                    break;
                case 0:
                    System.err.println("You have exited the program!!");
                    return;
            }
        }
    }
}
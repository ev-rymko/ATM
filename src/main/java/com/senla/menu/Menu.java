package com.senla.menu;

import java.util.Scanner;

public class Menu implements IMenu {
    private final AdminMenu adminMenu = new AdminMenu();
    private final UserMenu userMenu = new UserMenu();

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please choose correct: ");
        System.out.println("1. I am the admin.");
        System.out.println("2. I am the user");
        int begin = scanner.nextInt();
        if (begin == 1) {
            adminMenu.start();
        } else if (begin == 2) {
            userMenu.start();
        }
    }

}

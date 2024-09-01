package com.Youreserve;

import java.util.Scanner;

public class ReserveRoom {


    public static int chooseOption() {

        System.out.println("1 . Do you want to reserve a room?");
        System.out.println("0 . Go back to main menu");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        return option;
    }

   public static void createReservation(Scanner scanner) {
        System.out.println("Please enter the number of the room you would like to reserve");
        String number = scanner.next();
       System.out.println(number);

   }
}

package cinema;

import java.util.Objects;
import java.util.Scanner;

public class Cinema {

    public static String[][] cinema;
    public static int seatsPerRow, rows, totalSeats, ticketPrice_br, ticketPrice_fr, soldTickets, availableTickets, totalIncome, currentIncome, maxRows, maxSeats;
    public static float percentageSold;
    public static Scanner input;

    public static void main(String[] args) {
        soldTickets = 0;

        input = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        rows = input.nextInt();
        System.out.println("Enter the number of seats in each row:");

        seatsPerRow = input.nextInt();
        totalSeats = rows * seatsPerRow;
        availableTickets = totalSeats;

        if (totalSeats < 60) {
            ticketPrice_br = ticketPrice_fr = 10;
            totalIncome = totalSeats * ticketPrice_br;

        } else {
            ticketPrice_fr = 10;
            ticketPrice_br = 8;

            if (rows % 2 != 0){
                int totalSeatsFrontRow = (rows/2) * seatsPerRow;
                int totalSeatsBackRow = ((rows/2)+1) * seatsPerRow;
                totalIncome = (totalSeatsBackRow * ticketPrice_br) + (totalSeatsFrontRow * ticketPrice_fr);
            }
            else {
                int totalPrice_fr = ticketPrice_fr * (totalSeats/2);
                int totalPrice_br = ticketPrice_br * (totalSeats/2);
                totalIncome = totalPrice_fr + totalPrice_br;
            }

        }

        cinema = new String[rows][seatsPerRow];
        maxRows = rows;
        maxSeats = seatsPerRow;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                cinema[i][j] = " S";
            }
        }
        boolean running = true;

        while (running) {
            //Print main menu
            System.out.println("""
                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit""");

            int choice = input.nextInt();

            switch (choice) {
                case 1 -> showSeats();
                case 2 -> buyTickets();
                case 3 -> showStats();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    public static void showSeats() {
        System.out.println("Cinema:");
        System.out.print("  ");
        //print the row numbers:
        for (int i = 0; i < seatsPerRow; i++) {
            System.out.print((i + 1) + " ");
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < seatsPerRow; j++) {
                System.out.print(cinema[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void buyTickets() {
        boolean ticketPurchased = false;

        while (!ticketPurchased){
        System.out.println("Enter a row number:");
        int rowNr = input.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNr = input.nextInt();

        if ((rowNr > maxRows && seatNr > maxSeats) || (rowNr > maxRows && seatNr < maxSeats) || (rowNr < maxRows && seatNr > maxSeats))  {
            System.out.println("Wrong input!");

        } else{

        if (Objects.equals(cinema[rowNr - 1][seatNr - 1], " B")) {
            System.out.println("That ticket has already been purchased!");
        }

        else{
        cinema[rowNr-1][seatNr-1] = " B";

        System.out.print("Ticket price: $");

        if (totalSeats < 60) {System.out.print(ticketPrice_br); currentIncome += ticketPrice_br;}
        else {
            if (rowNr <= rows / 2) {System.out.print(ticketPrice_fr); currentIncome += ticketPrice_fr;}
            else {
                System.out.print(ticketPrice_br); currentIncome += ticketPrice_br;
            }
        }
        System.out.println("\n");

        soldTickets += 1;
        availableTickets -= 1;
        ticketPurchased = true;

    }}}}

    public static void showStats() {
        float seats = totalSeats;
        float sold = soldTickets;

        if (soldTickets == 0) {
            percentageSold = 0;}
        else {
            percentageSold = (sold / seats) * 100;
        }

        System.out.printf("Number of purchased tickets: %d%n", soldTickets);
        System.out.printf("Percentage: %.2f%%%n", percentageSold);
        System.out.printf("Current income: $%s%n", currentIncome);
        System.out.printf("Total income: $%s%n", totalIncome);

    }
}




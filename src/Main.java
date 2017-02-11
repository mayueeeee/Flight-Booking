import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner input = new Scanner(System.in);
        int select=0;
        while (input!=5)
        {
            //Show main menu
            System.out.println("Flight booking System");
            System.out.println("1: Show flights");
            System.out.println("2: Booking a flight");
            System.out.println("3: Edit booking");
            System.out.println("4: Cancel booking");
            System.out.println("5: Exit");
            System.out.print("Enter a choice: ");
            select=input.nextInt();
        }

    }
}

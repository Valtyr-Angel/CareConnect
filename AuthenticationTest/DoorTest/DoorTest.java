package AuthenticationTest.DoorTest;

import java.util.Scanner;

public class DoorTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Open door? (Y) yes / (N) No: ");
        String response = scanner.nextLine().trim().toUpperCase();

        if (response.equals("Y")) {
            System.out.println("Door unlocked");
        } else {
            System.out.println("Door remains locked");
        }

        scanner.close();
    }
}
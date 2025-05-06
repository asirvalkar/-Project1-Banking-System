import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankService bankService = new BankService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("==================Welcome To AVH Bank=====================");

        while (true) {
            System.out.println("\n1. Create Account\n2. Login\n3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                bankService.createAccount(username,password);
            } else if (choice == 2) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                int userId = bankService.login(username, password);

                if (userId >0) {
                    System.out.println("Login successful!");

                    while (true) {
                        System.out.println("\n1. Check Balance\n2. Deposit\n3. Withdraw\n4. View Account Details\n5. Logout");
                        System.out.print("Choose an option: ");
                        int option = scanner.nextInt();

                        if (option == 1) {
                            bankService.checkBalance(userId);
                        } else if (option == 2) {
                            System.out.print("Enter amount: ");
                            double amount = scanner.nextDouble();
                            bankService.deposit(userId, amount);
                        } else if (option == 3) {
                            System.out.print("Enter amount: ");
                            double amount = scanner.nextDouble();
                            bankService.withdraw(userId, amount);
                        } else if (option == 4) {
                            bankService.viewAccountDetails(userId);
                        } else if (option == 5) {
                            System.out.println("Logged out!");
                            break;
                        }
                    }
                } else {
                    System.out.println("Login failed!......Please try Again");
                }
            } else if (choice == 3) {
                System.out.println("Thank you for checking my project");
                break;
            }

        }
    }
}

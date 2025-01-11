import java.util.ArrayList;
import java.util.Scanner;

public class ATMInterface {
    private static double balance = 1000.00;
    private static ArrayList<String> transactionHistory = new ArrayList<>();
    private static String name;
    private static String userId;
    private static String password;

    
    private static void checkBalance() {
        System.out.println("\nCurrent available balance in the bank account : " + balance);
    }

    private static void showTransactionHistory() {
        System.out.println("\nTransaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    private static void withdraw(Scanner sc) {
        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful! Current balance is now: " + balance);
            transactionHistory.add("Withdrawn: " + amount);
        }
    }

    private static void deposit(Scanner sc) {
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        balance += amount;
        System.out.println("Deposit successful! Current balance is now: " + balance);
        transactionHistory.add("Deposited: " + amount);
    }

    private static void transfer(Scanner sc) {
        sc.nextLine(); 
        System.out.println("Enter the receiver's name:");
        String receiverName = sc.nextLine();
        System.out.print("Enter receiver's account ID: ");
        String receiver = sc.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = sc.nextDouble();

        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Transfer successful to " + receiverName + " (" + receiver + "). Current balance: " + balance);
            transactionHistory.add("Transferred: " + amount + " to " + receiverName + " (" + receiver + ")");
        }
    }

    
    private static void register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------------------------");
        System.out.println("Enter your name:");
        name = sc.nextLine();
        System.out.println("Enter user ID:");
        userId = sc.nextLine();
        System.out.println("Enter password:");
        password = sc.nextLine();
        System.out.println("-------------------------------------------");
        System.out.println("          REGISTRATION SUCCESSFUL!");
        System.out.println("-------------------------------------------");

        while (true) {
            display(name);
            int choice = sc.nextInt();
            if (choice == 1) {
                login(userId, password);
                break;
            } else if (choice == 2) {
                System.out.println("Exit");
                System.out.println("Exiting the ATM system. Thank you for visiting!");
                System.exit(0);
            } else {
                System.out.println("Wrong choice! Enter again!");
            }
        }
    
    }

    private static void display(String name) {
        System.out.println("WELCOME, " + name);
        System.out.println("1. ATM Login  ");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void login(String user, String pass) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter User ID:");
        String enteredUserId = sc.nextLine();
        System.out.println("Enter Password:");
        String enteredPassword = sc.nextLine();

        if (enteredUserId.equals(user) && enteredPassword.equals(pass)) {
            System.out.println("\n-------------Login successful!-------------");
            atmMenu();
        } else {
            System.out.println("Invalid User ID or Password. Access denied.");
        }
    }

    
    private static void atmMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n-------------------------------------------");
        System.out.println("      WELCOME " + name + "! TO OUR ATM SYSTEM");
        System.out.println("-------------------------------------------");
        int choice;

        do {
            System.out.println("\nATM Functionalities:");
            System.out.println("1. Check Balance");
            System.out.println("2. Transaction History");
            System.out.println("3. Withdraw");
            System.out.println("4. Deposit");
            System.out.println("5. Transfer");
            System.out.println("6. Quit");
            System.out.print("\nEnter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    showTransactionHistory();
                    break;
                case 3:
                    withdraw(scanner);
                    break;
                case 4:
                    deposit(scanner);
                    break;
                case 5:
                    transfer(scanner);
                    break;
                case 6:
                	System.out.println("\n-------------------------------------------");
                    System.out.println(" Thank you for using the ATM. Goodbye!! :)");
                    System.out.println("-------------------------------------------");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\033[H\033[2J"); // Clear the console
        System.out.println("**********WELCOME TO ATM INTERFACE**********");
        System.out.println("\nPlease choose one of the following options:");
        System.out.println("1. Register");
        System.out.println("2. Exit");
        System.out.println("Enter your choice:");
        int choice = scanner.nextInt();

        if (choice == 1) {
            register();
        } else if (choice == 2) {
            System.exit(0);
        } else {
            System.out.println("Select Correctly!!");
            main(args);
        }
    }
}

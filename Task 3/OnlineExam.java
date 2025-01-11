
import java.util.Scanner;

public class OnlineExam {
    private String username;
    private String password;
    private boolean isLoggedIn;
    private int timeRemaining;
    private int questionCount;
    private int[] userAnswers;
    private int[] correctAnswers;

    public OnlineExam(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("\n---------------------------------------------------");
        System.out.println("      You have been successfully registered!!");
        System.out.println("---------------------------------------------------");
        this.isLoggedIn = false;
        this.timeRemaining = 10; // in minutes
        this.questionCount = 10;
        this.userAnswers = new int[questionCount];
        this.correctAnswers = new int[questionCount];
        for (int i = 0; i < questionCount; i++) {
            correctAnswers[i] = (int) Math.round(Math.random());
        }
    }

    public boolean login() {
        System.out.println("\nLog in to participate ");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String inputUsername = scanner.nextLine();
        System.out.print("Password: ");
        String inputPassword = scanner.nextLine();
        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            isLoggedIn = true;
            System.out.println("\nLogin successful. Good luck with your exam! :)");
            return true;
        } else {
            System.out.println("\nLogin failed. Please try again!");
            return false;
        }
    }

    public void logout() {
        isLoggedIn = false;
        System.out.println("\n********* Logout successful. See you soon! *********");
    }

    private void updateProfile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nUpdate Profile:");
        System.out.print("Enter new username: ");
        username = scanner.nextLine();
        System.out.println("\n---------------------------------------------------");
        System.out.println("\nYour Name has been successfully updated to " + username);
        System.out.println("---------------------------------------------------");

        System.out.println("\nUpdate Password:");
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();
        if (validatePassword(currentPassword)) {
            System.out.print("Enter new password: ");
            password = scanner.nextLine();
            System.out.println("\n---------------------------------------------------");
            System.out.println("\n  Your Password has been successfully updated!");
            System.out.println("---------------------------------------------------");
        } else {
            System.out.println("Invalid current password.");
        }
    }

    private boolean validatePassword(String inputPassword) {
        return password.equals(inputPassword);
    }

    public void startExam() {
        if (!isLoggedIn) {
            System.out.println("Please login first.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n******************** Exam Time ********************");
        System.out.println("You have " + timeRemaining + " minutes to complete the exam.");
        for (int i = 0; i < questionCount; i++) {
            System.out.println("\nQuestion " + (i + 1) + ":");
            System.out.println("1. Option 1");
            System.out.println("2. Option 2");
            System.out.println("3. Option 3");
            System.out.println("4. Option 4");
            System.out.print("Enter your answer(1 to 4): ");
            int answer = scanner.nextInt();
            userAnswers[i] = answer;
        }

        System.out.println("\nWould you like to submit? \n1: Yes \n2: No");
        int n = scanner.nextInt();
        if (n == 1) {
            submitExam();
        } else {
            System.out.println("Time will auto-submit exam when time is up.");
            try {
                Thread.sleep(timeRemaining * 5 * 1000); // Simulates time passing
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            submitExam();
        }
    }

    public void submitExam() {
        if (!isLoggedIn) {
            System.out.println("Please login first.");
            return;
        }
        int score = 0;
        for (int i = 0; i < questionCount; i++) {
            if (userAnswers[i] == correctAnswers[i]) {
                score++;
            }
        }
        System.out.println("\n---------------------------------------------------");
        System.out.println("Your score is " + score + " out of " + questionCount + ".");
        System.out.println("---------------------------------------------------");
    }

    public static void main(String[] args) {
        System.out.println("*************** Welcome to the Online Exam System ***************");
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter username: ");
        String uName = scanner.nextLine();
        System.out.print("Enter password: ");
        String pWord = scanner.nextLine();

        OnlineExam examSystem = new OnlineExam(uName, pWord);
        if (!examSystem.login()) {
            System.out.println("Exiting the system.");
            return;
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\n******************** Main Menu ********************");
            System.out.println("1) Update Profile and Password");
            System.out.println("2) Start the Exam");
            System.out.println("3) Logout");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    examSystem.updateProfile();
                    break;
                case 2:
                    examSystem.startExam();
                    break;
                case 3:
                    examSystem.logout();
                    System.out.println("\nThank you for using Online Exam System. Wishing you all the best!");
                    exit = true;
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }
}


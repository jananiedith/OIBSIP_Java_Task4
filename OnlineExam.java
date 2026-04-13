import java.util.Scanner;

public class OnlineExam {

    static Scanner sc = new Scanner(System.in);

    static String username = "edith";
    static String password = "2212";

    public static void main(String[] args) {

        while (true) {

            if (login()) {
                menu();
            } else {
                System.out.println("Invalid Login! Try again.\n");
            }
        }
    }

    // LOGIN
    static boolean login() {

        System.out.println("===== ONLINE EXAM LOGIN =====");

        System.out.print("Enter Username: ");
        String u = sc.nextLine();

        System.out.print("Enter Password: ");
        String p = sc.nextLine();

        return u.equals(username) && p.equals(password);
    }

    // MENU
    static void menu() {
        int choice;

        do {
            System.out.println("\n1. Update Profile & Password");
            System.out.println("2. Start Exam");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    updateProfile();
                    return; // force re-login
                case 2:
                    startExam();
                    break;
                case 3:
                    System.out.println("Logged out successfully!");
                    return;
                default:
                    System.out.println("Invalid choice");
            }

        } while (true);
    }

    // UPDATE PROFILE
    static void updateProfile() {

        System.out.println("\n--- Update Profile ---");

        System.out.print("Enter new Username: ");
        username = sc.nextLine();

        System.out.print("Enter new Password: ");
        password = sc.nextLine();

        System.out.println("Profile updated successfully!");
        System.out.println("Please login again with new credentials.\n");
    }

    // EXAM WITH AUTO SUBMIT TIMER
    static void startExam() {

        int score = 0;

        System.out.println("\nExam Started!");
        System.out.println("You have 40 seconds...");

        // TIMER THREAD
        Thread timer = new Thread(() -> {
            try {
                Thread.sleep(40000);
                System.out.println("\nTime is up! Auto submitting...");
                System.exit(0);
            } catch (InterruptedException e) {
                return;
            }
        });

        timer.start();

        // QUESTIONS
        System.out.println("\nQ1: Java is?");
        System.out.println("1. Language  2. OS  3. Hardware  4. Browser");
        int ans1 = sc.nextInt();
        if (ans1 == 1) score++;

        System.out.println("\nQ2: 5 + 5 = ?");
        System.out.println("1. 5  2. 10  3. 15  4. 20");
        int ans2 = sc.nextInt();
        if (ans2 == 2) score++;

        System.out.println("\nQ3: CPU stands for?");
        System.out.println("1. Central Process Unit");
        System.out.println("2. Central Processing Unit");
        System.out.println("3. Computer Unit");
        System.out.println("4. None");
        int ans3 = sc.nextInt();
        if (ans3 == 2) score++;

        System.out.println("\nQ4: Which is not a programming language?");
        System.out.println("1. Java  2. Python  3. HTML  4. C++");
        int ans4 = sc.nextInt();
        if (ans4 == 3) score++;

        System.out.println("\nQ5: Which device is used for input?");
        System.out.println("1. Monitor  2. Keyboard  3. Printer  4. Speaker");
        int ans5 = sc.nextInt();
        if (ans5 == 2) score++;

        // STOP TIMER IF FINISHED EARLY
        timer.interrupt();

        System.out.println("\nExam Finished!");
        System.out.println("Your Score: " + score + "/5");

        sc.nextLine(); // clear buffer
    }
}
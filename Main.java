import java.util.Scanner;

public class Main {

   static Scanner sc = new Scanner(System.in);

   // Each account uses the same number (index) in every array
   static String[] names = new String[100];
   static String[] emails = new String[100];
   static String[] usernames = new String[100];
   static String[] passwords = new String[100];
   static double[] balances = new double[100];
   static int count = 0;

   public static void main(String[] args) {
      System.out.println("===== SIMPLE BANKING SYSTEM =====");

      int choice = 0;
      while (choice != 3) {
         System.out.println();
         System.out.println("[1] Login");
         System.out.println("[2] Sign Up");
         System.out.println("[3] Exit");
         System.out.print("Enter: ");
         choice = sc.nextInt();
         sc.nextLine();

         if (choice == 1) {
            login();
         } else if (choice == 2) {
            signUp();
         } else if (choice == 3) {
            System.out.println("Thank you for banking with us. Goodbye!");
         } else {
            System.out.println("Invalid choice. Try again.");
         }
      }
   }

   public static void signUp() {
      System.out.println();
      System.out.println("===== SIGN UP =====");

      System.out.print("Enter your name: ");
      String name = sc.nextLine();

      System.out.print("Enter your email: ");
      String email = sc.nextLine();

      System.out.print("Choose a username: ");
      String username = sc.nextLine();

      // check if the username is already used
      for (int i = 0; i < count; i++) {
         if (usernames[i].equals(username)) {
            System.out.println("Username already taken.");
            return;
         }
      }

      System.out.print("Choose a password: ");
      String password = sc.nextLine();

      names[count] = name;
      emails[count] = email;
      usernames[count] = username;
      passwords[count] = password;
      balances[count] = 0;
      count++;

      System.out.println("Sign up successful! You can now log in.");
   }

   public static void login() {
      System.out.println();
      System.out.println("===== LOGIN =====");

      System.out.print("Enter username: ");
      String username = sc.nextLine();

      System.out.print("Enter password: ");
      String password = sc.nextLine();

      for (int i = 0; i < count; i++) {
         if (usernames[i].equals(username) && passwords[i].equals(password)) {
            System.out.println("Login successful! Welcome, " + names[i] + ".");
            bankMenu(i);
            return;
         }
      }

      System.out.println("Invalid username or password.");
   }

   public static void bankMenu(int i) {
      int choice = 0;
      while (choice != 5) {
         System.out.println();
         System.out.println("===== BANK MENU =====");
         System.out.println("[1] Check Balance");
         System.out.println("[2] Deposit");
         System.out.println("[3] Withdraw");
         System.out.println("[4] Transfer");
         System.out.println("[5] Logout");
         System.out.print("Enter: ");
         choice = sc.nextInt();
         sc.nextLine();

         if (choice == 1) {
            System.out.println("Your balance is: " + balances[i]);
         } else if (choice == 2) {
            deposit(i);
         } else if (choice == 3) {
            withdraw(i);
         } else if (choice == 4) {
            transfer(i);
         } else if (choice == 5) {
            System.out.println("Logged out.");
         } else {
            System.out.println("Invalid choice. Try again.");
         }
      }
   }

   public static void deposit(int i) {
      System.out.print("Enter amount to deposit: ");
      double amount = sc.nextDouble();
      sc.nextLine();

      if (amount <= 0) {
         System.out.println("Invalid amount.");
      } else {
         balances[i] = balances[i] + amount;
         System.out.println("Deposit successful. New balance: " + balances[i]);
      }
   }

   public static void withdraw(int i) {
      System.out.print("Enter amount to withdraw: ");
      double amount = sc.nextDouble();
      sc.nextLine();

      if (amount <= 0) {
         System.out.println("Invalid amount.");
      } else if (amount > balances[i]) {
         System.out.println("Not enough balance.");
      } else {
         balances[i] = balances[i] - amount;
         System.out.println("Withdraw successful. New balance: " + balances[i]);
      }
   }

   public static void transfer(int i) {
      System.out.print("Enter the username to transfer to: ");
      String target = sc.nextLine();

      // find the other account
      int t = -1;
      for (int j = 0; j < count; j++) {
         if (usernames[j].equals(target)) {
            t = j;
         }
      }

      if (t == -1) {
         System.out.println("Account not found.");
         return;
      }
      if (t == i) {
         System.out.println("You cannot transfer to yourself.");
         return;
      }

      System.out.print("Enter amount to transfer: ");
      double amount = sc.nextDouble();
      sc.nextLine();

      if (amount <= 0) {
         System.out.println("Invalid amount.");
      } else if (amount > balances[i]) {
         System.out.println("Not enough balance.");
      } else {
         balances[i] = balances[i] - amount;
         balances[t] = balances[t] + amount;
         System.out.println("Transfer successful. New balance: " + balances[i]);
      }
   }
}

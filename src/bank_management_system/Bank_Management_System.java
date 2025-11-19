package bank_management_system;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank_Management_System {
     private static Map<String, Account> accounts = new HashMap<>();
     private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
      

        boolean i = true;

        while (i) {
            System.out.println("\n--- Welcome to the Bank ---");
            System.out.println("1. Open Bank Account");
            System.out.println("2. View Account Details");
            System.out.println("3. Check Balance");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Deposit Money");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    openAccount();
                    break;
                case 2:
                    viewAccountDetails();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    withdrawMoney();
                    break;
                case 5:
                    depositMoney();
                    break;
                case 6:
                    i = false;
                    System.out.println("Thank you for banking with us!");
                    break;
                case 7:
                showAllAccounts();
                break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        sc.close();
    }
    
    private static void openAccount(){
        
        System.out.println("\n--- Open New Account ---");
        //Taking Name
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();
        
        //taking money balance minimum 100tk
        System.out.print("Enter Initial Deposit (Minimum 100tk): ");
        double initialDeposit;
        try {
            initialDeposit = sc.nextDouble();
            sc.nextLine();
            if (initialDeposit < 100) {
                System.out.println("Initial deposit must be at least 100tk.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid deposit amount.");
            sc.nextLine();
            return;
        }
        
        //taking account type
        System.out.print("Select Account Type (1 for Savings, 2 for Current): ");
        int typeChoice;
        try {
            typeChoice = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid type choice.");
            sc.nextLine();
            return;
        }
        
        // adding account number
        String acNum = generateAccountNumber();
        
        //taking pin number
        String pinNum;

        while (true) {
            System.out.print("Enter 5-digit PIN: ");
            try {
                pinNum = sc.nextLine();

                if (pinNum.length() == 5) {
                    break;
                } else {
                    System.out.println("PIN must be 5 digits!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Only numbers allowed.");
                sc.nextLine();
            }
        }
        
        if (typeChoice == 1) {
          Savings_Account account = new Savings_Account();
            account.processAccount(name, acNum, pinNum, initialDeposit);
            accounts.put(acNum, account);

        } else if (typeChoice == 2) {
            Current_Account account = new Current_Account();
            account.processAccount(name, acNum, pinNum, initialDeposit);
            accounts.put(acNum, account);
        } else {
            System.out.println("Invalid account type selection.");
            return;
        }
    }
    
    private static String generateAccountNumber() {
    Random rand = new Random();
    long acc = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
    return Long.toString(acc);
    }
    
    private static Account findAccount() {
    System.out.print("Enter Account Number: ");
    String acNum;

    try {
        acNum = sc.nextLine().trim();
        if (acNum.isEmpty()) {
            System.out.println("Invalid input!");
            return null;
        }
    } catch (Exception e) {
        System.out.println("Error reading account number.");
        return null;
    }

    Account account = accounts.get(acNum);

    if (account == null) {
        System.out.println("Account number " + acNum + " not found.");
        return null;
    }
    
    
    System.out.print("Enter PIN: ");
    String inputPin = sc.nextLine();

    if (!account.verifyPin(inputPin)) {
        System.out.println("Incorrect PIN!");
        return null;
    }

    return account;
}

    
    
    private static void viewAccountDetails() {
    Account account = findAccount();
    if (account != null) {
        account.viewDetails();
    }
    }

    private static void checkBalance() {
    Account account = findAccount();
    if (account != null) {
        account.checkBalance();
    }
}
    private static void withdrawMoney() {
    Account account = findAccount();
    if (account != null) {
        System.out.print("Enter amount to withdraw: ");
        double amount;

        try {
            amount = sc.nextDouble();
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter a valid number.");
            sc.nextLine();
            return;
        }

        sc.nextLine();
        account.withdraw(amount);
    }
}
    
    private static void depositMoney() {
    Account account = findAccount();
    if (account != null) {
        System.out.print("Enter amount to deposit: ");
        double amount;

        try {
            amount = sc.nextDouble();
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter a valid number.");
            sc.nextLine();
            return;
        }

        sc.nextLine();
        account.deposit(amount);
    }
}

    private static void showAllAccounts() {
    System.out.print("Enter Admin PIN: ");
    String adminPin = sc.nextLine();

    String correctAdminPin = "99999";

    if (!adminPin.equals(correctAdminPin)) {
        System.out.println("Incorrect Admin PIN! Access Denied.");
        return;
    }

    if (accounts.isEmpty()) {
        System.out.println("No accounts found.");
        return;
    }

    System.out.println("\n--- All Bank Accounts ---");
    for (Map.Entry<String, Account> entry : accounts.entrySet()) {
        Account acc = entry.getValue();
        System.out.println("---------------------------");
        System.out.println("Name: " + acc.fullName);
        System.out.println("Account No: " + acc.acNum);
        System.out.println("Type: " + acc.acType);
        System.out.println("Balance: " + acc.balance);
    }
    System.out.println("---------------------------");
}



    

} 

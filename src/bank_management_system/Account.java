
package bank_management_system;
import java.time.Duration;
import java.time.LocalDateTime;

public class Account {
    String fullName;
    String acType;
    String acNum;
    String pinNum;
    double balance;
    LocalDateTime creationTime; 
    
    public void processAccount(String fullName,String acType,String acNum,String pinNum,double balance){
        this.fullName = fullName;
        this.acType = acType;
        this.acNum = acNum;
        this.pinNum = pinNum;
        this.balance = balance;
        this.creationTime = LocalDateTime.now();
        System.out.println("Account Created Successfully");
        System.out.println("Account Number: " + acNum);
        System.out.println("Account Type: " + acType);
        System.out.printf("Balance:", balance);
    }
    
    public boolean verifyPin(String inputPin) {
    return pinNum.equals(inputPin);
   }

    
    public void checkBalance(){
        System.out.println("Current Balance:" + balance);
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit Successfull");
            checkBalance();
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }
    
    public void withdraw(double amount) {
        
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal Successfull");
            checkBalance();
        } else if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else {
             System.out.println("Insufficient funds or withdrawal limit exceeded.");
        }
    }
    
    public void viewDetails() {
        System.out.println("Account Details");
        System.out.println("Name: " + fullName);
        System.out.println("Account Number: " + acNum);
        System.out.println("Account Type: " + acType);
        checkBalance();
    }
    
    
}

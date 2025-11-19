
package bank_management_system;
import java.time.Duration;
import java.time.LocalDateTime;


public class Savings_Account extends Account {
    private static final double Yearly_Rate = 0.05;
    private static final double Monthly_Rate = Yearly_Rate / 12;
    
    //method overloading
    public void processAccount(String fullName,String acNum,String pinNum,double balance) {
        super.processAccount(fullName,"Savings",acNum,pinNum, balance);
    }
    
    public void computeAndDepositInterest() {
        double interest =0;
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(creationTime, now);
        long totalDays = duration.toDays();
        long monthsPassed = totalDays / 30;
        //long monthsPassed = 5;
        
         for (int i = 1; i <= monthsPassed; i++) {
            double oldBalance = balance;
            balance = balance * (1 + Monthly_Rate);
            interest += (balance - oldBalance);
        }
        creationTime = now;
        //System.out.println("Interest:"+ interest);
        //checkBalance();
      
    }
    
    @Override
    public void checkBalance(){
        computeAndDepositInterest();
        System.out.println("Current Balance:" + balance);
    }
    
}

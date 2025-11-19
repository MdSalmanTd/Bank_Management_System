
package bank_management_system;

public class Current_Account extends Account{
    private static final double MinBalance = 100.0;
    private static final double Penalty = 50.0;
    
    public void processAccount(String fullName,String acNum,String pinNum,double balance) {
        super.processAccount(fullName,"Current",acNum,pinNum, balance);
    }
    
    public void checkMinBalanceAndImposePenalty() {
        if (balance < MinBalance) {
            System.out.println("Balance is below the minimum required");
            balance -= Penalty;
            System.out.println("Penalty Charge of" + Penalty + "imposed");
        }
    }
    
    @Override
    public void withdraw(double amount) {
        
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal Successfull");
            checkMinBalanceAndImposePenalty();
            checkBalance();
        } else if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else {
             System.out.println("Insufficient funds or withdrawal limit exceeded.");
        }
    }
}

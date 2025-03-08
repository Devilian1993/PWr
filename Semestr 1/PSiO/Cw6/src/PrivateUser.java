import java.util.ArrayList;

public class PrivateUser extends User {

    ArrayList<BankAccount> bankAccounts;

    public PrivateUser(String first_name, String last_name) {
        super(first_name, last_name);
        bankAccounts = new ArrayList<BankAccount>();
    }

    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void createNewBankAccount(ArrayList<BankAccount> bankAccounts) {
        this.bankAccounts.add(new BankAccount("123123"));
    }
}

public class CustomerServiceEmployee extends CompanyUser {

    PrivateUser current_user;

    public CustomerServiceEmployee(String first_name, String last_name, double salary) {
        super(first_name, last_name, salary);
    }

    public void setCurrent_user(PrivateUser current_user) {
        this.current_user = current_user;
    }

    public PrivateUser getCurrent_user() {
        return current_user;
    }

    public void UserCreateBankAccount() {
        if (current_user != null) {
            current_user.getBankAccounts().add(new BankAccount("321321"));
        } else {
            System.out.println("No user found");
        }
    }

    public void UserDeleteBankAccount() {
        if (current_user != null) {
            current_user.getBankAccounts().removeLast();
        } else {
            System.out.println("No user found");
        }
    }

    public void UserDeleteAccount() {
        if (current_user != null) {
            current_user.deleteUser();
        } else {
            System.out.println("No user found");
        }
        current_user = null;
    }
}

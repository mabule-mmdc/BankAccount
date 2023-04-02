public class BankAccount {
    private int accountNumber;
    private String accountName;
    public BankAccount(AccountHolder accountHolder, int accountNumber) {
        this.accountName = accountHolder.getFirstName() + ' ' + accountHolder.getLastName();
        this.accountNumber = accountNumber;
    }

    public float getCurrentBalance() {
        System.out.println("TO-DO: implement get current balance");
        return 0;
    }
}

public class AccountHolder {
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private String firstName;
    private String lastName;

    public AccountHolder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

package week3;

public class Employee {

    long id = -1;
    String firstName = "";
    String lastName = "";
    String email = "";
    long payrollID = -1;
    String phoneNumber = "";

    public Employee() {}

    public Employee setId(long id) {
        this.id = id;
        return this;
    }

    public Employee setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Employee setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}

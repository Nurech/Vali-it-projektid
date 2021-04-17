package ee.bcs.valiit.tasks;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class AccountDTO {

    //JPA/Hibernate
    //ModelMapper
    //Jackson?
    // Formats output date when this DTO is passed through JSON
    @JsonFormat(pattern = "dd/MM/yyyy")
    // Allows dd/MM/yyyy date to be passed into GET request in JSON
    @DateTimeFormat(pattern = "dd/MM/yyyy")

    private String accountnumber;
    private String accountnumber2;
    private double balance;
    private boolean locked;
    private Long id;
    private String address;
    private String phone;
    private String firstname;
    private String lastname;
    private String password;
    private String email;

    public AccountDTO(String accountnumber, String accountnumber2, double balance, boolean locked, Long id, String address, String phone, String firstname, String lastname, String password, String email) {
        this.accountnumber = accountnumber;
        this.accountnumber2 = accountnumber2;
        this.balance = balance;
        this.locked = locked;
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getAccountnumber2() {
        return accountnumber2;
    }

    public void setAccountnumber2(String accountnumber2) {
        this.accountnumber2 = accountnumber2;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}





package bank.puthusu.model;

public class Customer {
    private int customerId;
    private String name;
    private String email;
    private long mobileNo;


    public Customer(String name, String email, long mobileNo) {
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
    }


    public Customer(int customerId, String name, String email, long mobileNo) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo=" + mobileNo +
                '}';
    }
}

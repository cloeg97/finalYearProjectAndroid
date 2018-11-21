package project.finalyearapp.Model;

public class User {
    private String FirstName;
    private String LastName;
    private String Password;
    //private String UserType;
    //private String CampanyName;
    //private String Address;
    //private String BankDetails;

    public User() {
    }

    public User(String firstName, String lastName, String password) {
        FirstName = firstName;
        LastName = lastName;
        Password = password;
        //UserType = userType;
        //CampanyName = campanyName;
        //Address = address;
        //BankDetails = bankDetails;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    /*
    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getCampanyName() {
        return CampanyName;
    }

    public void setCampanyName(String campanyName) {
        CampanyName = campanyName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBankDetails() {
        return BankDetails;
    }

    public void setBankDetails(String bankDetails) {
        BankDetails = bankDetails;
    }
    */
}
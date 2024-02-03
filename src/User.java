public class User {

    private String userName;
    private String email;
    private String password;
    private String dateOfBirth;


    public User(String userName, String email, String password, String dateOfBirth) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}

package manytomany.Persons;

public class LoginRequest {
    private String SignUpUsername;
    private String SignUpPassword;

    public LoginRequest(String signUpUsername, String signUpPassword) {
        this.SignUpUsername = signUpUsername;
        this.SignUpPassword = signUpPassword;
    }

    public String getSignUpUsername() {
        return SignUpUsername;
    }

    public void setSignUpUsername(String email) {
        this.SignUpUsername = email;
    }

    public String getSignUpPassword() {
        return SignUpPassword;
    }

    public void setSignUpPassword(String signUpPassword) {
        this.SignUpPassword = signUpPassword;
    }
}

package manytomany.Persons;
class PersonInfo {
    private String signUpName;
    private String signUpPassword;

    public PersonInfo(String signUpName, String signUpPassword) {
        this.signUpName = signUpName;
        this.signUpPassword = signUpPassword;
    }

    public String getSignUpName() {
        return signUpName;
    }

    public String getSignUpPassword() {
        return signUpPassword;
    }
}
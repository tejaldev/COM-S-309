package coms309.admins;

public class Credentials {
    private String fullName;
    private String userID;
    private String userName;
    private String password;

    public Credentials() {
    }

    public Credentials(String fullName, String userID, String userName, String password) {
        this.fullName = fullName;
        this.userID = userID;
        this.userName = userName;
        this.password = password;
    }

    public String getFullName() {

        return this.fullName;
    }

    public void setFullName(String fullName) {

        this.fullName = fullName;
    }

    public String getUserID() {

        return this.userID;
    }

    public void setUserID(String userID) {

        this.userID = userID;
    }

    public String getUserName() {

        return this.userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getPassword() {

        return this.password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    @Override
    public String toString() {
        return fullName + " "
                + userID + " "
                + userName + " "
                + password;
    }

}

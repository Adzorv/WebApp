package nl.dagobank.webapp.domain;

public class InlogCredentials {
    private String userName;
    private String password;

    public InlogCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public InlogCredentials() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "InlogCredentials{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

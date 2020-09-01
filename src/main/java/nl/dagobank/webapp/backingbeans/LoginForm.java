package nl.dagobank.webapp.backingbeans;

public class LoginForm {

    private String username;
    private String password;
    private String usernameError;
    private String passwordError;
    private String loginAttemptsError;

    public LoginForm( String username, String password, String usernameError, String passwordError ) {
        this.username = username;
        this.password = password;
        this.usernameError = usernameError;
        this.passwordError = passwordError;
    }

    public LoginForm( String username, String password ) {
        this(username, password, "", "");
    }

    public LoginForm() {
        this("", "", "", "");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError( String usernameError ) {
        this.usernameError = usernameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError( String passwordError ) {
        this.passwordError = passwordError;
    }

    public String getLoginAttemptsError() {
        return loginAttemptsError;
    }

    public void setLoginAttemptsError( String loginAttemptsError ) {
        this.loginAttemptsError = loginAttemptsError;
    }
}

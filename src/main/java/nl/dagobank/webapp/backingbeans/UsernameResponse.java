package nl.dagobank.webapp.backingbeans;

public class UsernameResponse {

    private String username;
    private String error;

    public UsernameResponse( String username ) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getError() {
        return error;
    }

    public void setError( String error ) {
        this.error = error;
    }
}

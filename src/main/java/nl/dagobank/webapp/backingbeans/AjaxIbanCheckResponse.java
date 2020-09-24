package nl.dagobank.webapp.backingbeans;

public class AjaxIbanCheckResponse {

    private String msg = "";
    private boolean ibanCorrect;

    public String getMsg() {
        return msg;
    }

    public void setMsg( String msg ) {
        this.msg = msg;
    }

    public boolean isIbanCorrect() {
        return ibanCorrect;
    }

    public void setIbanCorrect( boolean ibanCorrect ) {
        this.ibanCorrect = ibanCorrect;
    }
}

package it.reactive.torneoDemo.exception;

public class CustomException extends RuntimeException {


    private String codErr;
    private String messaggio;

    public CustomException(String codErr, String messaggio) {
        this.messaggio=messaggio;
        this.codErr = codErr;
    }

    public String getCodErr() {
        return codErr;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public void setCodErr(String codErr) {
        this.codErr = codErr;
    }

}

package it.reactive.torneoDemo.eccezioni;

public class CustomException extends RuntimeException {


  private String codErr;
  private String messaggio;

  public CustomException(String codErr, String messaggio) {
    super(messaggio);
    this.codErr = codErr;
  }

  public String getCodErr() {
    return codErr;
  }

  public void setCodErr(String codErr) {
    this.codErr = codErr;
  }

  public String getMessaggio() {
    return messaggio;
  }

  public void setMessaggio(String messaggio) {
    this.messaggio = messaggio;
  }
}

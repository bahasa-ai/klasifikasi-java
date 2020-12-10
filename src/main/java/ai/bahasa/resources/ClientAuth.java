package ai.bahasa.resources;

public class ClientAuth {

  Token auth;

  public ClientAuth(Token auth) { this.auth = auth; }

  public Token getAuth() { return auth; }

  public void setAuth(Token auth) { this.auth = auth; }
}

package ai.bahasa.resources;

public class ClientData {

  Token auth;

  Model model;
  public ClientData(Token auth, Model model) {
    this.auth = auth;
    this.model = model;
  }

  public Token getAuth() { return auth; }

  public void setAuth(Token auth) { this.auth = auth; }

  public Model getModel() { return model; }

  public void setModel(Model model) { this.model = model; }
}

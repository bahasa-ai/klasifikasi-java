package ai.bahasa.resources;

public class ClientData {

  Token auth;

  Model model;

  BuildParams buildParams;

  public ClientData(Token auth, Model model, BuildParams buildParams) {
    this.auth = auth;
    this.model = model;
    this.buildParams = buildParams;
  }

  public ClientData(Token auth, Model model) {
    this.auth = auth;
    this.model = model;
  }

  public BuildParams getBuildParams() { return buildParams; }

  public void setBuildParams(BuildParams buildParams) {
    this.buildParams = buildParams;
  }

  public Token getAuth() { return auth; }

  public void setAuth(Token auth) { this.auth = auth; }

  public Model getModel() { return model; }

  public void setModel(Model model) { this.model = model; }
}

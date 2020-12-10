package ai.bahasa.resources;

public enum RequestMethod {
  GET("GET"),
  POST("POST");

  private String text;

  RequestMethod(String text) { this.text = text; }

  public String getText() { return this.text; }
}
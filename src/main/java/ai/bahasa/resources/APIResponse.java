package ai.bahasa.resources;

public class APIResponse {

  int statusCode;

  String responseBody;

  public APIResponse(int statusCode, String responseBody) {
    this.statusCode = statusCode;
    this.responseBody = responseBody;
  }

  public int getStatusCode() { return statusCode; }

  public void setStatusCode(int statusCode) { this.statusCode = statusCode; }

  public String getResponseBody() { return responseBody; }

  public void setResponseBody(String responseBody) {
    this.responseBody = responseBody;
  }
}

package ai.bahasa.resources;

public class Token {

  String token;

  long expiredAfter;

  public Token(String token, long expiredAfter) {
    this.token = token;
    this.expiredAfter = expiredAfter;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public long getExpiredAfter() {
    return expiredAfter;
  }

  public void setExpiredAfter(long expiredAfter) {
    this.expiredAfter = expiredAfter;
  }
}

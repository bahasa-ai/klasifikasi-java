package ai.bahasa.resources;

import com.google.gson.annotations.SerializedName;

public class LogsResponse {

  @SerializedName("histories")
  Log[] logs;

  int length;

  public LogsResponse(Log[] logs, int length) {
    this.logs = logs;
    this.length = length;
  }

  public Log[] getLogs() {
    return logs;
  }

  public void setLogs(Log[] logs) {
    this.logs = logs;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }
}

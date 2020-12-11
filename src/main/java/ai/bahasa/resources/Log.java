package ai.bahasa.resources;

public class Log {

  int id;

  String query;

  TagScore[] modelResult;

  String createdAt;

  String updatedAt;

  public Log(int id, String query, TagScore[] modelResult, String createdAt, String updatedAt) {
    this.id = id;
    this.query = query;
    this.modelResult = modelResult;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public TagScore[] getModelResult() {
    return modelResult;
  }

  public void setModelResult(TagScore[] modelResult) {
    this.modelResult = modelResult;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
}

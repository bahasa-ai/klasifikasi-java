package ai.bahasa.resources;

import com.google.gson.annotations.SerializedName;

public class ClassifyResponse {

  @SerializedName("result")
  TagScore[] tagScores;

  public ClassifyResponse(TagScore[] tagScores) {
    this.tagScores = tagScores;
  }

  public TagScore[] getTagScores() {
    return tagScores;
  }

  public void setTagScores(TagScore[] tagScores) {
    this.tagScores = tagScores;
  }
}

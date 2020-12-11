package ai.bahasa.resources;

import com.google.gson.annotations.SerializedName;

public class TagScore {

  @SerializedName("label")
  String name;

  @SerializedName("desc_weight")
  Integer descriptionWeight;

  double score;

  public TagScore(String name, Integer descriptionWeight, double score) {
    this.name = name;
    this.descriptionWeight = descriptionWeight;
    this.score = score;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getDescriptionWeight() {
    return descriptionWeight;
  }

  public void setDescriptionWeight(Integer descriptionWeight) {
    this.descriptionWeight = descriptionWeight;
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }
}

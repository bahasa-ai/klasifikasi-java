package ai.bahasa.resources;

public class Tag {

  String name;

  String description;

  Integer descriptionWeight;

  public Tag(String name, String description, Integer descriptionWeight) {
    this.name = name;
    this.description = description;
    this.descriptionWeight = descriptionWeight;
  }

  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

  public String getDescription() { return description; }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getDescriptionWeight() { return descriptionWeight; }

  public void setDescriptionWeight(Integer descriptionWeight) {
    this.descriptionWeight = descriptionWeight;
  }
}

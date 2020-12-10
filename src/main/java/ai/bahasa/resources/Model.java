package ai.bahasa.resources;

public class Model {

  String name;

  String publicId;

  Tag[] tags;

  public Model(String name, String publicId, Tag[] tags) {
    this.name = name;
    this.publicId = publicId;
    this.tags = tags;
  }

  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

  public String getPublicId() { return publicId; }

  public void setPublicId(String publicId) { this.publicId = publicId; }

  public Tag[] getTags() { return tags; }

  public void setTags(Tag[] tags) { this.tags = tags; }
}

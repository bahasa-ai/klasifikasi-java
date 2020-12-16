# Klasifikasi for Java

Official [Klasifikasi](https://klasifikasi.com/) API Client Library

## Requirement
JDK 15 or later

## Installation

#### Gradle
Add these lines in your `build.gradle`
```
repositories {
    ...
    maven {
        url "https://dl.bintray.com/bahasa-ai/klasifikasi"
    }
}

dependencies {
    ...
    implementation 'ai.bahasa:klasifikasi-java:0.0.1'
}

```

#### Maven
Add these lines in your `pom.xml`
```xml
<dependency>
    <groupId>ai.bahasa</groupId>
    <artifactId>klasifikasi-java</artifactId>
    <version>0.0.1</version>
    <type>pom</type>
</dependency>


<repositories>
    <repository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>bintray-bahasa-ai-klasifikasi</id>
        <name>bintray</name>
        <url>https://dl.bintray.com/bahasa-ai/klasifikasi</url>
    </repository>
</repositories>
<pluginRepositories>
    <pluginRepository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>bintray-bahasa-ai-klasifikasi</id>
        <name>bintray-plugins</name>
        <url>https://dl.bintray.com/bahasa-ai/klasifikasi</url>
    </pluginRepository>
</pluginRepositories>
<id>bintray</id>

```

## Quick Start

You will need valid `clientId` & `clientSecret` of your model. You can get those
from credential section at your model page, which is both unique per model.

```java
Klasifikasi klasifikasi = Klasifikasi.build(new BuildParams[]{
  new BuildParams("client-id", "client-secret")
});

```
You can pass multiple `clientId` & `clientSecret` too

```java

Klasifikasi klasifikasi = Klasifikasi.build(new BuildParams[]{
  new BuildParams("client-id-1", "client-secret-1"),
  new BuildParams("client-id-2", "client-secret-2"),
});

```

## Classify
You will need you model `publicId` to start classifying with your model. You can get your model `publicId` from your model page, or you can get it from here :
```java

Map<String, ClientData> models = klasifikasi.getModels();
for (Map.Entry<String, ClientData> data : models.entrySet()) {
  System.out.println(data.getKey()); // publicId
}
```

Classifying example
```java
ClassifyResponse response = klasifikasi.classify("publicId", "Query");
TagScore[] tagScores = response.getTagScores();
for (TagScore data : tagScores) {
  System.out.printf("%s : %f\n", data.getName(), data.getScore());
  /*
    Example:
      Tag 1 : 0.52
      Tag 2 : 0.23
  */

}
```

## Logs
You can get your classifying logs based on your model `publicId`
```java
SimpleDateFormat formater = new SimpleDateFormat("d MMMM yyyy");
Date startedAt = formater.parse("10 December 2020");
Date endedAt = formater.parse("12 December 2020");
int skip = 0;
int take = 10;
LogsResponse response = instance.logs("publicId", startedAt, endedAt, skip, take);
Log[] logs = response.getLogs();
for (Log log : logs) {
  System.out.printf("Result: %s, %s\n",log.getQuery(), log.getCreatedAt());
  for (TagScore score : log.getModelResult()) {
    System.out.printf("-%s : %f\n", score.getName(), score.getScore());
  }
}
/*
Output :
  Result : your query, 2020-12-11T10:00:29+07:00
  - Tag 1 : 0.52
  - Tag 2 : 0.23
*/
```

## Error

All the function above will throw an Exception if something bad happen. Always run
each function inside `try` & `catch` block

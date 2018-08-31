guid-client
=============
GUID client for Java.

##Maven Repo
```xml
<dependencies>
	<dependency>
		<groupId>com.guthub.twbinfo</groupId>
		<artifactId>guid-client</artifactId>
		<version>master-SNAPSHOT</version>
	</dependency>
</dependencies>

<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```

##Quick Start
####GUID Client
```java
import static tw.edu.ym.guid.client.field.Name.NamePart.*;

PII pii = new PII.Builder(
  new Name(ImmutableMap.of(LAST_NAME, "李", FIRST_NAME, "大寶")), Sex.MALE,
  new Birthday(2015, 1, 31), new TWNationalId("A123456789")).build();

GuidClient gc = new GuidClient(new URI("<GUID_SERVER_URI_HERE>"),
                  "<USERNAME_HERE>", "<PASSWORD_HERE>", "<PREFIX_HERE>");

System.out.println(gc.create(pii));
```

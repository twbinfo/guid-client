guid-client
=============
GUID client for Java.

##Maven Repo
```xml
<dependencies>
	<dependency>
		<groupId>tw.edu.ym.guid</groupId>
		<artifactId>guid-client</artifactId>
		<version>1.3.2</version>
	</dependency>
</dependencies>

<repositories>
	<repository>
		<id>guid-client-mvn-repo</id>
		<url>https://raw.github.com/twbinfo/guid-client/mvn-repo/</url>
		<snapshots>
			<enabled>true</enabled>
			<updatePolicy>always</updatePolicy>
		</snapshots>
	</repository>
</repositories>
```

##Quick Start
####GUID Client
```java
PII pii = new PII.Builder(
  new Name(ImmutableMap.of(LAST_NAME, "李", FIRST_NAME, "大寶")), Sex.MALE,
  new Birthday(2015, 1, 31), new TWNationalId("A123456789")).build();

GuidClient gc = new GuidClient(new URI("<GUID_SERVER_URI_HERE>"),
                  "<USERNAME_HERE>", "<PASSWORD_HERE>", "<PREFIX_HERE>");

System.out.println(gc.create(pii));
```

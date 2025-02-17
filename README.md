# FilterLibrary
This Java library provides a set of filter classes that can be used to check if a resource (represented by a Map<String, String>) matches certain criteria.

## Building
Building requires a Java JDK and Apache Maven. The required Java version is found in the pom.xml as the maven.compiler.source property.
Use the following command to compile, test, and package the project:

```
mvn clean install
```

## Running Unit Tests
To just run unit test, use the following command:

```
mvn test
```

## Creating Filters
You can create various filters using the FilterFactory.

```java
import handsoncode.filter.*;

Map<String, String> resource = new HashMap<>();
resource.put("age", "25");
resource.put("status", "active");

Filter filter1 = FilterFactory.greaterThan("age", 20);
Filter filter2 = FilterFactory.equalsTo("status", "active");

System.out.println(filter1.matches(resource));  // true
System.out.println(filter2.matches(resource));  // true
```
You can also create a Filter based on a Json representation

```java
import handsoncode.filter.*;

Map<String, String> resource = new HashMap<>();
resource.put("age", "25");
resource.put("status", "active");

String filter1String = "{\"type\":\"GreaterThan\",\"property\":\"age\",\"value\":\"20\"}";

Filter filter1 = FilterFactory.fromString(filter1String);

System.out.println(filter1.matches(resource));  // true
```

## Expanding Library: adding new filters
To add a new filter type, follow these steps:
- Create a new class that implements the `Filter` interface. 
- Modify the `FilterFactory` class to include a method for creating the new filter type.
- Update the `FilterDeserializer` by adding a new case in the switch-case clause to handle the new filter.
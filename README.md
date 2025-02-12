# FilterLibrary
This Java library provides a set of filter classes that can be used to check if a resource (represented by a Map<String, String>) matches certain criteria.

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
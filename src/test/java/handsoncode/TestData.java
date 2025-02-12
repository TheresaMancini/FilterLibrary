package handsoncode;

import java.util.HashMap;
import java.util.Map;

public class TestData {

    private static final Map<String, String> userAge35 = new HashMap<>();
    private static final Map<String, String> userAge25 = new HashMap<>();
    private static final Map<String, String> userNullValues = new HashMap<>();

    static {
        userAge35.put("firstname", "Joe");
        userAge35.put("lastname", "Bloggs");
        userAge35.put("role", "administrator");
        userAge35.put("age", "35");
        userAge35.put("testNull", null);
        userAge35.put("testEmpty", "");
    }

    static {
        userAge25.put("firstname", "John");
        userAge25.put("lastname", "Doe");
        userAge25.put("role", "administrator");
        userAge25.put("age", "25");
        userAge25.put("height", "1.70");
    }

    static {
        userNullValues.put("firstname", null);
        userNullValues.put("lastname", null);
        userNullValues.put("role", null);
        userNullValues.put("age", null);
    }

    public static Map<String, String> getUserAge35() {
        return userAge35;
    }

    public static Map<String, String> getUserAge25() {
        return userAge25;
    }
    
    public static Map<String, String> getUserNull() {
        return userNullValues;
    }
}

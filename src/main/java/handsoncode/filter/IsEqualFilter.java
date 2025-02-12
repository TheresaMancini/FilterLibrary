package handsoncode.filter;

import java.util.Map;
import java.util.Objects;

/**
 * {@code IsEqualFilter} is a concrete implementation of the {@link Filter} interface
 * A filter that checks if a field's value is equal to a given value.
 * 
 * Example Usage:
 * <pre>{@code
 * Filter equalToFilter = new IsEqualFilter("status", "active");
 * boolean result = equalToFilter.matches(resource);  // Returns true if "status" equals "active"
 * }</pre>
 *
 */

public class IsEqualFilter implements Filter {

    private final String property;
    private final String value;


    /**
     * Constructs a {@code IsEqualFilter} that evaluates if a property value is equal to a given value
     * 
     * @param property The field name to check (must not be {@code null}).
     * @param value The value to compare the property's value to (must not be {@code null}).
     * @throws NullPointerException if {@code property} or {@code value} is {@code null}.
     */
    protected IsEqualFilter(String property, String value){
        this.property = Objects.requireNonNull(property, "property must not be null");
        this.value = Objects.requireNonNull(value, "value must not be null");
    }

     /**
     * Evaluates whether the given resource matches the filter {@code IsEqualFilter}.
     * Checks whether the value of the specified property in the given resource 
     * is equal to the provided value.
     * 
     * @param resource a {@link Map} containing the resource data, where the property is a {@link String} 
     *                 and the value is also a {@link String}. The map must contain the property to be checked.
     * 
     * @return {@code true} if the value of the property is equal to the value, 
     *         {@code false} if the value is different or if the property doesn't exist in the resource.
     *  
     */
    @Override
    public boolean matches(Map<String, String> resource) {
    
        return (resource.containsKey(property) && resource.get(property).equalsIgnoreCase(value));
    
    }

    /**
     * Returns a string representation of this {@code IsEqualFilter}, describing the type, property and value applied.
     * The string format is a JSON-like representation.
     * 
     * @return a string representation of the {@code IsEqualFilter}.
     */
    @Override
    public String toString(){
        return "{\"type\":\"IsEqualFilter\",\"filed\':" + property + "\",\"value:\""+ value +"\"}";
    }
    


}

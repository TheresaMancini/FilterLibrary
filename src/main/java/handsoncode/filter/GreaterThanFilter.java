package handsoncode.filter;

import java.util.Map;
import java.util.Objects;

import static handsoncode.utils.UtilsFilter.safeParseDouble;

/**
 * {@code GreaterThanFilter} is a concrete implementation of the {@link Filter} interface
 * A filter that checks if a field's value is greater than a given threshold.
 * 
 * Example Usage:
 * <pre>{@code
 * Filter ageFilter = new GreaterThanFilter("age", 18);
 * boolean result = ageFilter.matches(resource);  // Returns true if "age" is greater than 18
 * }</pre>
 *
 */
public class GreaterThanFilter implements Filter {

    private final String property;
    private final Double value;

    /**
     * Constructs a {@code GreaterThanFilter} that evaluates whether the property's value is greater than the threshold.
     * 
     * @param property The field name to check (must not be {@code null}).
     * @param value The value to compare the field's value against (must be a valid integer).
     * @throws NullPointerException if {@code property} is {@code null}.
     */
    protected GreaterThanFilter(String property, Double value){
        this.property = Objects.requireNonNull(property, "property must not be null");
        this.value = Objects.requireNonNull(value, "value must not be null");
    }

    /**
     * Evaluates whether the given resource matches the filter {@code GreaterThanFilter}.
     * Checks whether the value of the specified property in the given resource 
     * is greater than the predefined threshold value.
     * 
     * @param resource a {@link Map} containing the resource data, where the key is a {@link String} 
     *                 and the value is also a {@link String}. The map must contain the property to be checked.
     * 
     * @return {@code true} if the value of the property is greater than the threshold value, 
     *         {@code false} otherwise.
     * 
     * @throws IllegalArgumentException if the property value cannot be parsed into a valid {@code Double}.
     * 
     */
    @Override
    public boolean matches(Map<String, String> resource) {
        
        if (!resource.containsKey(property)){
            return false;
        }
        
        Double intValue  = safeParseDouble(resource.get(property));

        if (intValue == null){
            throw new IllegalArgumentException("Property '" + property + "' must be a valid integer, but found: '" + resource.get(property) + "'");
        }
        
        return intValue > value;
        
    }
    
    /**
     * Returns a string representation of this {@code GreaterThanFilter}, describing the type, property and value applied.
     * The string format is a JSON-like representation.
     * 
     * @return a string representation of the {@code GreaterThanFilter}.
     */
    @Override
    public String toString(){
        return "{\"type\":\"GreaterThan\",\"property\":\"" + property + "\",\"value\":\""+ value +"\"}";
    }
    
}

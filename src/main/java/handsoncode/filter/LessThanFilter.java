package handsoncode.filter;
import static handsoncode.utils.UtilsFilter.safeParseDouble;

import java.util.Map;
import java.util.Objects;

/**
 * {@code LessThanFilter} is an implementation of the {@link Filter} interface 
 * that checks if the value of a specified property in the resource is less than 
 * a predefined threshold value.
 * 
 * Example Usage:
 * <pre>{@code
 * Filter ageFilter = new LessThanFilter("age", 18);
 * boolean result = ageFilter.matches(resource);  // Returns true if "age" is less than 18
 * }</pre>
 */
public class LessThanFilter implements Filter {

    private final String property;
    private final Double value;

    /**
     * Constructs a {@code LessThanFilter} for the specified property and threshold value.
     * 
     * @param property the name of the property to check.
     * @param value the threshold value that the property value must be less than.
     * @throws NullPointerException if either {@code property} or {@code value} is {@code null}.
     */
    protected LessThanFilter(String property, Double value){
        this.property = Objects.requireNonNull(property, "property must not be null");
        this.value = Objects.requireNonNull(value, "value must not be null");
    }

    /**
     * Checks whether the value of the specified property in the resource is less than the threshold value.
     * The method will return {@code false} if the property is not found in the resource or if the property 
     * value cannot be parsed as a valid {@code Double}.
     * 
     * @param resource a {@link Map} representing the resource data, where the key is the property name 
     *                 (a {@link String}), and the value is the property value (a {@link String}).
     * @return {@code true} if the value of the specified property is less than the threshold value,
     *         {@code false} otherwise.
     * @throws IllegalArgumentException if the property value cannot be parsed as a valid {@code Double}.
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

        return intValue < value;

    }

    /**
     * Returns a string representation of this {@code LessThanFilter}, describing the type, property and value applied.
     * The string format is a JSON-like representation.
     * 
     * @return a string representation of the {@code LessThanFilter}.
     */
    @Override
    public String toString(){
        return "{\"type\":\"LessThan\",\"field\":\"" + property + "\",\"value\":\""+ value +"\"}";
    }

}

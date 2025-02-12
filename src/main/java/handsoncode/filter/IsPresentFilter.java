package handsoncode.filter;

import java.util.Map;
import java.util.Objects;

/**
 * {@code IsPresentFilter} is an implementation of the {@link Filter} interface 
 * that checks whether a specified property exists in the provided resource 
 * and if the property's value is not blank (non-null and not empty).
 *
 * Example Usage:
 * <pre>{@code
 * Filter presentFilter = new IsPresentFilter("username");
 * boolean result = presentFilter.matches(resource);  // Returns true if "username" is not null or empty
 * }</pre>
 *
 */

public class IsPresentFilter implements Filter {

    private final String property;

     /**
     * Constructs an {@code IsPresentFilter} for the specified property.
     * 
     * @param property the name of the property to check for presence and non-blank value.
     * @throws NullPointerException if {@code property} is {@code null}.
     */
    protected IsPresentFilter(String property){
        this.property = Objects.requireNonNull(property, "property must not be null");
    }

    /**
     * Checks whether the specified property is present in the resource and has a non-blank value.
     * A property is considered present if it exists in the {@code resource} and its value is not blank.
     * 
     * @param resource a {@link Map} representing the resource data, where the key is a {@link String} 
     *                 (the property name), and the value is a {@link String} (the property value).
     * @return {@code true} if the property exists in the resource and its value is non-blank,
     *         {@code false} otherwise.
     */
    @Override
    public boolean matches(Map<String, String> resource) {
        return resource.containsKey(property) && !resource.get(property).isBlank() && resource.get(property) != null ;
    }

    /**
     * Returns a string representation of this {@code IsPresentFilter}, describing the type, property and value applied.
     * The string format is a JSON-like representation.
     * 
     * @return a string representation of the {@code IsPresentFilter}.
     */
    @Override
    public String toString(){
        return "{\"type\":\"isPresent\",\"field\":\"" +   property + "\"}";
    }

}

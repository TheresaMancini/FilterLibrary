package handsoncode.filter;

import java.util.Map;

/**
 * Represents a filter that evaluates whether a given resource matches certain criteria.
 * <p>
 * Implementations of this interface define different types of filtering logic, such as
 * comparisons (e.g., greater than, less than, equals) or logical operations (AND, OR).
 * </p>
 * 
 * <p>Filters are used to evaluate a resource, represented as a {@code Map<String, String>},
 * and determine if it satisfies the filter's conditions.</p>
 *
 * Example Usage:
 * <pre>{@code
 * Filter filter = new GreaterThanFilter("age", 18);
 * boolean result = filter.matches(resource); // Evaluates if "age" > 18 in the given resource
 * }</pre>
 *
 */

public interface Filter 
{
     /**
     * Checks if the filter matches the given resource.
     *
     * @param resource A map {@code Map<String,String>} representing the resource to check (never {@code null}).
     * @return {@code true} if the filter matches, {@code false} otherwise.
     */
    boolean matches(Map<String,String> resource);

    /**
     * Provides a string representation of the filter.
     *
     * @return A string representation of the filter.
     */
    String toString();

}

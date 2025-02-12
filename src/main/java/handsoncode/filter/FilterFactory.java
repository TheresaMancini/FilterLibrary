package handsoncode.filter;

import java.util.List;

/**
 * A factory class for creating various types of {@link Filter} objects.
 * <p>
 * This class provides static factory methods to create filter instances dynamically.
 * It ensures proper instantiation and validation of filter parameters.
 * </p>
 *
 * Example Usage:
 * <pre>{@code
 * Filter ageFilter = FilterFactory.greaterThan("age", 18);
 * Filter nameFilter = FilterFactory.equalsFilter("name", "John");
 * Filter combinedFilter = FilterFactory.and(ageFilter, nameFilter);
 *
 * boolean result = combinedFilter.matches(resource); // Checks if resource satisfies both conditions
 * }</pre>
 *
 * Available Filters:
 * <ul>
 *     <li>{@link #trueFilter()} - Creates a boolean literal filter that always returns true.</li>
 *     <li>{@link #falseFilter()} - Creates a boolean literal filter that always returns false.</li>
 *     <li>{@link #greaterThan(String, int)} - Creates a filter that checks if a value is greater than a given number.</li>
 *     <li>{@link #lessThan(String, int)} - Creates a filter that checks if a value is less than a given number.</li>
 *     <li>{@link #equalsTo(String, String)} - Creates a filter that checks if a value matches a given string.</li>
 *     <li>{@link #and(List<Filter>)} - Combines multiple filters using logical AND.</li>
 *     <li>{@link #or(List<Filter>)} - Combines multiple filters using logical OR.</li>
 *     <li>{@link #not(Filter)} - Negates the result of another filter.</li>
 * </ul>
 *
 */

public class FilterFactory {

    private FilterFactory(){

    }

    /**
     * Creates a filter that always evaluates to {@code true}.
     *
     * @return A {@link FalseFilter} instance that always returns {@code true}.
     */
    public static Filter trueFilter(){
        return TrueFilter.getInstance();
    }

    /**
     * Creates a filter that always evaluates to {@code false}.
     *
     * @return A {@link FalseFilter} instance that always returns {@code false}.
    */
    public static Filter falseFilter(){
        return FalseFilter.getInstance();
    }

    /**
     * Creates a filter that checks if a numeric field is greater than the specified value.
     *
     * @param property   The name of the field to compare.
     * @param value The threshold value.
     * @return A {@link GreaterThanFilter} instance.
     * @throws NullPointerException if {@code property} is null.
     */
    public static Filter greaterThan(String property, double value) {
        return new GreaterThanFilter(property, value);
    }

     /**
     * Creates a filter that checks if a numeric field is less than the specified value.
     *
     * @param property   The name of the field to compare.
     * @param value The threshold value.
     * @return A {@link LessThanFilter} instance.
     * @throws NullPointerException if {@code property} is null.
     */
    public static Filter lessThan(String property, double value) {
        return new LessThanFilter(property, value);
    }
    /**
     * Creates a filter that checks if a field matches a given string.
     *
     * @param property   The field to check.
     * @param value The expected value.
     * @return An {@link IsEqualFilter} instance.
     * @throws NullPointerException if {@code property} or {@code value} is null.
     */
    public static Filter equalsTo(String property, String value) {
        return new IsEqualFilter(property, value);
    }

    /**
     * Creates a filter that checks if a field is present in the resource.
     *
     * @param property   The field to check.
     * @return An {@link IsPresentFilter} instance.
     * @throws NullPointerException if {@code property} is null.
     */
    public static Filter isPresent(String property) {
        return new IsPresentFilter(property);
    }

    /**
     * Creates a filter that checks if a field is present in the resource.
     *
     * @param property   The field to check.
     * @param value The regular expression to match (must not be {@code null}).
     * @return A {@link MatchesExpressionFilter} that applies the given regex.
     * @throws NullPointerException if {@code property} is null.
     * @throws IllegalArgumentException if {@code value} is not a valid regex.
     */
    public static Filter matchesExpression(String property, String value) {
        return new MatchesExpressionFilter(property, value);
    }
    /**
     * Combines multiple filters using logical AND.
     *
     * @param filters The filters to combine.
     * @return An {@link AndFilter} that requires all conditions to be true.
     * @throws NullPointerException if {@code filters} is null.
     */
    public static Filter and(List<Filter> filters){
        return new AndFilter(filters);
    }

    /**
     * Combines multiple filters using logical OR.
     *
     * @param filters The filters to combine.
     * @return An {@link OrFilter} that requires at least one condition to be true.
     * @throws NullPointerException if {@code filters} is null.
     */
    public static Filter or(List<Filter> filters){
        return new OrFilter(filters);
    }
    /**
     * Negates the result of another filter using logical NOT.
     *
     * @param filter to have result negated.
     * @return An {@link NotFilter} that inverts the logic of the provided filter.
     * @throws NullPointerException if {@code filters} is null.
     */
    public static Filter not(Filter filter){
        return new NotFilter(filter);
    }

}


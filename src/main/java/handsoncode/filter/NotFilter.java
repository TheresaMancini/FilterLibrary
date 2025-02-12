package handsoncode.filter;

import java.util.Map;
import java.util.Objects;

/**
 * {@code NotFilter} is an implementation of the {@link Filter} interface that negates the result of 
 * another filter.
 * 
 * <p>
 * For example:
 * <pre>
 * Filter isPresentFilter = new IsPresentFilter("key");
 * Filter notPresentFilter = new NotFilter(isPresentFilter);
 * </pre>
 * The {@code notPresentFilter} will return {@code true} if the "key" is not present in the resource.
 * 
 */

public class NotFilter implements Filter {

    private final Filter filter;

     /**
     * Constructs a {@code NotFilter} that negates the result of the given filter.
     * 
     * @param filter the filter whose result will be negated by this filter.
     * @throws NullPointerException if {@code filter} is {@code null}.
     */
    protected NotFilter(Filter filter) {
        Objects.requireNonNull(filter, "Filter cannot be null");
        this.filter = filter;
    }

    /**
     * Negates the result of the {@link Filter#matches(Map)} method of the provided filter.
     * 
     * @param resource a {@link Map} representing the resource data, where the key is a {@link String} 
     *                 (the property name), and the value is the property value (a {@link String}).
     * @return the negation of the result of the provided filter's {@code matches} method:
     *         {@code true} if the filter returns {@code false}, and {@code false} if the filter returns {@code true}.
     */
    @Override
    public boolean matches(Map<String,String> resource) {
        return !filter.matches(resource);
    }

    /**
     * Returns a string representation of this {@code NotFilter}, describing the type of filter 
     * and the negated filter it contains. The string format is JSON-like.
     * 
     * @return a string representation of the {@code NotFilter}.s
     */
    @Override
    public String toString(){
        return "{\"type\":\"NOT\",\"filters\":" + filter.toString() + "}";
    }

}

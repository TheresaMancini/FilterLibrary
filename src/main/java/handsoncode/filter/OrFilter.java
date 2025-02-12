package handsoncode.filter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * {@code OrFilter} is an implementation of the {@link Filter} interface that checks if at least one 
 * of the provided filters matches the resource. It applies a logical OR operation across the filters.
 *
 * Example Usage:
 * <pre>{@code
 * Filter filter1 = new IsEqualTo("status", "active");
 * Filter filter2 = new LessThanFilter("age", 30);
 * Filter orFilter = new OrFilter(filter1, filter2);
 * boolean result = orFilter.matches(resource);  // Returns true if at least one of the filters match
 * }</pre>
 */

public class OrFilter implements Filter{
    private final List<Filter> filters;

    /**
     * Constructs an {@code OrFilter} that applies a logical OR operation across the provided filters.
     * 
     * @param filters the list of filters to be applied with an OR operation.
     * @throws NullPointerException if {@code filters} is {@code null}.
     * @throws IllegalArgumentException if the provided list of filters is empty.
     */
    protected OrFilter(List<Filter> filters) {
        Objects.requireNonNull(filters, "Filters cannot be null");
        if (filters.isEmpty()) {
            throw new IllegalArgumentException("At least one filter is required");
        }
        this.filters = filters;
    }

    /**
     * Checks whether at least one filter in the list matches the resource.
     * 
     * @param resource a {@link Map} representing the resource data, where the key is the property name 
     *                 (a {@link String}), and the value is the property value (a {@link String}).
     * @return {@code true} if at least one filter matches the resource, {@code false} otherwise.
     */
    @Override
    public boolean matches(Map<String,String> resource) {
        for (Filter filter : filters){
            if(filter.matches(resource))
            {
                return true;
            }
        }
        return false;

    }

    /**
     * Returns a string representation of this {@code OrFilter}, describing the type of filter and the filters
     * included in the logical OR operation. The string format is JSON-like.
     * 
     * @return a string representation of the {@code OrFilter}.
     */
    @Override
    public String toString(){
        
        Iterator<Filter> filterIterator = filters.iterator();
        
        StringBuilder filterstring = new StringBuilder("{\"type\":\"OR\",\"filters\":[");
        
        while(filterIterator.hasNext())
        {
            filterstring.append(filterIterator.next().toString());

            if(filterIterator.hasNext()){
                filterstring.append(",");
            }
        }

        filterstring.append("]}");

        return filterstring.toString();
    }

}

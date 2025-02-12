package handsoncode.filter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * {@code AndFilter} is a concrete implementation of the {@link Filter} interface
 * that applies a logical "AND" operation on a collection of filters. 
 * The filter will only match if all the individual filters in the list match the given resource.
 *
 * Example Usage:
 * <pre>{@code
 * Filter filter1 = new IsEqualTo("status", "active");
 * Filter filter2 = new LessThanFilter("age", 30);
 * Filter andFilter = new AndFilter(filter1, filter2);
 * boolean result = andFilter.matches(resource);  // Returns true if both filters match
 * }</pre>
 *
 */
public class AndFilter implements Filter {
    
    private final List<Filter> filters;
    
    /**
     * Constructs an {@code AndFilter} with the specified list of filters.
     * 
     * @param filters  a list of filters to combine using a logical AND operation. 
     *                The list must not be empty and cannot contain {@code null} filters.
     * @throws IllegalArgumentException if {@code filters} is empty.
     */

    public AndFilter(List<Filter> filters) {
        Objects.requireNonNull(filters, "Filters cannot be null");
        if (filters.isEmpty()) {
            throw new IllegalArgumentException("At least one filter is required");
        }
        this.filters = filters;
    }

     /**
     * Evaluates whether the given resource matches all filters in this {@code AndFilter}.
     * The resource matches if all filters in the list match the resource.
     * 
     * @param resource a {@link Map} {@code Map<String,String>} representing the resource to be checked against the filters.
     * @return {@code true} if all filters match the resource, {@code false} otherwise.
     */
    @Override
    public boolean matches(Map<String,String> resource) {
        for (Filter filter : filters){
            if(!filter.matches(resource))
            {
                return false;
            }
        }
        return true;

    }

    /**
     * Returns a string representation of this {@code AndFilter}, describing the type and filters applied.
     * The string format is a JSON-like representation.
     * 
     * @return a string representation of the {@code AndFilter}.
     */
    @Override
    public String toString(){
        
        Iterator<Filter> filterIterator = filters.iterator();
        
        StringBuilder filterstring = new StringBuilder("{\"type\":\"AND\",\"filters\":[");
        
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

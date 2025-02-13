package handsoncode.filter;

import java.util.Map;

/**
 * {@code FalseFilter} is a singleton implementation of the {@link Filter} interface 
 * that always returns {@code false}.
 * This filter can be used when you need a filter that will never match any resource.
 * 
 * <p>This class follows the singleton pattern, ensuring that only one instance of the 
 * {@code FalseFilter} exists.</p>
 * 
 * Example Usage:
 * <pre>{@code
 * Filter falseFilter = FalseFilter.getInstance();
 * boolean result = falseFilter.matches(resource);  // Always returns false
 * }</pre>
 *
 */

public class FalseFilter implements Filter {

    private boolean value = false;
    private static final FalseFilter INSTANCE = new FalseFilter();

    /**
     * Private constructor to prevent instantiation from outside.
     * The class follows the Singleton pattern, so only one instance of {@code FalseFilter} exists.
     */
    private FalseFilter(){}

    /**
     * Always returns {@code false}.
     * 
     * @param resource a {@link Map} representing the resource to be checked (not used in this implementation).
     * @return always {@code false}.
     */
    @Override
    public boolean matches(Map<String,String> resource) {
        return value;
    }

    /**
     * Returns a string representation of the {@code FalseFilter}.
     * The string format is a JSON-like representation.
     * 
     * @return a string representation of the {@code FalseFilter}.
     */
    @Override
    public String toString(){
        return "{\"type\":\"FalseFilter\",\"value\":\""+value+"\"}" ;
    }
    
    /**
     * Retrieves the single instance of the {@code FalseFilter}.
     * 
     * @return the unique instance of the {@code FalseFilter}.
     */
    public static FalseFilter getInstance(){
        return INSTANCE;
    }

}

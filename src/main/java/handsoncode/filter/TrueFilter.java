package handsoncode.filter;

import java.util.Map;

/**
 * {@code TrueFilter} is a singleton implementation of the {@link Filter} interface 
 * that always returns {@code true}.
 * This filter can be used when you need a filter that will always match any resource.
 * 
 * <p>This class follows the singleton pattern, ensuring that only one instance of the 
 * {@code TrueFilter} exists.</p>
 * 
 * Example Usage:
 * <pre>{@code
 * Filter trueFilter = TrueFilter.getInstance();
 * boolean result = TrueFilter.matches(resource);  // Always returns true
 * }</pre>
 *
 */

public class TrueFilter implements Filter {
    
    private boolean value = true;
    private static final TrueFilter instance = new TrueFilter();

    /**
     * Constructor to prevent instantiation from outside.
     * The class follows the Singleton pattern, so only one instance of {@code TrueFilter} exists.
     */
    protected TrueFilter(){
        
    }

    /**
     * Always returns {@code true}.
     * 
     * @param resource a {@link Map} representing the resource to be checked (not used in this implementation).
     * @return always {@code true}.
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
        return "{\"type\":\"TrueFilter\",\"value\":\""+value+"\"}" ;
    }

    /**
     * Retrieves the single instance of the {@code TrueFilter}.
     * 
     * @return the unique instance of the {@code TrueFilter}.
     */
    public static TrueFilter getInstance(){
        return instance;
    }
}

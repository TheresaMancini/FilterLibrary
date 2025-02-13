package handsoncode.filter;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * {@code MatchesExpressionFilter} is an implementation of the {@link Filter} interface 
 * that checks if a specified property in the resource matches a given regular expression.
 * The regular expression is applied in a case-insensitive manner.
 *
 * Example Usage:
 * <pre>{@code
 * Filter regexFilter = new MatchesExpressionFilter("email", "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$");
 * boolean result = MatchesExpressionFilter.matches(resource);  // Returns true if "email" matches the regex pattern
 * }</pre>
 */

public class MatchesExpressionFilter implements Filter{
    
    private final String property;
    private final Pattern regex;
    private final String value;
    
    /**
     * Constructs a {@code MatchesExpressionFilter} with the specified property name and regular expression value.
     * The regular expression is compiled with case-insensitivity.
     * 
     * @param property the name of the property to check against the regular expression.
     * @param value the regular expression pattern to match against the property value.
     * @throws NullPointerException if {@code property} or {@code value} is {@code null}.
     * @throws IllegalArgumentException if the {@code value} is not a valid regular expression.
     */
    protected MatchesExpressionFilter(String property, String value){
        this.property = Objects.requireNonNull(property, "property must not be null");
        this.value = Objects.requireNonNull(value, "value must not be null");
        try{
            this.regex = Pattern.compile(value, Pattern.CASE_INSENSITIVE);
           }
           catch(PatternSyntaxException e){
               throw new IllegalArgumentException("Value '" + value + "' is not a proper Regular Expression.");
        }
    }

    /**
     * Checks whether the specified property in the resource matches the regular expression.
     * If the property exists in the resource, its value is compared against the regular expression.
     * 
     * @param resource a {@link Map} representing the resource data, where the key is the property name 
     *                 (a {@link String}), and the value is the property value (a {@link String}).
     * @return {@code true} if the value of the specified property matches the regular expression,
     *         {@code false} otherwise.
     */
    @Override
    public boolean matches(Map<String, String> resource) {
        if(resource.containsKey(property) && value != null)
        {
            Matcher matches = regex.matcher(resource.get(property));
            return matches.find();

        }

        return false;
    }

    /**
     * Returns a string representation of this {@code MatchesExpressionFilter}, describing the type of filter,
     * the field (property) being checked, and the regular expression value. The string format is JSON-like.
     * 
     * @return a string representation of the {@code MatchesExpressionFilter}.
     */
    @Override
    public String toString(){
        return "{\"type\":\"MatchesExpression\",\"property\":\"" + property + "\",\"value\":\""+ value +"\"}";
    }

}

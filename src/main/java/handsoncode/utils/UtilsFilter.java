package handsoncode.utils;

/**
 * Utility class that provides common helper methods for various operations.
 * <p>
 * This class contains static methods for frequently used functionalities, such as safe parsing
 * of doubles. The methods are designed to be simple and reusable across different parts of an application.
 * </p>
 *
 * Example Usage:
 * <pre>{@code
 * int result = UtilsFilter.safeParseDouble("123");  // Safe parsing example
 * }</pre>
 *
 * <p><strong>Note:</strong> This class cannot be instantiated as it only contains static methods.</p>
 */
public final class UtilsFilter {

    // Suppresses default constructor, ensuring non-instantiability.
    private UtilsFilter() {
        throw new UnsupportedOperationException("Utility class, should not be instantiated");
    }
    /**
     * Safely parses a string into a double, returning a default value if parsing fails.
     * <p>
     * This method attempts to parse the string into an integer. If the string cannot be parsed (due to
     * invalid format or {@code null}), it returns the provided default value.
     * </p>
     *
     * Example Usage:
     * <pre>{@code
     * int number = UtilsFilter.safeParseDouble("123");  // Returns 123
     * int invalidNumber = UtilsFilter.safeParseDouble("abc");  // Returns null because the string is not a valid number
     * }</pre>
     *
     * @param value The string value to parse (may be {@code null}).
     * @return The parsed integer if successful, or {@code null} if parsing fails or the string is invalid.
     */
    public static Double safeParseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }

}

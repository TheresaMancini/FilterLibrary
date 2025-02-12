package handsoncode.filter;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/** Deserializes JSON objects into {@link Filter} instances.
 * This class is used to convert JSON representations of various filter types into their corresponding Java objects.
 *
 * <p>Supported filter types:</p>
 * <ul>
 *     <li>TrueFilter</li>
 *     <li>FalseFilter</li>
 *     <li>GreaterThanFilter</li>
 *     <li>LessThanFilter</li>
 *     <li>IsEqualFilter</li>
 *     <li>IsPresentFilter</li>
 *     <li>MatchesExpressionFilter</li>
 *     <li>NotFilter</li>
 *     <li>AndFilter</li>
 *     <li>OrFilter</li>
 * </ul>
 *
 * <p>Example JSON format:</p>
 * <pre>
 * {
 *     "type": "GreaterThan",
 *     "field": "age",
 *     "value": 30
 * }
 * </pre>
 * 
 * 
 */

public class FilterDeserializer implements JsonDeserializer<Filter> {

     /**
     * Deserializes a JSON element into a {@link Filter} object.
     *
     * @param json    The JSON element to deserialize.
     * @param typeOfT The type of the object to deserialize.
     * @param context The deserialization context.
     * @return The deserialized {@link Filter} object.
     * @throws JsonParseException If the JSON format is invalid or the filter type is unknown.
     */

    @Override
    public Filter deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        switch (type.toLowerCase()) {
            case "truefilter":
                return TrueFilter.getInstance();

            case "falsefilter":
                return FalseFilter.getInstance();

            case "greaterthan":
                return new GreaterThanFilter(jsonObject.get("field").getAsString(), jsonObject.get("value").getAsDouble());

            case "lessthan":
                return new LessThanFilter(jsonObject.get("field").getAsString(), jsonObject.get("value").getAsDouble());

            case "equalsto":
                return new IsEqualFilter(jsonObject.get("field").getAsString(), jsonObject.get("value").getAsString());
            
            case "ispresent":
                return new IsPresentFilter(jsonObject.get("field").getAsString());
            
            case "matchesexpression":
                return new MatchesExpressionFilter(jsonObject.get("field").getAsString(), jsonObject.get("value").getAsString());

            case "not":
                return new NotFilter(context.deserialize(jsonObject.get("filter"), Filter.class));

            case "and":
                List<Filter> andFilters = new ArrayList<>();
                for (JsonElement element : jsonObject.getAsJsonArray("filters")) {
                    andFilters.add(context.deserialize(element, Filter.class));
                }
                return new AndFilter(andFilters);

            case "or":
                List<Filter> orFilters = new ArrayList<>();
                for (JsonElement element : jsonObject.getAsJsonArray("filters")) {
                    orFilters.add(context.deserialize(element, Filter.class));
                }
                return new OrFilter(orFilters);

            default:
                throw new JsonParseException("Unknown filter type: " + type);
        }
    }
}

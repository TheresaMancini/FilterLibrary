package handsoncode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import handsoncode.filter.Filter;
import handsoncode.filter.FilterFactory;

public class ParseJsonTest {

    @Test
    public void JsonParseTest(){
        
        Filter trueFilter = FilterFactory.trueFilter();
        Filter agegt30 = FilterFactory.greaterThan("age",30);
        Filter agelt27 = FilterFactory.lessThan("age",27);
        
        Filter filter2 = FilterFactory.equalsTo("status", "active");
        Filter filter2new = FilterFactory.fromString(filter2.toString());
        
        assertEquals(filter2.toString(), filter2new.toString());
        
        Filter isPresentFilter = FilterFactory.isPresent("age");
        Filter regexFilter = FilterFactory.matchesExpression("firstname","[abc]");
        
        
        Filter agelt27fromSring = FilterFactory.fromString(agelt27.toString());
        Filter trueFilterfromSring = FilterFactory.fromString(trueFilter.toString());
        Filter isPresentfromSring = FilterFactory.fromString(isPresentFilter.toString());
        Filter regexFilterfromSring = FilterFactory.fromString(regexFilter.toString());
        
        assertEquals(agelt27fromSring.toString(), agelt27.toString());
        assertEquals(trueFilterfromSring.toString(), trueFilter.toString());
        assertEquals(isPresentfromSring.toString(), isPresentFilter.toString());
        assertEquals(regexFilterfromSring.toString(), regexFilter.toString());
        
        
        Filter andFilter = FilterFactory.and(List.of(trueFilter,agelt27));
        Filter orComposeFilter = FilterFactory.or(List.of(andFilter,agegt30));
        Filter notOrComposeFilter = FilterFactory.not(orComposeFilter);
        
        Filter andFilterFromString = FilterFactory.fromString(andFilter.toString());
        Filter orFilterFromSring = FilterFactory.fromString(orComposeFilter.toString());
        Filter notFilterFromString = FilterFactory.fromString(notOrComposeFilter.toString());

        assertEquals(andFilterFromString.toString(), andFilter.toString());
        assertEquals(orFilterFromSring.toString(), orComposeFilter.toString());
        assertEquals(notFilterFromString.toString(), notOrComposeFilter.toString());

    }
    
    @Test
    public void JsonParseFilter()
    {
        Map<String, String> user;
        user = TestData.getUserAge25();
        
        Filter agegt30 = FilterFactory.greaterThan("age",20);
        Filter trueFilter = FilterFactory.trueFilter();
        
        Filter composedFilter =FilterFactory.or(List.of(agegt30,trueFilter));
        
        Filter parsedFilter = FilterFactory.fromString(agegt30.toString());
        assertTrue(parsedFilter.matches(user));
        Filter combinedFilter = FilterFactory.fromString(composedFilter.toString());
        assertTrue(combinedFilter.matches(user));
    }
}

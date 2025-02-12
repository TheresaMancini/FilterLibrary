package handsoncode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import handsoncode.filter.Filter;
import handsoncode.filter.FilterFactory;

public class LogicalOperatorsTest {
   
   
    @Test
    public void NotFilterTest(){
        Map<String, String> user;
        user = TestData.getUserAge35();

        Filter trueFilter = FilterFactory.trueFilter();
        Filter falseFilter = FilterFactory.falseFilter();

        Filter notTrue = FilterFactory.not(trueFilter);
        Filter notFalse = FilterFactory.not(falseFilter);
        
        assertFalse(notTrue.matches(user));
        assertTrue(notFalse.matches(user));
        
    }
    
    
    @Test
    public void AndFilterTest(){

        Map<String, String> user;
        user = TestData.getUserAge35();

        Filter trueFilter = FilterFactory.trueFilter();
        Filter falseFilter = FilterFactory.falseFilter();
       
        Filter andFilterFalse = FilterFactory.and(List.of(falseFilter,trueFilter));
        Filter andFilterFalse2 = FilterFactory.and(List.of(falseFilter,falseFilter,falseFilter));
        Filter andFilterTrue = FilterFactory.and(List.of(trueFilter,trueFilter,trueFilter));

        assertFalse(andFilterFalse.matches(user));
        assertFalse(andFilterFalse2.matches(user));
        assertTrue(andFilterTrue.matches(user));

        String expected = "{\"type\":\"AND\",\"filters\":[{\"type\":\"FalseFilter\",\"value\":\"false\"},{\"type\":\"TrueFilter\",\"value\":\"true\"}]}";

        assertEquals(expected, andFilterFalse.toString());

    }

    @Test
    public void OrFilterTest(){

        Map<String, String> user;
        user = TestData.getUserAge35();

        Filter trueFilter = FilterFactory.trueFilter();
        Filter falseFilter = FilterFactory.falseFilter();
        Filter agegt30 = FilterFactory.greaterThan("age",30);
        Filter agelt20 = FilterFactory.lessThan("age",20);
       
        Filter orFilterTrue = FilterFactory.or(List.of(agegt30,trueFilter));
        Filter orFilterFalse = FilterFactory.or(List.of(falseFilter,agelt20));

        assertTrue(orFilterTrue.matches(user));
        assertFalse(orFilterFalse.matches(user));

        String expected = "{\"type\":\"OR\",\"filters\":[{\"type\":\"FalseFilter\",\"value\":\"false\"},{\"type\":\"LessThan\",\"field\":\"age\",\"value\":\"20.0\"}]}";

        assertEquals(expected,orFilterFalse.toString());

    }

    @Test
    public void ComposeAndOrFilterTest(){

        Map<String, String> user;
        user = TestData.getUserAge25();

        Filter trueFilter = FilterFactory.trueFilter();
        Filter agegt30 = FilterFactory.greaterThan("age",30);
        Filter agelt27 = FilterFactory.lessThan("age",27);
       
       
        Filter andFilterTrue = FilterFactory.and(List.of(trueFilter,agelt27));
        Filter andFilterFalse = FilterFactory.and(List.of(trueFilter,agegt30));
        
        Filter orComposeFilterTrue = FilterFactory.or(List.of(andFilterTrue,andFilterFalse));
        Filter notOrComposeFilterFalse = FilterFactory.not(andFilterFalse);

        assertTrue(orComposeFilterTrue.matches(user));
        assertTrue(notOrComposeFilterFalse.matches(user));
        
        String expected = "{\"type\":\"NOT\",\"filter\":{\"type\":\"AND\",\"filters\":[{\"type\":\"TrueFilter\",\"value\":\"true\"},{\"type\":\"GreaterThan\",\"field\":\"age\",\"value\":\"30.0\"}]}}";

        assertEquals(expected, notOrComposeFilterFalse.toString());
       
    }

}

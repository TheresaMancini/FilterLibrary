package handsoncode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import handsoncode.filter.Filter;
import handsoncode.filter.FilterFactory;

public class LogicalOperatorsTest {
   
   
    @Test
    public void NotFilterTest(){
        Map<String, String> user = new HashMap<>();
        user.put("firstname", "Joe");
        user.put("lastname", "Bloggs");

        Filter trueFilter = FilterFactory.trueFilter();
        Filter falseFilter = FilterFactory.falseFilter();

        Filter notTrue = FilterFactory.not(trueFilter);
        Filter notFalse = FilterFactory.not(falseFilter);
        
        assertFalse(notTrue.matches(user));
        assertTrue(notFalse.matches(user));
        
    }
    
    
    @Test
    public void AndFilterTest(){

        Map<String, String> user = new HashMap<>();
        user.put("firstname", "Joe");
        user.put("lastname", "Bloggs");

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

        Map<String, String> user = new HashMap<>();
        user.put("firstname", "Joe");
        user.put("lastname", "Bloggs");

        Filter trueFilter = FilterFactory.trueFilter();
        Filter falseFilter = FilterFactory.falseFilter();
       
        Filter orFilterTrue = FilterFactory.or(List.of(falseFilter,trueFilter));
        Filter orFilterTrue2 = FilterFactory.or(List.of(falseFilter,falseFilter,trueFilter));
        Filter orFilterFalse = FilterFactory.or(List.of(falseFilter,falseFilter,falseFilter));

        assertTrue(orFilterTrue.matches(user));
        assertTrue(orFilterTrue2.matches(user));
        assertFalse(orFilterFalse.matches(user));

        String expected = "{\"type\":\"OR\",\"filters\":[{\"type\":\"FalseFilter\",\"value\":\"false\"},{\"type\":\"FalseFilter\",\"value\":\"false\"},{\"type\":\"FalseFilter\",\"value\":\"false\"}]}";

        assertEquals(expected,orFilterFalse.toString());

    }

    @Test
    public void ComposeAndOrFilterTest(){

        Map<String, String> user = new HashMap<>();
        user.put("firstname", "Joe");
        user.put("lastname", "Bloggs");

        Filter trueFilter = FilterFactory.trueFilter();
        Filter falseFilter = FilterFactory.falseFilter();
       
        Filter andFilterTrue = FilterFactory.and(List.of(trueFilter,trueFilter));
        Filter andFilterFalse = FilterFactory.and(List.of(trueFilter,falseFilter));
        Filter orComposeFilterTrue = FilterFactory.or(List.of(andFilterTrue,andFilterFalse));
        Filter orComposeFilterFalse = FilterFactory.or(List.of(andFilterFalse,andFilterFalse));
        
        Filter notOrComposeFilterFalse = FilterFactory.not(orComposeFilterFalse);

        assertTrue(orComposeFilterTrue.matches(user));
        assertFalse(orComposeFilterFalse.matches(user));
        assertTrue(notOrComposeFilterFalse.matches(user));

        String expected = "{\"type\":\"NOT\",\"filters\":{\"type\":\"OR\",\"filters\":[{\"type\":\"AND\",\"filters\":[{\"type\":\"TrueFilter\",\"value\":\"true\"},{\"type\":\"FalseFilter\",\"value\":\"false\"}]},{\"type\":\"AND\",\"filters\":[{\"type\":\"TrueFilter\",\"value\":\"true\"},{\"type\":\"FalseFilter\",\"value\":\"false\"}]}]}}";

        assertEquals(expected, notOrComposeFilterFalse.toString());
       
    }

}

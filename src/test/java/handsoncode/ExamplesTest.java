package handsoncode;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import handsoncode.filter.Filter;
import handsoncode.filter.FilterFactory;

public class ExamplesTest {
    
    @Test
    public void exampleTest(){
        
        Map<String, String> user,user2;
        user = TestData.getUserAge35();
        user2 = TestData.getUserAge25(); 

        // EXAMPLE 1
        // Create a filter which matches all administrators older than 30:
        Filter older30 = FilterFactory.greaterThan("age", 30);
        Filter roleAdmin = FilterFactory.equalsTo("role", "administrator");

        Filter filter = FilterFactory.and(List.of(older30,roleAdmin));

        assertTrue(filter.matches(user));
        assertFalse(filter.matches(user2));

        // EXAMPLE 2
        // create boolean literals
        Filter trueFilter = FilterFactory.trueFilter();

        assertTrue(trueFilter.matches(user));

        // EXAMPLE 3
        // create logical boolean operators
        Filter orFilter = FilterFactory.or(List.of(trueFilter,filter));

        assertTrue(orFilter.matches(user));

    }



}

package handsoncode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import handsoncode.filter.Filter;
import handsoncode.filter.FilterFactory;

public class BooleanLiteralTest 
{

    //Puxar o Map pra fora -- Criar modelo de teste
    @Test
    public void testTrueFilter(){

        Map<String, String> user;
        user = TestData.getUserAge35();

        Filter trueFilter = FilterFactory.trueFilter();
        assertTrue(trueFilter.matches(user));

        assertEquals("{\"type\":\"TrueFilter\",\"value\":\"true\"}",trueFilter.toString());
        
    }

    @Test
    public void testFalseFilter(){

        Map<String, String> user;
        user = TestData.getUserAge25();

        Filter falseFilter = FilterFactory.falseFilter();
        assertFalse(falseFilter.matches(user));

        assertEquals("{\"type\":\"FalseFilter\",\"value\":\"false\"}",falseFilter.toString());
        
    }
}

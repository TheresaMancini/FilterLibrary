package handsoncode;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import handsoncode.filter.Filter;
import handsoncode.filter.FilterFactory;

public class ExceptionsTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullValuedProperties(){
        Map<String, String> user;
        user = TestData.getUserNull();

        Filter agegt30 = FilterFactory.greaterThan("age", 30);
        Filter agelt30 = FilterFactory.lessThan("age", 30);
       
        agegt30.matches(user);
        agelt30.matches(user);     
       
    }

    @Test(expected = NullPointerException.class)
    public void testNullValuedParameter(){
        
        Map<String, String> user;
        user = TestData.getUserAge35();

        Filter ageis30 = FilterFactory.equalsTo("age", null);
        Filter ageispresent = FilterFactory.isPresent(null);
        
        ageis30.matches(user);
        ageispresent.matches(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullValuedParameter2(){
        
        Map<String, String> user;
        user = TestData.getUserAge35();

        Filter lessthannull = FilterFactory.lessThan("lastname",20);
        
        lessthannull.matches(user);
    }

    @Test(expected = NullPointerException.class)
    public void testNoParameter(){
        
        Map<String, String> user;
        user = TestData.getUserAge35();

        Filter and = FilterFactory.or(List.of(null,null));
        
        and.matches(user);
       
    }

    @Test(expected = NullPointerException.class)
    public void testMatchesExpressionNullException(){

        Map<String, String> user;
        user = TestData.getUserAge35();

        Filter firstname = FilterFactory.matchesExpression("firstname",null);
        firstname.matches(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMatchesExpressionException(){

        Map<String, String> user;
        user = TestData.getUserAge35();

        Filter firstname = FilterFactory.matchesExpression("firstname","[A-Z+");
        firstname.matches(user);
    }


}

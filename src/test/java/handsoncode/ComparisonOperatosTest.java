package handsoncode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import handsoncode.filter.Filter;
import handsoncode.filter.FilterFactory;

public class ComparisonOperatosTest {

    @Test
    public void testIsPresentFilter(){

        Map<String, String> user;
        user = TestData.getUserAge35();

        /* Should Return True */
        Filter hasFirstname = FilterFactory.isPresent("firstname");
        Filter hasLastname = FilterFactory.isPresent("lastname");
        Filter hasAge = FilterFactory.isPresent("age");
        
        assertTrue(hasFirstname.matches(user));
        assertTrue(hasLastname.matches(user));
        assertTrue(hasAge.matches(user));
        
        
        /* Should Return False - property is case sensitive - missing property*/
        Filter hasLastName = FilterFactory.isPresent("LastName");
        Filter hasExtra = FilterFactory.isPresent("extra");
        Filter hasNull = FilterFactory.isPresent("testEmpty");
        Filter hasEmpty = FilterFactory.isPresent("testnull");

        assertFalse(hasLastName.matches(user));
        assertFalse(hasExtra.matches(user));
        assertFalse(hasNull.matches(user));
        assertFalse(hasEmpty.matches(user));
    }

    @Test
    public void testIsEqualToFilter(){

        Map<String, String> user;
        user = TestData.getUserAge35();
        
        Filter firstname = FilterFactory.equalsTo("firstname","Joe");
        Filter lastname = FilterFactory.equalsTo("lastname","Bloggs");
        Filter lastnameNotCaseSensitive = FilterFactory.equalsTo("lastname","bloggs");
        Filter age = FilterFactory.equalsTo("age","35");
        
        assertTrue(firstname.matches(user));
        assertTrue(lastname.matches(user));
        assertTrue(lastnameNotCaseSensitive.matches(user));
        assertTrue(age.matches(user));
        
        
        Filter firstnameNotequal = FilterFactory.equalsTo("firstname","John");
        Filter firstnameEmpty = FilterFactory.equalsTo("firstname","");
        Filter caseSensitiveProperty = FilterFactory.equalsTo("FirstName","Joe");
        Filter missingProperty = FilterFactory.equalsTo("extra","any");
        Filter ageNotEqual = FilterFactory.equalsTo("age","25");
        
        assertFalse(firstnameNotequal.matches(user));
        assertFalse(firstnameEmpty.matches(user));
        assertFalse(caseSensitiveProperty.matches(user));
        assertFalse(missingProperty.matches(user));
        assertFalse(ageNotEqual.matches(user));

    }

    @Test
    public void testGreaterThan(){
        
        Map<String, String> user;
        user = TestData.getUserAge35();

        Filter gtAge30 = FilterFactory.greaterThan("age",30);
        assertTrue(gtAge30.matches(user));

        
        Filter gtAge40 = FilterFactory.greaterThan("age",40);
        assertFalse(gtAge40.matches(user));
            
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGreaterThanException(){
        
        Map<String, String> user;
        user = TestData.getUserAge35();

        Filter stringPropertyInt = FilterFactory.greaterThan("firstname",30);
        stringPropertyInt.matches(user);
        
    }

    @Test
    public void testLessThan(){
        
        Map<String, String> user;
        user = TestData.getUserAge25();
    
        Filter ltAge30 = FilterFactory.lessThan("age",30);
        assertTrue(ltAge30.matches(user));
        
        
        Filter ltAge20 = FilterFactory.lessThan("age",24);
        assertFalse(ltAge20.matches(user));    
        
        Filter ltheght = FilterFactory.lessThan("height",1.20);
        assertFalse(ltheght.matches(user));    
        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testLessThanException(){
        
        Map<String, String> user;
        user = TestData.getUserAge35();

        Filter stringPropertyInt = FilterFactory.lessThan("firstname",30);

        stringPropertyInt.matches(user);
        
    }

    @Test
    public void testMatchesExpression(){

        Map<String, String> user;
        user = TestData.getUserAge35();

        Filter firstname = FilterFactory.matchesExpression("firstname","Joe");
        Filter lastname = FilterFactory.matchesExpression("lastname","Blog*.");
        Filter lastnameNotCaseSensitive = FilterFactory.matchesExpression("lastname","bloggs");
        Filter age = FilterFactory.matchesExpression("age","35");
        
        assertTrue(firstname.matches(user));
        assertTrue(lastname.matches(user));
        assertTrue(lastnameNotCaseSensitive.matches(user));
        assertTrue(age.matches(user));
        
        Filter firstnameNotequal = FilterFactory.matchesExpression("firstname","John");
        Filter caseSensitiveProperty = FilterFactory.matchesExpression("FirstName","Joe");
        Filter missingProperty = FilterFactory.matchesExpression("extra","any");
        
        assertFalse(firstnameNotequal.matches(user));
        assertFalse(caseSensitiveProperty.matches(user));
        assertFalse(missingProperty.matches(user));
        
    }
}

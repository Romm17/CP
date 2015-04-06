package test;

import code.PhoneNumber;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;

public class PhoneNumberJUnit4Test extends TestCase {

    private String trueNumbers[];
    private String falseNumbers[];

    @Before
    public void setUp(){
        trueNumbers = new String[] {
                "687 87 76",
                "(7832) 234-234-234",
                "7 (7832) 234-234-234",
                "+3 (7832) 234-234-234",
        };
        falseNumbers = new String[] {
                "567-78 98",
                "367 783-34",
                "(673 786-78-32",
                "673) 786-78-32",
                "9 (7832) 234-234-234",
        };
    }

    @After
    public void tearDown(){
        trueNumbers = null;
        falseNumbers = null;
    }

    public void testCheckNumber(){
        for(String s : trueNumbers)
            assertEquals(PhoneNumber.checkNumber(s), true);
        for(String s : falseNumbers)
            assertEquals(PhoneNumber.checkNumber(s), false);
    }
}

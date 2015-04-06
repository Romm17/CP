package test;

import code.MyParser;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyParserJUnit4Test extends TestCase {

    private String param[];

    @Before
    public void setUp(){
        param = new String[]{
                System.getProperty("user.dir") + "/res/test1.java",
                System.getProperty("user.dir") + "/res/test2.java",
                System.getProperty("user.dir") + "/res/test3.java"};
    }

    @After
    public void tearDown(){
        param = null;
    }

    @Test
    public void testAction(){
        int i = -1;

        for(String s : param) {
            i = MyParser.action(s);
            assertEquals(0, i);
        }
    }
}

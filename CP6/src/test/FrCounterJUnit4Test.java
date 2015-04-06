package test;

import code.*;
import junit.framework.TestCase;

public class FrCounterJUnit4Test extends TestCase {

    public void testCalc(){
        long ms = System.currentTimeMillis();
        FrCounter.calc(System.getProperty("user.dir") + "/res/shakespeare.txt", 5);
        FrCounter.calc(System.getProperty("user.dir") + "/res/shakespeare.txt", 10);
        FrCounter.calc(System.getProperty("user.dir") + "/res/shakespeare.txt", 20);
        ms = System.currentTimeMillis() - ms;
        assertTrue(ms < 35000);
    }
}

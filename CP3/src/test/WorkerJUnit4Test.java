package test;

import code.Worker;
import junit.framework.TestCase;
import org.junit.Test;

public class WorkerJUnit4Test extends TestCase {

    @Test
    public void testActionByThreadPool(){
        assertTrue(Worker.actionByThreadPool(1, 1));
        assertTrue(Worker.actionByThreadPool(2, 10));
        assertTrue(Worker.actionByThreadPool(3, 5));
        assertTrue(Worker.actionByThreadPool(4, 20));
        assertTrue(Worker.actionByThreadPool(5, 16));
        assertTrue(Worker.actionByThreadPool(6, 30));
        assertTrue(Worker.actionByThreadPool(7,100));
    }

    @Test
    public void testActionByExecutors(){
        assertTrue(Worker.actionByExecutors(1, 1));
        assertTrue(Worker.actionByExecutors(2, 10));
        assertTrue(Worker.actionByExecutors(3, 5));
        assertTrue(Worker.actionByExecutors(4, 20));
        assertTrue(Worker.actionByExecutors(5, 16));
        assertTrue(Worker.actionByExecutors(6, 30));
        assertTrue(Worker.actionByExecutors(7,100));
    }

}

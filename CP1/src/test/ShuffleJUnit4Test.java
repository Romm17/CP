package test;

import code.Shuffle;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShuffleJUnit4Test extends TestCase {

    private int arr[][];
    private final int WIDTH = 20;
    private final int HEIGHT = 21;

    @Before
    public void setUp(){
        arr = new int[HEIGHT][WIDTH];
        for(int i = 0; i < HEIGHT; i++)
            for(int j = 0; j < WIDTH; j++)
                arr[i][j] = i * WIDTH + j + 1;
    }

    @After
    public void tearDown(){
        arr = null;
    }

    @Test
    public void testShuffle(){
        Shuffle.arr = arr.clone();
        Shuffle.SIZE_X = WIDTH;
        Shuffle.SIZE_Y = HEIGHT;
        Shuffle.shuffle();
        int miss = 0;
        for(int i = 0; i < HEIGHT; i++)
            for(int j = 0; j < WIDTH; j++)
                if(Shuffle.arr[i][j] != arr[i][j])
                    miss++;
        assertTrue(miss <= (HEIGHT + 1) * (WIDTH + 1) / 4);
    }
}

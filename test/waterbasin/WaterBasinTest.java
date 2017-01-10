package waterbasin;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tien
 * the test cases are from https://gist.github.com/igor47/7228586
 * and https://gist.github.com/mkozakov/59af0fd5bddbed1a0399
 */
public class WaterBasinTest {
    
    public WaterBasinTest() {
    }
    


    /**
     * Test of solution method, of class WaterBasin.
     */
    @Test
    public void testSolution() {
        System.out.println("solution");
//        ArrayList arr = new ArrayList(Arrays.asList(1,0,1));
//        int expResult = 0;
//        int result = WaterBasin.solution(arr);
//        assertEquals(expResult, result);
        assertEquals(1,WaterBasin.solution(new ArrayList(Arrays.asList(1,0,1))));
        assertEquals(5,WaterBasin.solution(new ArrayList(Arrays.asList(5,0,5))));
        assertEquals(1,WaterBasin.solution(new ArrayList(Arrays.asList(0,1,0,1,0))));
        assertEquals(1,WaterBasin.solution(new ArrayList(Arrays.asList(1,0,1,0))));
        assertEquals(3,WaterBasin.solution(new ArrayList(Arrays.asList(1,0,1,2,0,2))));
        assertEquals(10,WaterBasin.solution(new ArrayList(Arrays.asList(2,5,1,2,3,4,7,7,6))));
        assertEquals(1,WaterBasin.solution(new ArrayList(Arrays.asList(5,1,0,1))));
        assertEquals(12,WaterBasin.solution(new ArrayList(Arrays.asList(2,5,1,2,3,4,7,7,6,3,5))));
        assertEquals(51,WaterBasin.solution(new ArrayList(Arrays.asList(2, 1, 3, 
                                                                        4, 1, 2, 1, 3, 2, 1, 0, 2,
                                                                        4, 3, 1, 5, 3, 1, 4, 1, 2, 
                                                                        1, 0, 5, 2, 1, 3))));
        assertEquals(12,WaterBasin.solution(new ArrayList(Arrays.asList(2, 1, 3, 
                4, 1, 2, 1, 3, 2, 1, 0, 2, 4, 3, 1, 5, 3, 1, 4, 1, 2, 1, 0))));
        
        //this is the failed test
        assertEquals(13,WaterBasin.solution(new ArrayList(Arrays.asList(2,5,1,2,3,4,7,7,6,3,5))));

    }
    
}
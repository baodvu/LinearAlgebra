package mathproject.presenters;

import math.matrix.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bvu
 */
public class VectorOperationTest {
    
    public VectorOperationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of dotProduct method, of class VectorOperation.
     */
    @Test
    public void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(8, 7, 4);
        double expResult = 34.0;
        double result = v1.dotProduct(v2);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testNorm() {
        Vector v1 = new Vector(3, 4);
        double expResult = 5.0;
        double result = v1.norm();
        assertEquals(expResult, result, 0.0);
    }
    
}

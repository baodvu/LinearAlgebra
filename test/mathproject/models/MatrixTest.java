package mathproject.models;

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
public class MatrixTest {
    
    public MatrixTest() {
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
     * Test of get method, of class Matrix.
     */
    @Test
    public void testGet() {
        /*System.out.println("get");
        int n = 0;
        int m = 0;
        Matrix instance = null;
        double expResult = 0.0;
        double result = instance.get(n, m);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of put method, of class Matrix.
     */
    @Test
    public void testPut() {
        Matrix m = new Matrix(2, 3);
        m.put(5, 4, 3, 2, 1, 5);
        System.out.println(m);
    }
    
}

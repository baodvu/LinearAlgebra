/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathproject.presenters;

import mathproject.models.Vector;
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
        System.out.println("dotProduct");
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(8, 7, 4);
        double expResult = 34.0;
        double result = v1.dotProduct(v2);
        assertEquals(expResult, result, 0.0);
    }
    
}

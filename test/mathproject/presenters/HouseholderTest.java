/*
 * The MIT License
 *
 * Copyright 2014 bvu.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package mathproject.presenters;

import mathproject.models.Matrix;
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
public class HouseholderTest {
    
    public HouseholderTest() {
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
     * Test of calculate method, of class Householder.
     */
    @Test
    public void testCalculate() {/*
        Matrix A =  new Matrix(4,2);
        A.put(1,2,3,4,5,6,7,8);
        Householder instance = new Householder(A);
        System.out.println(instance.getQ());
        System.out.println(instance.getR());
        Matrix expResult = MatrixOps.multiply(instance.getQ(), instance.getR());
        MatrixOps.prettify(expResult);
        assertEquals(A, expResult);*/
    }
    
        /**
     * Test of calculate method, of class Householder.
     */
    @Test
    public void testCalculate1() {
        Matrix A =  new Matrix(4,3);
        A.put(1,0,1,0,1,1,1,1,0,2,1,0);
        Householder instance = new Householder(A);
        System.out.println(instance.getQ());
        System.out.println(MatrixOps.prettify(instance.getR()));
        Matrix expResult = MatrixOps.multiply(instance.getQ(), instance.getR());
        MatrixOps.prettify(expResult);
        System.out.println(expResult);
        assertEquals(A, expResult);
    }
    
}

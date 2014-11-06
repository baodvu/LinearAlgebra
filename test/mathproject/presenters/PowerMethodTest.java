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
import mathproject.models.Vector;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bvu
 */
public class PowerMethodTest {
    
    public PowerMethodTest() {
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
/*
    @Test
    public void test2x2() {
        Matrix A = new Matrix(2, 2);
        A.put(2, -12, 1, -5);
        Vector x = new Vector(1, 1);
        PowerMethod pm = new PowerMethod(A, x, 100, 0.00005);
        //System.out.println(pm.getNorm());
        //System.out.println(pm.getV());
    }
    
    @Test
    public void test3x3() {
        Matrix A = new Matrix(3, 3);
        A.put(1,2,0,-2,1,2,1,3,1);
        Vector x = new Vector(1, 1, 1);
        PowerMethod pm = new PowerMethod(A, x, 10, 0.0000001);
        //System.out.println(pm.getNorm());
        //System.out.println(pm.getV());
    }*/
    
    @Test
    public void testNegative() {
        Matrix A = new Matrix(2,2);
        //A.put(1.7284562947211088, 1.3922099811482793, -1.4415219305470965, 1.7481003925156733);
        A.put(1, 1.1, 0, 2);
        Vector x = new Vector(1, 1);
        PowerMethod pm = new PowerMethod(A, x, 100, 0.00005);
        System.out.println(pm.getNorm());
        System.out.println(pm.getV());
        System.out.println(pm.getIterationsNeeded());
    }
    
    /*@Test
    public void test2x2Fail() {
        Matrix A = new Matrix(3, 3);
        A.put(2,0,0,0,2,0,0,0,1);
        Vector x = new Vector(1, 1, 1);
        PowerMethod pm = new PowerMethod(A, x, 100, 0.00000000005);
    }*/
    
}

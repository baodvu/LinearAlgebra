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

import java.util.LinkedList;
import java.util.List;
import mathproject.models.Point;
import mathproject.models.Vector;
import mathproject.models.functions.ExponentialFunction;
import mathproject.models.functions.Function;
import mathproject.models.functions.RationalFunction;
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
public class GaussNewtonTest {
    
    public GaussNewtonTest() {
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

    @Test
    public void testSomeMethod() {
        /*List<Point> dataset = new LinkedList<>();
        dataset.add(new Point(0.038, 0.050));
        dataset.add(new Point(0.194, 0.127));
        dataset.add(new Point(0.425, 0.094));
        dataset.add(new Point(0.626, 0.2122));
        dataset.add(new Point(1.253, 0.2729));
        dataset.add(new Point(2.500, 0.2665));
        dataset.add(new Point(3.740, 0.3317));
        Function rf = new RationalFunction();
        Vector beta = new Vector(0.9, 0.2, 0.2);
        GaussNewton gn = new GaussNewton();
        gn.setUp(dataset, rf, beta, 5);*/
    }
    
    @Test
    public void testGN2() {
        List<Point> dataset = new LinkedList<>();
        dataset.add(new Point(1, 3.2939));
        dataset.add(new Point(2, 4.2699));
        dataset.add(new Point(4, 7.1749));
        dataset.add(new Point(5, 9.3008));
        dataset.add(new Point(8, 20.259));
        Function rf = new ExponentialFunction();
        Vector beta = new Vector(2.50, 0.25);
        GaussNewton gn = new GaussNewton();
        gn.setUp(dataset, rf, beta, 5);
    }
    
}

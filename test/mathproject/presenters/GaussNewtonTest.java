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
import junit.framework.Assert;
import mathproject.models.Point;
import mathproject.models.Vector;
import mathproject.models.functions.ExponentialFunction;
import mathproject.models.functions.Function;
import mathproject.models.functions.LogarithmicFunction;
import mathproject.models.functions.QuadraticFunction;
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
    public void testQuadraticFit() {
        List<Point> dataset = new LinkedList<>();
        dataset.add(new Point(0.1, 2.0));
        dataset.add(new Point(2.4, -8.1));
        dataset.add(new Point(4.2, -1.1));
        dataset.add(new Point(0.55, 0.37));
        dataset.add(new Point(12.101, 0.735));
        Function rf = new QuadraticFunction();
        Vector beta = new Vector(1, 3, -1);
        GaussNewton gn = new GaussNewton();
        gn.setUp(dataset, rf, beta, 5);
        
        Vector expResult = new Vector(0.16,-2.0,0.85);
        double delta = 0.1;
        Assert.assertEquals(expResult.get(1), gn.getBeta().get(1), delta);
        Assert.assertEquals(expResult.get(2), gn.getBeta().get(2), delta);
        Assert.assertEquals(expResult.get(3), gn.getBeta().get(3), delta);
    }

    @Test
    public void testExponentialFit() {
        List<Point> dataset = new LinkedList<>();
        dataset.add(new Point(0.0, -0.27));
        dataset.add(new Point(0.7, -0.354));
        dataset.add(new Point(-1.1, -0.185));
        dataset.add(new Point(2.1, -0.642));
        dataset.add(new Point(-0.51, -0.225));
        dataset.add(new Point(1.17, -0.429));
        dataset.add(new Point(-3.2, -0.11));
        Function rf = new ExponentialFunction();
        Vector beta = new Vector(-0.3, 0.3, 0.3);
        GaussNewton gn = new GaussNewton();
        gn.setUp(dataset, rf, beta, 5);
        
        Vector expResult = new Vector(-0.2, 0.5, -0.07);
        double delta = 0.1;
        Assert.assertEquals(expResult.get(1), gn.getBeta().get(1), delta);
        Assert.assertEquals(expResult.get(2), gn.getBeta().get(2), delta);
        Assert.assertEquals(expResult.get(3), gn.getBeta().get(3), delta);
    }

    @Test
    public void testLogarithmicFit() {
        List<Point> dataset = new LinkedList<>();
        dataset.add(new Point(0, -4.1));
        dataset.add(new Point(1, -4.47));
        dataset.add(new Point(2, -4.79));
        dataset.add(new Point(3, -5.09));
        dataset.add(new Point(4, -5.35));
        dataset.add(new Point(5, -5.6));
        dataset.add(new Point(10, -6.6));
        Function f = new LogarithmicFunction();
        Vector beta = new Vector(-2, 10, 5);
        GaussNewton gn = new GaussNewton();
        gn.setUp(dataset, f, beta, 5);
        
        Vector expResult = new Vector(-3.02, 7.8, 2.1);
        double delta = 0.1;
        Assert.assertEquals(expResult.get(1), gn.getBeta().get(1), delta);
        Assert.assertEquals(expResult.get(2), gn.getBeta().get(2), delta);
        Assert.assertEquals(expResult.get(3), gn.getBeta().get(3), delta);
    }
    
    @Test
    public void testRationalFit() {
        List<Point> dataset = new LinkedList<>();
        dataset.add(new Point(0.038, 0.050));
        dataset.add(new Point(0.194, 0.127));
        dataset.add(new Point(0.425, 0.094));
        dataset.add(new Point(0.626, 0.2122));
        dataset.add(new Point(1.253, 0.2729));
        dataset.add(new Point(2.500, 0.2665));
        dataset.add(new Point(3.740, 0.3317));
        Function rf = new RationalFunction();
        Vector beta = new Vector(0.9, 0.2, 0.1);
        GaussNewton gn = new GaussNewton();
        gn.setUp(dataset, rf, beta, 5);
    }
    
}

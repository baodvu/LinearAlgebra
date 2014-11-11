/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathproject.presenters;

import math.matrix.MatrixOps;
import math.matrix.Matrix;
import math.matrix.Vector;
import static math.matrix.MatrixOps.findPivotingRow;
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
public class MatrixOperationTest {

    public MatrixOperationTest() {
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
     * Test of multiply method, of class MatrixOps.
     */
    @Test
    public void testMultiplyTwoMatrices() {
        Matrix m1 = new Matrix(2, 3);
        m1.put(1, 2, 3, 4, 5, 6);
        Matrix m2 = new Matrix(3, 2);
        m2.put(7, 8, 9, 10, 11, 12);
        Matrix result = MatrixOps.multiply(m1, m2);
        Matrix expResult = new Matrix(2, 2);
        expResult.put(58, 64, 139, 154);
        assertEquals(expResult, result);
    }

    @Test
    public void testAddTwoMatrices() {
        Matrix m1 = new Matrix(2, 3);
        m1.put(1, 2, 3, 4, 5, 6);
        Matrix m2 = new Matrix(2, 3);
        m2.put(7, 8, 9, 10, 11, 12);
        Matrix result = MatrixOps.add(m1, m2);
        Matrix expResult = new Matrix(2, 3);
        expResult.put(8, 10, 12, 14, 16, 18);
        assertEquals(expResult, result);
    }

    @Test
    public void testTranspose() {
        Matrix m1 = new Matrix(2, 3);
        m1.put(1, 2, 3, 4, 5, 6);
        Matrix result = MatrixOps.transpose(m1);
        Matrix expResult = new Matrix(3, 2);
        expResult.put(1, 4, 2, 5, 3, 6);
        assertEquals(expResult, result);
    }

    @Test
    public void testRowReduceAndRank() {
        Matrix m1 = new Matrix(3, 4);
        m1.put(3, 2, -1, 1, 1, -2, 1, 0, -3, -2, 1, -1);
        m1.rowReduce();
        assertEquals(2, m1.rank());
    }

    @Test
    public void testInverse() {
        /*Matrix m1 = new Matrix(3, 3);
         m1.put(3.80676, -2.89412, 4.82744,-2.89412, 3.6979, -4.5931, 4.82744, -4.5931, 7.0);
         System.out.println(m1.getInverse());
         Matrix mInverse = m1.getInverse();
         //assertEquals(MatrixOps.prettify(MatrixOps.multiply(m1, mInverse)), MatrixOps.getIdentityMatrix(4));*/
    }

    @Test
    public void test2x2() {
        Matrix m = new Matrix(2, 2);
        m.put(1, 2, 3, 4);
        //System.out.println(MatrixOps.inverse2x2(m).multiply(m));
    }

}

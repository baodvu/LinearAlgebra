package math.matrix.factor;

import math.matrix.Matrix;

/**
 *
 * @author bvu
 */
public interface Factorization {

    public void perform(Matrix A);
    
    public void setA(Matrix A);

    public Matrix getA();

    public void calculate();

    public Matrix getQ();

    public Matrix getR();

    public Matrix getAInverse();

}

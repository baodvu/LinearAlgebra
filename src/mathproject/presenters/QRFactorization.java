package mathproject.presenters;

import mathproject.models.Matrix;

/**
 *
 * @author bvu
 */
public interface QRFactorization {
    
    public void setA(Matrix A);
    
    public Matrix getA();
    
    public void calculate();
    
    public Matrix getQ();
    
    public Matrix getR();
    
    public Matrix getAInverse();
    
}

package mathproject.presenters.factor;

import java.util.ArrayList;
import java.util.List;
import mathproject.models.Matrix;
import mathproject.models.Vector;
import mathproject.presenters.MatrixOps;

/**
 *
 * @author bvu
 */
public class GivensRotation implements QRFactorization {

    private Matrix A, Q, R;
    private List<Matrix> Gs;

    public GivensRotation(Matrix A) {
        setA(A);
        calculate();
    }

    @Override
    public void setA(Matrix A) {
        this.A = A;
    }

    @Override
    public Matrix getA() {
        return A;
    }

    @Override
    public void calculate() {
        Gs = new ArrayList<>();
        
        
    }

    @Override
    public Matrix getQ() {
        return Q;
    }

    @Override
    public Matrix getR() {
        return R;
    }

    @Override
    public Matrix getAInverse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

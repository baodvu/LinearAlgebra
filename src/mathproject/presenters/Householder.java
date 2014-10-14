package mathproject.presenters;

import java.util.ArrayList;
import java.util.List;
import mathproject.models.Matrix;
import mathproject.models.Vector;

/**
 *
 * @author bvu
 */
public class Householder implements QRFactorization {
    private Matrix A, Q, R;
    private List<Matrix> Hs = new ArrayList<>();
    
    public Householder(Matrix A) {
        setA(A);
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
        Matrix HA = A.copy();
        int rows = A.getNumberOfRows();
        int cols = A.getNumberOfCols();
        for (int c = 1; c < cols; c++) {
            Matrix HA_part = MatrixOps.extract(HA, c, c);
            Matrix x = HA_part.getColumn(1);
            Matrix e1 = MatrixOps.getColumnE(HA_part.getNumberOfRows(), 1);
            Matrix v = x.add(e1.multiply(x.vectorNorm()));
            Matrix u = v.multiply(1/v.vectorNorm());
            Matrix uT = u.copy().transpose();
            Matrix H_part = MatrixOps.getIdentityMatrix(HA_part.getNumberOfRows()).subtract(u.multiply(uT).multiply(2));
            Matrix H = MatrixOps.getIdentityMatrix(rows);
            MatrixOps.overwrite(H, H_part, c, c);
            Hs.add(H);
            HA = MatrixOps.multiply(H, HA);
        }
        R = HA;
        
        //Calculate Q
        Q = Hs.get(0);
        for (int i = 1; i < Hs.size(); i++) {
            Q = Q.multiply(Hs.get(1));
        }
        
        Matrix check = Q.copy().multiply(R);
        MatrixOps.prettify(check);
        System.out.println(check);
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

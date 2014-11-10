package mathproject.presenters.factor;

import java.util.ArrayList;
import java.util.List;
import mathproject.models.Matrix;

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
        Matrix GA = A.copy();
        int rows = A.getNumberOfRows();
        int cols = A.getNumberOfCols();
        for (int j = 1; j <= cols; j++) {
            for (int i = j + 1; i <= rows; i++) {
                if (GA.get(i, j) != 0) {
                    Matrix G = new Matrix(rows, cols);
                    double a = GA.get(j, j);
                    double b = GA.get(i, j);
                    double r = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
                    double c = a/r;
                    double s = -b/r;
                    System.out.println(r);
                    System.out.println(c);
                    System.out.println(s);
                    for (int row = 1; row <= rows; row++) {
                        for (int col = 1; col <= cols; col++) {
                            double value = 0;
                            if ((row == i && col == i) || (row == j && col == j)) {
                                value = c;
                            } else if (row == i && col == j) {
                                value = s;
                            } else if (row == j && col == i) {
                                value = -s;
                            } else if (row == col) {
                                value = 1;
                            }
                            G.put(row, col, value);
                        }
                    }
                    GA = G.multiply(GA);
                    if (GA.get(i, j) < 0.0000000001) {
                        GA.put(i, j, 0);
                    }
                    System.out.println(GA);
                }
            }
        }
        
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

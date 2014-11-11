package math.matrix.factor;

import java.util.ArrayList;
import java.util.List;
import math.matrix.Matrix;
import math.matrix.MatrixOps;

/**
 *
 * @author bvu
 */
public class GivensRotation implements Factorization {

    private Matrix A, Q, R;
    private List<Matrix> Gs;

    public GivensRotation() {
    }

    public GivensRotation(Matrix A) {
        perform(A);
    }

    @Override
    public void perform(Matrix A) {
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
        int rows = A.getNumberOfRows();
        int cols = A.getNumberOfCols();
        int n = Math.max(rows, cols);
        if (rows != cols) {
            Matrix B = new Matrix(n, n);
            MatrixOps.overwrite(B, A, 1, 1);
            A = B;
        }
        Gs = new ArrayList<>();
        Matrix GA = A.copy();
        for (int j = 1; j <= n; j++) {
            for (int i = j + 1; i <= n; i++) {
                if (GA.get(i, j) != 0) {
                    Matrix G = new Matrix(n, n);
                    double a = GA.get(j, j);
                    double b = GA.get(i, j);
                    double r = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
                    double c = a / r;
                    double s = -b / r;
                    for (int row = 1; row <= n; row++) {
                        for (int col = 1; col <= n; col++) {
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
                    Gs.add(G);
                }
            }
        }
        R = new Matrix(rows, cols);
        for (int i = 1; i <= rows; i++)
            for (int j = 1; j <= cols; j++)
                R.put(i, j, GA.get(i, j));
                
        Q = Gs.get(0).transpose();
        for (int i = 1; i < Gs.size(); i++) {
            Q = Q.multiply(Gs.get(i).transpose());
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

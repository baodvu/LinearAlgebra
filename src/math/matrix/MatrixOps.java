package math.matrix;

import math.matrix.Matrix;
import math.matrix.Vector;

/**
 * Performs basic matrix operation
 *
 * @author bvu
 */
public class MatrixOps {

    public static Matrix copy(Matrix m1) {
        Matrix m = new Matrix(m1.getNumberOfRows(), m1.getNumberOfCols());
        for (int r = 1; r <= m.getNumberOfRows(); r++) {
            for (int c = 1; c <= m.getNumberOfCols(); c++) {
                m.put(r, c, m1.get(r, c));
            }
        }
        return m;
    }

    public static Matrix extract(Matrix m1, int r1, int c1) {
        Matrix m = new Matrix(m1.getNumberOfRows() - r1 + 1, m1.getNumberOfCols() - c1 + 1);
        for (int r = r1; r <= m1.getNumberOfRows(); r++) {
            for (int c = c1; c <= m1.getNumberOfCols(); c++) {
                m.put(r - r1 + 1, c - c1 + 1, m1.get(r, c));
            }
        }
        return m;
    }

    public static void overwrite(Matrix target, Matrix source, int r1, int c1) {
        for (int r = 1; r <= source.getNumberOfRows(); r++) {
            for (int c = 1; c <= source.getNumberOfCols(); c++) {
                target.put(r + r1 - 1, c + c1 - 1, source.get(r, c));
            }
        }
    }

    public static Matrix prettify(Matrix m) { //AKA Round-up
        for (int r = 1; r <= m.getNumberOfRows(); r++) {
            for (int c = 1; c <= m.getNumberOfCols(); c++) {
                m.put(r, c, (double) Math.round(m.get(r, c) * 100000) / 100000);
            }
        }
        return m;
    }

    public static Matrix add(Matrix m1, Matrix m2) {
        if (m1.getNumberOfRows() != m2.getNumberOfRows() || m1.getNumberOfCols() != m2.getNumberOfCols()) {
            throw new IllegalArgumentException("Size mismatch");
        }

        Matrix result = new Matrix(m1.getNumberOfRows(), m2.getNumberOfCols());
        for (int r = 1; r <= result.getNumberOfRows(); r++) {
            for (int c = 1; c <= result.getNumberOfCols(); c++) {
                result.put(r, c, m1.get(r, c) + m2.get(r, c));
            }
        }
        return result;
    }

    public static Matrix subtract(Matrix m1, Matrix m2) {
        if (m1.getNumberOfRows() != m2.getNumberOfRows() || m1.getNumberOfCols() != m2.getNumberOfCols()) {
            throw new IllegalArgumentException("Size mismatch");
        }

        Matrix result = new Matrix(m1.getNumberOfRows(), m2.getNumberOfCols());
        for (int r = 1; r <= result.getNumberOfRows(); r++) {
            for (int c = 1; c <= result.getNumberOfCols(); c++) {
                result.put(r, c, m1.get(r, c) - m2.get(r, c));
            }
        }
        return result;
    }

    public static Matrix multiply(Matrix m1, double scalar) {
        Matrix result = new Matrix(m1.getNumberOfRows(), m1.getNumberOfCols());
        for (int r = 1; r <= result.getNumberOfRows(); r++) {
            for (int c = 1; c <= result.getNumberOfCols(); c++) {
                result.put(r, c, m1.get(r, c) * scalar);
            }
        }
        return result;
    }

    public static Matrix multiply(Matrix m1, Matrix m2) {
        if (m1.getNumberOfCols() != m2.getNumberOfRows()) {
            throw new IllegalArgumentException("Can't perform multiplication because of mismatched sizes");
        }

        Matrix product = new Matrix(m1.getNumberOfRows(), m2.getNumberOfCols());
        for (int r = 1; r <= product.getNumberOfRows(); r++) {
            for (int c = 1; c <= product.getNumberOfCols(); c++) {
                double newValue = 0;
                for (int i = 1; i <= m1.getNumberOfCols(); i++) {
                    newValue += m1.get(r, i) * m2.get(i, c);
                }
                product.put(r, c, newValue);
            }
        }
        return product;
    }

    public static Matrix transpose(Matrix m1) {
        Matrix result = new Matrix(m1.getNumberOfCols(), m1.getNumberOfRows());
        for (int r = 1; r <= result.getNumberOfRows(); r++) {
            for (int c = 1; c <= result.getNumberOfCols(); c++) {
                result.put(r, c, m1.get(c, r));
            }
        }
        return result;
    }

    public static void scale(Matrix m1, int row, double scalar) {
        for (int c = 1; c <= m1.getNumberOfCols(); c++) {
            m1.put(row, c, m1.get(row, c) * scalar);
        }
    }

    public static void swap(Matrix m1, int row1, int row2) {
        for (int c = 1; c <= m1.getNumberOfCols(); c++) {
            double temp = m1.get(row1, c);
            m1.put(row1, c, m1.get(row2, c));
            m1.put(row2, c, temp);
        }
    }

    public static void pivot(Matrix m1, int row1, int row2) {
        for (int c = 1; c <= m1.getNumberOfCols(); c++) {
            m1.put(row1, c, m1.get(row1, c) + m1.get(row2, c));
        }
    }

    public static int findPivotingRow(Matrix m1, int col) {
        for (int r = 1; r <= m1.getNumberOfRows(); r++) {
            double value = m1.get(r, col);
            if (value != 0) {
                return r;
            }
        }
        return -1;
    }

    public static int findPivotingRow(Matrix m1, int col, int fromRow) {
        for (int r = fromRow; r <= m1.getNumberOfRows(); r++) {
            double value = m1.get(r, col);
            if (value != 0) {
                return r;
            }
        }
        return -1;
    }

    public static void rowReduce(Matrix m1) {
        int rowDone = 1;
        for (int c = 1; c < m1.getNumberOfCols(); c++) {
            int pivotingRow = findPivotingRow(m1, c, rowDone);
            if (pivotingRow != -1) {
                m1.swap(rowDone, pivotingRow);
                m1.scale(rowDone, 1 / m1.get(rowDone, c));
                for (int r = rowDone + 1; r <= m1.getNumberOfRows(); r++) {
                    if (m1.get(r, c) != 0) {
                        m1.scale(r, -1 / m1.get(r, c));
                        m1.pivot(r, rowDone);
                    }
                }
                rowDone++;
            }
        }
    }

    public static void rowEchelonReduce(Matrix m1) {
        rowReduce(m1);
        for (int r = m1.getNumberOfRows(); r > 0; r--) {
            int c = 1;
            while (c <= m1.getNumberOfCols() && m1.get(r, c) == 0) {
                c++;
            }
            if (m1.get(r, c) != 0) {
                for (int rUp = r - 1; rUp > 0; rUp--) {
                    if (m1.get(rUp, c) != 0) {
                        m1.scale(r, -1 / m1.get(r, c) * m1.get(rUp, c));
                        m1.pivot(rUp, r);
                    }
                }
            }
            m1.scale(r, 1 / m1.get(r, c));
        }
    }

    public static Matrix getInverse(Matrix m1) {
        int n = m1.getNumberOfCols();
        Matrix m = new Matrix(n, 2 * n);
        overwrite(m, m1, 1, 1);
        overwrite(m, getIdentityMatrix(n), 1, n + 1);
        rowEchelonReduce(m);
        return extract(m, 1, n + 1);
    }

    public static int rank(Matrix m1) {
        Matrix rowReduceM = copy(m1);
        rowReduceM.rowReduce();
        int rank = rowReduceM.getNumberOfRows();
        for (int r = rowReduceM.getNumberOfRows(); r > 0; r--) {
            if (rowReduceM.get(r, rowReduceM.getNumberOfCols()) == 0) {
                rank--;
            } else {
                return rank;
            }
        }
        return rank;
    }

    public static Matrix getIdentityMatrix(int n) {
        Matrix m1 = new Matrix(n, n);
        for (int i = 1; i <= n; i++) {
            m1.put(i, i, 1);
        }
        return m1;
    }

    public static Matrix getColumnE(int n, int c) {
        Matrix m1 = new Matrix(n, 1);
        m1.put(c, 1, 1);
        return m1;
    }

    public static Vector toVector(Matrix m) {
        double[] values = new double[m.getNumberOfRows()];
        for (int i = 0; i < values.length; i++) {
            values[i] = m.get(i + 1, 1);
        }
        return new Vector(values);
    }

    public static double determinant2x2(Matrix m) {
        return m.get(1, 1) * m.get(2, 2) - m.get(2, 1) * m.get(1, 2);
    }

    public static Matrix inverse2x2(Matrix m) {
        Matrix ret = new Matrix(2, 2);
        ret.put(m.get(2, 2), -m.get(1, 2), -m.get(2, 1), m.get(1, 1));
        return multiply(ret, 1 / determinant2x2(m));
    }

    public static double trace(Matrix m) {
        double sum = 0;
        int max = Math.min(m.getNumberOfCols(), m.getNumberOfRows());
        for (int i = 1; i <= max; i++) {
            sum += m.get(i, i);
        }
        return sum;
    }

}

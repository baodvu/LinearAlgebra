package mathproject.models;

import java.util.Arrays;
import mathproject.presenters.MatrixOps;

/**
 * Holds values of and basic information about a matrix
 *
 * @author Bao
 */
public class Matrix {

    private int n; //number of rows
    private int m; //number of columns
    private double[][] matrix;

    public Matrix(int n, int m) {
        if (n < 1 || m < 1) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.m = m;
        matrix = new double[n][m];
    }

    public double get(int r, int c) {
        return matrix[r - 1][c - 1];
    }

    public Matrix getRow(int row) {
        Matrix m1 = new Matrix(1, m);
        for (int c = 1; c <= n; c++) {
            m1.put(row, c, get(row, c));
        }
        return m1;
    }

    public int getNumberOfRows() {
        return n;
    }

    public int getNumberOfCols() {
        return m;
    }

    public double vectorNormCol(int c) {
        return getColumn(c).vectorNorm();
    }

    public double vectorNorm() {
        double norm = 0;
        for (int i = 0; i < m * n; i++) {
            norm += Math.pow(matrix[i / m][i % m], 2.0);
        }
        return Math.sqrt(norm);
    }

    public Matrix getColumn(int col) {
        Matrix m1 = new Matrix(n, 1);
        for (int r = 1; r <= n; r++) {
            m1.put(r, col, get(r, col));
        }
        return m1;
    }

    public void put(int r, int c, double value) {
        matrix[r - 1][c - 1] = value;
    }

    public void put(double... values) {
        for (int i = 0; i < values.length; i++) {
            matrix[i / m][i % m] = values[i];
        }
    }

    public Matrix copy() {
        return MatrixOps.copy(this);
    }

    public Matrix add(Matrix m2) {
        return MatrixOps.add(this, m2);
    }

    public Matrix subtract(Matrix m2) {
        return MatrixOps.subtract(this, m2);
    }

    public Matrix multiply(Matrix m2) {
        return MatrixOps.multiply(this, m2);
    }

    public Matrix multiply(double scalar) {
        return MatrixOps.multiply(this, scalar);
    }

    public Matrix transpose() {
        return MatrixOps.transpose(this);
    }

    public void scale(int row, double scalar) {
        MatrixOps.scale(this, row, scalar);
    }

    public void swap(int row1, int row2) {
        MatrixOps.swap(this, row1, row2);
    }

    public void pivot(int row1, int row2) {
        MatrixOps.pivot(this, row1, row2);
    }

    public int findPivotingRow(int col) {
        return MatrixOps.findPivotingRow(this, col);
    }

    public void rowReduce() {
        MatrixOps.rowReduce(this);
    }

    public void rowEchelonReduce() {
        MatrixOps.rowEchelonReduce(this);
    }

    public Matrix getInverse() {
        return MatrixOps.getInverse(this);
    }

    public int rank() {
        return MatrixOps.rank(this);
    }

    @Override
    public String toString() {
        String s = "Matrix " + n + "x" + m + ":\n{";
        for (int r = 1; r <= n; r++) {
            s += "{";
            for (int c = 1; c <= m; c++) {
                s += get(r, c);
                if (c != m) {
                    s += ", ";
                }
            }
            s += "}";
            if (r != n) {
                s += ",\n";
            }
        }
        s += "}\n";
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        Matrix mat2 = (Matrix) obj;
        if (n != mat2.getNumberOfRows() || m != mat2.getNumberOfCols()) {
            return false;
        }
        boolean equality = true;
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= m; c++) {
                if (get(r, c) != mat2.get(r, c)) {
                    equality = false;
                }
            }
        }
        return equality;
    }
}

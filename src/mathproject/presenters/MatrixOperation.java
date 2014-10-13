package mathproject.presenters;

import mathproject.models.Matrix;

/**
 * Performs basic matrix operation
 * @author bvu
 */
public class MatrixOperation {
    
    public static Matrix add(Matrix m1, Matrix m2) {
        if (m1.getNumberOfRows() != m2.getNumberOfRows() || m1.getNumberOfCols() != m2.getNumberOfCols()) throw new IllegalArgumentException("Size mismatch");
        
        Matrix result = new Matrix(m1.getNumberOfRows(), m2.getNumberOfCols());
        for (int r = 1; r <= result.getNumberOfRows(); r++) {
            for (int c = 1; c <= result.getNumberOfCols(); c++) {
                result.put(r, c, m1.get(r, c) + m2.get(r, c));
            }
        }
        return result;
    }
    
    public static Matrix subtract(Matrix m1, Matrix m2) {
        if (m1.getNumberOfRows() != m2.getNumberOfRows() || m1.getNumberOfCols() != m2.getNumberOfCols()) throw new IllegalArgumentException("Size mismatch");
        
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
        if (m1.getNumberOfCols() != m2.getNumberOfRows()) throw new IllegalArgumentException("Can't perform multiplication because of mismatched sizes");
        
        Matrix product = new Matrix(m1.getNumberOfRows(), m2.getNumberOfCols());
        for (int r = 1; r <= product.getNumberOfRows(); r++) {
            for (int c = 1; c <= product.getNumberOfCols(); c++) {
                double newValue = 0;
                for (int i = 1; i <= m1.getNumberOfCols(); i++) {
                    newValue += m1.get(r, i)*m2.get(i, c);
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
}

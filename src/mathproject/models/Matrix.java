/*
 * Copyright 2014 Bao.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mathproject.models;

import java.util.Arrays;
import mathproject.presenters.MatrixOperation;

/**
 * Holds values of and basic information about a matrix
 * @author Bao
 */
public class Matrix {
    private int n; //number of rows
    private int m; //number of columns
    private double[][] matrix;
    
    public Matrix(int n, int m) {
        if (n < 1 || m < 1) throw new IllegalArgumentException();
        this.n = n;
        this.m = m;
        matrix = new double[n][m];
    }
    
    public double get(int r, int c) {
        return matrix[r-1][c-1];
    }
    
    public Matrix getRow(int row) {
        Matrix m1 = new Matrix(1, m);
        for (int c = 1; c <= n; c++)
            m1.put(row, c, get(row, c));
        return m1;
    }
    
    public int getNumberOfRows() {
        return n;
    }
    
    public int getNumberOfCols() {
        return m;
    }
    
    public Matrix getColumn(int col) {
        Matrix m1 = new Matrix(n, 1);
        for (int r = 1; r <= n; r++)
            m1.put(r, col, get(r, col));
        return m1;
    }
    
    public void put(int r, int c, double value) {
        matrix[r-1][c-1] = value;
    }
    
    public void put(double... values) {
        for (int i = 0; i < values.length; i++) {
            matrix[i/m][i%m] = values[i];
        }
    }
    
    public Matrix add(Matrix m2) {
        return MatrixOperation.add(this, m2);
    }
    
    public Matrix subtract(Matrix m2) {
        return MatrixOperation.subtract(this, m2);
    }
    
    public Matrix multiply(Matrix m2) {
        return MatrixOperation.multiply(this, m2);
    }

    public Matrix multiply(double scalar) {
        return MatrixOperation.multiply(this, scalar);
    }
    
    public Matrix transpose() {
        return MatrixOperation.transpose(this);
    }
    
    @Override
    public String toString() {
        String s = "Matrix " + n + "x" + m + ":\n{";
        for (int r = 1; r <= n; r++) {
            s += "{";
            for (int c = 1; c <= m; c++) {
                s += get(r, c);
                if (c != m) s += ", ";
            }
            s += "}";
            if (r != n) s += ",\n";
        }
        s += "}\n";
        return s;
    }
    
    @Override
    public boolean equals(Object obj) {
        Matrix mat2 = (Matrix) obj;
        if (n != mat2.getNumberOfRows() || m != mat2.getNumberOfCols()) return false;
        boolean equality = true;
        for (int r = 1; r <= n; r++)
            for (int c = 1; c <= m; c++)
                if (get(r,c) != mat2.get(r, c)) {
                    equality = false;
                }
        return equality;
    }
}

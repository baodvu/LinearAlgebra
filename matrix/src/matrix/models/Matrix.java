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
package matrix.models;

/**
 * Holds values of and basic information about a matrix
 * @author Bao
 */
public class Matrix {
    public int n; //number of rows
    public int m; //number of columns
    public double[][] matrix;
    
    public Matrix(int n, int m) {
        if (n < 1 || m < 1) throw new IllegalArgumentException();
        this.n = n;
        this.m = m;
        matrix = new double[n][m];
    }
    
    public double get(int n, int m) {
        return matrix[n][m];
    }
    
    public void put(int n, int m, double value) {
        matrix[n][m] = value;
    }
    
}

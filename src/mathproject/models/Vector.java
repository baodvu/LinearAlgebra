package mathproject.models;

import java.util.Arrays;

/**
 * Holds values of and basic information about a vector
 * @author Bao
 */
public class Vector extends Matrix{
    
    public Vector(double... c) {
        super(c.length, 1);
        super.put(c);
    }
    
    public int size() {
        return super.getNumberOfRows();
    }
    
    public double get(int i) {
        return super.get(i, 1);
    }
    
    public Vector add(Vector m2) {
        return (Vector) super.add((Matrix) m2);
    }
    public Vector subtract(Vector m2) {
        return (Vector) super.subtract((Matrix) m2);
    }
    public Vector multiply(Vector m2) {
        return (Vector) super.multiply((Matrix) m2);
    }
    public Vector multiply(double scalar) {
        return (Vector) super.multiply(scalar);
    }
    public Matrix transpose() {
        return super.transpose();
    }
    public double dotProduct(Vector v2) {
        return super.transpose().multiply(v2).get(1, 1);
    }
    
    public double norm() {
        double norm = 0;
        for (int i = 1; i <= size(); i++) {
            norm += Math.pow(get(i), 2.0);
        }
        return Math.sqrt(norm);
    }
    
    @Override
    public boolean equals(Object obj) {
        Vector vec2 = (Vector) obj;
        if (size() != vec2.size()) return false;
        boolean equality = true;
        for (int i = 1; i <= size(); i++)
            if (get(i) != vec2.get(i)) {
                equality = false;
            }
        return equality;
    }
    
}

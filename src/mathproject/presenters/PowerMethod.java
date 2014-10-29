package mathproject.presenters;

import mathproject.models.Matrix;
import mathproject.models.Vector;

/**
 *
 * @author bvu
 */
public class PowerMethod {
    Matrix A;
    Vector v;
    double norm;
    int iterations;
    double epsilon;
    int iterationsNeeded;
    
    public PowerMethod(Matrix A, Vector initialVector, int iterations, double epsilon) {
        this.A = A;
        this.v = initialVector;
        this.iterations = iterations;
        this.epsilon = epsilon;
        norm = v.norm();
        process();
    }
    
    private void process() {
        boolean inEpsilonRange = false;
        int i = 0;
        while (i < iterations && !inEpsilonRange) {
            i++;
            v = MatrixOps.toVector(A.multiply(v));
            double temp = v.norm();
            if (Math.abs(temp - norm) < epsilon) {
                inEpsilonRange = true;
            }
            norm = v.norm();
            v = v.multiply(1/norm);
        }
        if (!inEpsilonRange && i >= iterations) {
            v = null;
            iterationsNeeded = -1;
            //System.out.println("Failure to converge the vector in the given number of iterations.");
        } else {
            iterationsNeeded = i;
            //System.out.println("Approximation done in " + i + " iterations. Eigenvalue is " + norm + " and eigenvector is: " + v);
        }
    }

    public Vector getV() {
        return v;
    }

    public double getNorm() {
        return norm;
    }

    public int getIterationsNeeded() {
        return iterationsNeeded;
    }

    public void setIterationsNeeded(int iterationsNeeded) {
        this.iterationsNeeded = iterationsNeeded;
    }
    
}

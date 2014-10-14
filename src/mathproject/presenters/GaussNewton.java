package mathproject.presenters;

import java.util.LinkedList;
import java.util.List;
import mathproject.models.Matrix;
import mathproject.models.Point;
import mathproject.models.Vector;
import mathproject.models.functions.Function;

/**
 *
 * @author bvu
 */
public class GaussNewton {
    private List<Point> dataTable;
    private Function function;
    private int N;
    private Vector beta, r;
    private Matrix J;

    public GaussNewton() {
        
    }   
    
    public void setUp(List<Point> l, Function f, Vector v, int n) {
        this.dataTable = l;
        this.function = f;
        this.N = n;
        this.beta = v;
        process();
    }
    
    public void process() {
        for (int n = 1; n <= N; n++) {
            r = getR();
            J = new Matrix(dataTable.size(), beta.size());//7x2
            for (int i = 1; i <= J.getNumberOfRows(); i++) {
                for (int j = 1; j <= J.getNumberOfCols(); j++) {
                    J.put(i, j, -function.getPartialDerivative(j, beta, dataTable.get(i-1).getX()));
                }
            }
            beta = calculateBetaEnhanced();
            //MatrixOps.prettify(beta);
            System.out.println(beta);
            System.out.println(Math.pow(r.norm(), 2));
        }
    }
    
    public Vector calculateBeta() {
        return MatrixOps.toVector(beta.subtract(J.transpose().multiply(J).getInverse().multiply(J.transpose()).multiply(r)));
    }
        
    public Vector calculateBetaEnhanced() {
        Householder hh = new Householder(J);
        return MatrixOps.toVector(beta.subtract(hh.getR().getInverse().multiply(hh.getQ().transpose()).multiply(r)));
    }
    
    private Vector getR() {
        double[] rValues = new double[dataTable.size()];
        for (int i = 0; i < rValues.length; i++) {
            rValues[i] = calculateRInOneDimension(i);
        }
        return new Vector(rValues);
    }
            
    private double calculateRInOneDimension(int i) {
        return dataTable.get(i).getY() - function.getValue(beta, dataTable.get(i).getX());
    }
    
}
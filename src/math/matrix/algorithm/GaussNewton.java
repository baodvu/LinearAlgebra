package math.matrix.algorithm;

import math.matrix.MatrixOps;
import math.matrix.factor.Householder;
import java.util.LinkedList;
import java.util.List;
import math.matrix.Matrix;
import math.matrix.Point;
import math.matrix.Vector;
import math.function.Function;
import math.matrix.factor.Factorization;
import math.matrix.factor.FactorizationProcessor;

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
    private Factorization fact = FactorizationProcessor.INSTANCE;

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
                    J.put(i, j, -function.getPartialDerivative(j, beta, dataTable.get(i - 1).getX()));
                }
            }
            calculateBeta2();
        }
    }

    public void calculateBeta() {
        Matrix temp = MatrixOps.prettify(J.transpose().multiply(J));
        temp = MatrixOps.prettify(temp.getInverse());
        temp = temp.multiply(MatrixOps.prettify(J.transpose().multiply(MatrixOps.prettify(r))));
        temp = MatrixOps.prettify(temp);
        System.out.println(temp);
    }

    public void calculateBeta2() {
        System.out.println(J);
        fact.perform(J);
        Matrix QT = fact.getQ().transpose();
        Vector Qr = MatrixOps.toVector(QT.multiply(r));
        Matrix R = fact.getR();
        double x3 = Qr.get(3) / R.get(3, 3);
        double x2 = (Qr.get(2) - R.get(2, 3) * x3) / R.get(2, 2);
        double x1 = (Qr.get(1) - R.get(1, 2) * x2 - R.get(1, 3) * x3) / R.get(1, 1);
        beta = new Vector(beta.get(1) - x1, beta.get(2) - x2, beta.get(3) - x3);
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

    public Vector getBeta() {
        return beta;
    }
}

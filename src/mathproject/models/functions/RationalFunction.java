package mathproject.models.functions;

import mathproject.models.Vector;

/**
 *
 * @author bvu
 */
public class RationalFunction implements Function {

    @Override
    public double getValue(Vector v, double x) {
        return (v.get(1)*x)/(x + v.get(2)) + v.get(3);
    }

    @Override
    public double getPartialDerivative(int j, Vector v, double x) {
        switch (j) {
            case 1: return getPartialDerivativeA(v, x);
            case 2: return getPartialDerivativeB(v, x);
            case 3: return getPartialDerivativeC(v, x);
        }
        return 0;
    }

    @Override
    public double getPartialDerivativeA(Vector v, double x) {
        return x/(x + v.get(2));
    }

    @Override
    public double getPartialDerivativeB(Vector v, double x) {
        return -(v.get(1) * x)/Math.pow(x + v.get(2), 2);
    }

    @Override
    public double getPartialDerivativeC(Vector v, double x) {
        return 1;
    }

}

package Controller;

/**
 * Created by Lion on 04.04.2016.
 */
public class Function {

    private final static double EPSILON = 0.0001;

    public double getY(double x, int a, int b) {
        double result = 0;
        double y;
        for(int i = 1; ; ++i) {
            y = Math.pow(-1, i - 1) * Math.sin(i * (a * x - b)) / i;
            if(Math.abs(y) < EPSILON) {
                break;
            }
            result += y;
        }
        return result;
    }
}

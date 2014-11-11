package math.presenters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import math.Main;
import math.function.ExponentialFunction;
import math.function.Function;
import math.function.LogarithmicFunction;
import math.function.QuadraticFunction;
import math.function.RationalFunction;
import math.matrix.Matrix;
import math.matrix.MatrixOps;
import math.matrix.Point;
import math.matrix.Vector;
import math.matrix.algorithm.GaussNewton;
import math.matrix.algorithm.PowerMethod;
import math.matrix.factor.FactorizationProcessor;
import math.matrix.factor.GivensRotation;
import math.matrix.factor.Householder;

/**
 *
 * @author Bao
 */
public class MainWindowController implements Initializable {

    private Main application;
    private final FileChooser fileChooser = new FileChooser();

    @FXML
    private TextArea input, output;
    @FXML
    private TextField numIterations, a, b, c;
    @FXML
    private ChoiceBox eqChoiceBox, letterCBox;
    @FXML
    private Canvas canvas, canvas2, canvas3;
    private GraphicsContext gc, gc2;
    private int MAX_NUMBER_OF_ITERATIONS = 100;
    private double TOLERANCE = 0.00005;
    private int windowDim = 500;
    private int ratio = 50;
    private List<Point> dataset = new LinkedList<>();
    private Timer timer;

    /**
     * Links to main application
     *
     * @param application
     */
    public void setApp(Main application) {
        this.application = application;
    }

    public Matrix generateMatrix() {
        Random r = new Random();
        Matrix m = new Matrix(2, 2);
        do {
            m.put(r.nextDouble() * 4 - 2, r.nextDouble() * 4 - 2, r.nextDouble() * 4 - 2, r.nextDouble() * 4 - 2);
        } while (MatrixOps.determinant2x2(m) == 0.0);
        return m;
    }

    public void plot() {

        for (int i = 1; i <= 2000; i++) {
            Matrix m = generateMatrix();
            //System.out.println(m);
            Vector x = new Vector(1, 1);
            double det = MatrixOps.determinant2x2(m);
            double tr = MatrixOps.trace(m);
            PowerMethod pm = new PowerMethod(m, x, MAX_NUMBER_OF_ITERATIONS, TOLERANCE);
            int n = pm.getIterationsNeeded();
            //System.out.println(det + " " + tr + " " + n);
            gc = canvas.getGraphicsContext2D();
            if (n > 0) {
                plotPoint((int) (det * ratio), (int) (tr * ratio), n);
                /*if (tr*tr - 4*det < 0) {
                    System.out.println(m);
                }*/
            }

            gc = canvas2.getGraphicsContext2D();
            Matrix mInverse = MatrixOps.inverse2x2(m);
            x = new Vector(1, 1);
            pm = new PowerMethod(mInverse, x, MAX_NUMBER_OF_ITERATIONS, TOLERANCE);
            n = pm.getIterationsNeeded();
            //det = MatrixOps.determinant2x2(mInverse);
            //tr = MatrixOps.trace(mInverse);
            if (n > 0) {
                plotPoint((int) (det * ratio), (int) (tr * ratio), n);
            }
        }

        gc = canvas.getGraphicsContext2D();
        plotAxes();
        gc = canvas2.getGraphicsContext2D();
        plotAxes();

    }

    public void plotAxes() {
        gc.setFill(Color.BLACK);
        strokeText("0", -8, -12);
        strokeText("1", ratio - 8, -12);
        strokeText("2", 2 * ratio - 8, -12);
        strokeText("3", 3 * ratio - 8, -12);
        strokeText("4", 4 * ratio - 8, -12);
        strokeText("5", 5 * ratio - 8, -12);
        strokeText("6", 6 * ratio - 8, -12);
        strokeText("-1", -ratio - 8, -12);
        strokeText("-2", -2 * ratio - 8, -12);
        strokeText("-3", -3 * ratio - 8, -12);
        strokeText("-4", -4 * ratio - 8, -12);
        strokeText("-5", -5 * ratio - 8, -12);
        strokeText("det(A)", 7 * ratio - 8, -12);

        strokeText("1", -8, ratio - 12);
        strokeText("2", -8, 2 * ratio - 12);
        strokeText("3", -8, 3 * ratio - 12);
        strokeText("-1", -8, -ratio - 12);
        strokeText("-2", -8, -2 * ratio - 12);
        strokeText("-3", -8, -3 * ratio - 12);
        strokeText("tr(A)", -28, 4 * ratio - 12);

        strokeLine(-windowDim, 0, windowDim, 0);
        strokeLine(0, -windowDim, 0, windowDim);
    }

    public void strokeLine(int x1, int y1, int x2, int y2) {
        gc.strokeLine(translateX(x1), translateY(y1), translateX(x2), translateY(y2));
    }

    public void strokeText(String s, int x, int y) {
        gc.strokeText(s, translateX(x), translateY(y));
    }

    public void plotPoint(int x, int y, int i) {
        gc.setFill(Color.color(0, 1 - ((double) i / MAX_NUMBER_OF_ITERATIONS), 0 + ((double) i / MAX_NUMBER_OF_ITERATIONS)));
        gc.fillRect(translateX(x) - 1, translateY(y) - 1, 3, 3);
    }

    public void plotPoint(int x, int y) {
        gc.fillRect(translateX(x) - 1, translateY(y) - 1, 3, 3);
    }

    public int translateX(int x) {
        return 360 + x;
    }

    public int translateY(int y) {
        return 230 - y;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eqChoiceBox.setItems(FXCollections.observableArrayList(
                "Quadratic", "Exponential", "Logarithmic", "Rational"
        ));
        eqChoiceBox.getSelectionModel().selectFirst();
        plot();
        drawLetter();
    }

    @FXML
    private void handleOpenFile(ActionEvent e) {
        File file = fileChooser.showOpenDialog(application.getStage());
        if (file != null) {
            openFile(file);
        }
    }

    private void openFile(File f) {
        input.setText(readFile(f));
    }

    @FXML
    private void calculateGN(ActionEvent e) {
        try {
            Scanner sLine = new Scanner(input.getText());
            String line = null;
            dataset = new LinkedList<>();
            while (sLine.hasNextLine()) {
                line = sLine.nextLine();
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(" |,");
                if (scanner.hasNextDouble()) {
                    dataset.add(new Point(scanner.nextDouble(), scanner.nextDouble()));
                }
            }
            Function rf;
            switch (eqChoiceBox.getSelectionModel().getSelectedIndex()) {
                case 0:
                    rf = new QuadraticFunction();
                    break;
                case 1:
                    rf = new ExponentialFunction();
                    break;
                case 2:
                    rf = new LogarithmicFunction();
                    break;
                case 3:
                    rf = new RationalFunction();
                    break;
                default:
                    rf = new QuadraticFunction();
            }
            Vector beta = new Vector(Double.parseDouble(a.getText()), Double.parseDouble(b.getText()), Double.parseDouble(c.getText()));
            GaussNewton gn = new GaussNewton();
            gn.setUp(dataset, rf, beta, Integer.parseInt(numIterations.getText()));

            beta = gn.getBeta();
            output.setText(String.format("a = %.3f; b = %.3f; c = %.3f", beta.get(1), beta.get(2), beta.get(3)));
        } catch (Exception ex) {
            output.setText("INVALID INPUT");
        }
    }

    private String readFile(File file) {
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));

            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(text + "\n");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return stringBuffer.toString();
    }
    
    @FXML
    private void useHouseholder(ActionEvent event) {
        FactorizationProcessor.setEngine(new Householder());
    }

    @FXML
    private void useGivensRotation(ActionEvent event) {
        FactorizationProcessor.setEngine(new GivensRotation());
    }
    
    private void drawLetter() {
        letterCBox.setItems(FXCollections.observableArrayList(
                "Letter L", "Letter U", "Letter Z"
        ));
        
        LinkedList<Vector> letterL = new LinkedList<>();
        letterL.add(new Vector(0,0,0));
        letterL.add(new Vector(4,0,0));
        letterL.add(new Vector(4,2,0));
        letterL.add(new Vector(2,2,0));
        letterL.add(new Vector(2,6,0));
        letterL.add(new Vector(0,6,0));
        
        LinkedList<Vector> letterU = new LinkedList<>();
        letterU.add(new Vector(0,0,0));
        letterU.add(new Vector(4,0,0));
        letterU.add(new Vector(4,6,0));
        letterU.add(new Vector(2.5,6,0));
        letterU.add(new Vector(2.5,1,0));
        letterU.add(new Vector(1.5,1,0));
        letterU.add(new Vector(1.5,6,0));
        letterU.add(new Vector(0,6,0));
        
        LinkedList<Vector> letterZ = new LinkedList<>();
        letterZ.add(new Vector(0,0,0));
        letterZ.add(new Vector(4,0,0));
        letterZ.add(new Vector(4,1,0));
        letterZ.add(new Vector(1,1,0));
        letterZ.add(new Vector(4,5,0));
        letterZ.add(new Vector(4,6,0));
        letterZ.add(new Vector(0,6,0));
        letterZ.add(new Vector(0,5,0));
        letterZ.add(new Vector(3,5,0));
        letterZ.add(new Vector(0,1,0));
        
        int r = 30;
        double theta = Math.PI/60;
        gc = canvas3.getGraphicsContext2D();
        
        Matrix Rx = new Matrix(3,3);
        Rx.put(1,0,0,0,Math.cos(theta),-Math.sin(theta),0,Math.sin(theta),Math.cos(theta));
        Matrix Ry = new Matrix(3,3);
        Ry.put(Math.cos(theta),0,Math.sin(theta),0,1,0,-Math.sin(theta),0,Math.cos(theta));
        Matrix Rz = new Matrix(3,3);
        Rz.put(Math.cos(theta),-Math.sin(theta),0,Math.sin(theta),Math.cos(theta),0,0,0,1);
        
        letterCBox.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                switch (letterCBox.getSelectionModel().getSelectedIndex()) {
                    case 0: animate(letterL, theta, r, Rx); break;
                    case 1: animate(letterU, theta, r, Ry); break;
                    case 2: animate(letterZ, theta, r, Rz);
                }
            }
        });
    }
    
    private void animate(LinkedList<Vector> letter, double theta, int r, Matrix R) {
        if (timer != null) timer.cancel();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        gc.clearRect(0, 0, canvas3.getWidth(), canvas3.getHeight());
                        drawSmallGraph(r);
                        drawLetter(letter, r);
                        rotateLetter(letter, theta, R);
                    }
                });
            }
        }, 0, 100);
    }
    
    private void rotateLetter(LinkedList<Vector> letter, double theta, Matrix R) {
        //http://en.wikipedia.org/wiki/Rotation_matrix
        for (int i = 0; i < letter.size(); i++) {
            Vector vertex = letter.get(i);
            vertex = MatrixOps.toVector(R.multiply(vertex));
            letter.remove(i);
            letter.add(i, vertex);
        }
    }
    
    private void drawLetter(LinkedList<Vector> letter, int r) {
        gc.setFill(Color.rgb((int)Color.AQUAMARINE.getRed()*255, (int)Color.AQUAMARINE.getGreen()*255,
                (int)Color.AQUAMARINE.getBlue()*255, 0.5));
        double[] xPoints = new double[letter.size()];
        double[] yPoints = new double[letter.size()];
        for (int i = 0; i < letter.size(); i++) {
            xPoints[i] = translateX((int) (letter.get(i).get(1)*r));
            yPoints[i] = translateY((int) (letter.get(i).get(2)*r));
        }
        gc.fillPolygon(xPoints, yPoints, letter.size());
        /*for (int i = 0; i < letter.size(); i++) {
            int next = (i + 1) % letter.size();
            System.out.println("Connecting " + i + " and " + next);
            strokeLine((int) letter.get(i).get(1)*r,(int) letter.get(i).get(2)*r,
                    (int) letter.get(next).get(1)*r,(int) letter.get(next).get(2)*r);
        }*/
    }
    
    public void drawSmallGraph(int r) {
        gc.setFill(Color.BLACK);
        strokeText("0", -8, -12);
        strokeText("1", r - 8, -12);
        strokeText("2", 2 * r - 8, -12);
        strokeText("3", 3 * r - 8, -12);
        strokeText("4", 4 * r - 8, -12);
        strokeText("5", 5 * r - 8, -12);
        strokeText("6", 6 * r - 8, -12);
        strokeText("-1", -r - 8, -12);
        strokeText("-2", -2 * r - 8, -12);
        strokeText("-3", -3 * r - 8, -12);
        strokeText("-4", -4 * r - 8, -12);
        strokeText("-5", -5 * r - 8, -12);
        strokeText("x", 8 * r - 8, -12);

        strokeText("1", -8, r - 4);
        strokeText("2", -8, 2 * r - 4);
        strokeText("3", -8, 3 * r - 4);
        strokeText("4", -8, 4 * r - 4);
        strokeText("5", -8, 5 * r - 4);
        strokeText("6", -8, 6 * r - 4);
        strokeText("-1", -8, -r - 4);
        strokeText("-2", -8, -2 * r - 4);
        strokeText("-3", -8, -3 * r - 4);
        strokeText("-4", -8, -4 * r - 4);
        strokeText("-5", -8, -5 * r - 4);
        strokeText("-6", -8, -6 * r - 4);
        strokeText("y", -8, 7 * r - 4);

        strokeLine(-windowDim, 0, windowDim, 0);
        strokeLine(0, -windowDim, 0, windowDim);
    }
}

package mathproject.presenters;

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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import mathproject.Main;
import mathproject.models.Matrix;
import mathproject.models.Point;
import mathproject.models.Vector;
import mathproject.models.functions.ExponentialFunction;
import mathproject.models.functions.Function;
import mathproject.models.functions.LogarithmicFunction;
import mathproject.models.functions.QuadraticFunction;
import mathproject.models.functions.RationalFunction;

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
    private ChoiceBox eqChoiceBox;
    @FXML
    private Canvas canvas, canvas2;
    private GraphicsContext gc, gc2;
    private int MAX_NUMBER_OF_ITERATIONS = 100;
    private double TOLERANCE = 0.00005;
    private int windowDim = 500;
    private int ratio = 50;
    private List<Point> dataset = new LinkedList<>();

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

        for (int i = 1; i <= 5000; i++) {
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
            }

            gc = canvas2.getGraphicsContext2D();
            Matrix mInverse = MatrixOps.inverse2x2(m);
            x = new Vector(1, 1);
            pm = new PowerMethod(mInverse, x, MAX_NUMBER_OF_ITERATIONS, TOLERANCE);
            n = pm.getIterationsNeeded();
            det = MatrixOps.determinant2x2(mInverse);
            tr = MatrixOps.trace(mInverse);
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
        strokeText("det(A)", 8 * ratio - 8, -12);

        strokeText("1", -8, ratio - 12);
        strokeText("2", -8, 2 * ratio - 12);
        strokeText("3", -8, 3 * ratio - 12);
        strokeText("4", -8, 4 * ratio - 12);
        strokeText("-1", -8, -ratio - 12);
        strokeText("-2", -8, -2 * ratio - 12);
        strokeText("-3", -8, -3 * ratio - 12);
        strokeText("tr(A)", -28, 5 * ratio - 12);

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
        return 300 + x;
    }

    public int translateY(int y) {
        return 250 - y;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eqChoiceBox.setItems(FXCollections.observableArrayList(
                "Quadratic", "Exponential", "Logarithmic", "Rational"
        ));
        eqChoiceBox.getSelectionModel().selectFirst();
        plot();
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

            output.setText(gn.getBeta().toString());
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
}

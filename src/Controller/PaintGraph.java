package Controller;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PaintGraph extends JPanel {
    private int valueOfDivision;
    private int initialIndentationY;
    private int initialIndentationX;
    private int axisLengthY;
    private int axisLengthX;
    private int temp2;

    private int a=0;
    private int b=0;
    private int minValue=0;
    private int maxValue=0;
    double prevX;
    double prevY;

    private float scaleFactorX;
    private float scaleFactorY;
    private float tABStep;

    DefaultTableModel tableModel;

    public PaintGraph() {}

    public PaintGraph(DefaultTableModel tableModel, int a, int b, int min, int max) {
        valueOfDivision = 10;
        scaleFactorY = (float) 0.5;
        scaleFactorX = (float) 0.5;
        initialIndentationY = 50;
        initialIndentationX = 50;
        axisLengthY = 400;
        axisLengthX = 600;


        tABStep = (float) 0.011;
        this.tableModel=tableModel;
        this.a=a;
        this.b=b;
        minValue=min;
        maxValue=max;
    }

    public void paint(Graphics g) {

        super.paint(g);
        while (tableModel.getRowCount()>0) {
            tableModel.removeRow(0);
        }

        g.drawLine((int) (axisLengthX * scaleFactorX + initialIndentationX), initialIndentationY,
                (int) (axisLengthX * scaleFactorX + initialIndentationX), axisLengthY + initialIndentationY);

        g.drawLine((int) (axisLengthX * scaleFactorX + initialIndentationX), initialIndentationY,
                (int) (axisLengthX * scaleFactorX + initialIndentationX) - 3, initialIndentationY + 10);
        g.drawLine((int) (axisLengthX * scaleFactorX + initialIndentationX), initialIndentationY,
                (int) (axisLengthX * scaleFactorX + initialIndentationX) + 3, initialIndentationY + 10);

        g.drawString("Y", (int) (axisLengthX * scaleFactorX + initialIndentationX) - 10, initialIndentationY + 10);
        g.drawString("0", (int) (axisLengthX * scaleFactorX + initialIndentationX) - 10, (int) (axisLengthY * scaleFactorY + initialIndentationY) + 10);

        int temp1 = (int) (axisLengthY * scaleFactorY);
        temp2 = axisLengthY - temp1;
        int kof1 = (int) temp1 / valueOfDivision;
        int kof2 = (int) temp2 / valueOfDivision;
        for (int i = 1; i < kof1 + 1; i++) {
            g.drawLine((int) (axisLengthX * scaleFactorX - 2 + initialIndentationX), temp1 - valueOfDivision + initialIndentationY,
                    (int) (axisLengthX * scaleFactorX + 2 + initialIndentationX), temp1 - valueOfDivision + initialIndentationY);
            temp1 = temp1 - valueOfDivision;
        }
        temp1 = axisLengthY - temp2;
        for (int i = 1; i < kof2 + 1; i++) {
            g.drawLine((int) (axisLengthX * scaleFactorX - 2 + initialIndentationX), temp1 + valueOfDivision + initialIndentationY,
                    (int) (axisLengthX * scaleFactorX + 2 + initialIndentationX), temp1 + valueOfDivision + initialIndentationY);
            temp1 = temp1 + valueOfDivision;
        }

        g.drawLine(initialIndentationX, (int) (axisLengthY * scaleFactorY + initialIndentationY), axisLengthX + initialIndentationX, (int) (axisLengthY * scaleFactorY + initialIndentationY));
        g.drawLine(axisLengthX + initialIndentationX, (int) (axisLengthY * scaleFactorY + initialIndentationY), axisLengthX + initialIndentationX - 10,
                (int) (axisLengthY * scaleFactorY + initialIndentationY) - 3);
        g.drawLine(axisLengthX + initialIndentationX, (int) (axisLengthY * scaleFactorY + initialIndentationY), axisLengthX + initialIndentationX - 10,
                (int) (axisLengthY * scaleFactorY + initialIndentationY) + 3);

        g.drawString("Х", axisLengthX + initialIndentationY - 10, (int) (axisLengthY * scaleFactorY + initialIndentationY) - 10);

        temp1 = (int) (axisLengthX * scaleFactorX);
        temp2 = axisLengthX - temp1;
        kof1 = (int) temp1 / valueOfDivision;
        kof2 = (int) temp2 / valueOfDivision;
        for (int i = 1; i < kof1 + 1; i++) {
            g.drawLine(temp1 - valueOfDivision + initialIndentationX, (int) (axisLengthY * scaleFactorY - 2 + initialIndentationY),
                    temp1 - valueOfDivision + initialIndentationX, (int) (axisLengthY * scaleFactorY + 2 + initialIndentationY));
            temp1 = temp1 - valueOfDivision;
        }
        temp1 = axisLengthX - temp2;
        double xl = temp1 / valueOfDivision;
        double xl1 = temp2 / valueOfDivision;
        for (int i = 1; i < kof2 + 1; i++) {
            g.drawLine(temp1 + valueOfDivision + initialIndentationX, (int) (axisLengthY * scaleFactorY - 2 + initialIndentationY),
                    temp1 + valueOfDivision + initialIndentationX, (int) (axisLengthY * scaleFactorY + 2 + initialIndentationY));
            temp1 = temp1 + valueOfDivision;
        }

        ThreadGroup group = new ThreadGroup("A");
        Function fun = new Function();

        for(double x = minValue; x <= maxValue; x += 0.1) {
            if(x == minValue) {
                prevX = x;
                prevY = fun.getY(x, a, b);
            }

            final double finalX = x;
            final double finalX1 = x;
            Thread thread = new Thread(group, new Runnable() {
                @Override
                public void run() {
                    addAndDraw(finalX, fun.getY(finalX1, a, b), g);
                }
            });
            thread.run();
        }

    }

    private void inscriptions(int x, Graphics g) {
        int c=10;
        for (int i=0; i<x; i+=0.4) {
            g.drawString(i+"", (int) (axisLengthX * scaleFactorX + initialIndentationX) + c, (int) (axisLengthY * scaleFactorY + initialIndentationY) + 10);
            c+=10;
        }
    }

    public synchronized void addAndDraw(double x, double y, Graphics g) {
        Object[] row = {x, y};
        tableModel.addRow(row);
        g.drawLine(350 + (int) (prevX * 100), 250 - (int) (100 * prevY),
                350 + (int)(x * 100), 250 - (int)(y * 100));
        prevX = x;
        prevY = y;
    }

    public void setParam(int a, int b, int minValue, int maxValue) {
        while (tableModel.getRowCount()>0) {
            tableModel.removeRow(0);
        }
        this.a = a;
        this.b = b;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }


}

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
    private int sw;
    private int xln;
    private int l2;

    private int a=5;
    private int b=10;
    private int minValue=0;
    private int maxValue=2;
    double prevX;
    double prevY;

    private float scaleFactorX;
    private float scaleFactorY;
    private float tABStep;
    private float yg;
    private float xk;
    DefaultTableModel tableModel;

    public PaintGraph() {}

    public PaintGraph(DefaultTableModel tableModel) {
        valueOfDivision = 10;// цена деления  по шкалам
        scaleFactorY = (float) 0.5; // коэф шкалы по у
        scaleFactorX = (float) 0.5; // коэф шкалы по x
        initialIndentationY = 50; // начальный отступ по y
        initialIndentationX = 50; //начальный отступ по х
        axisLengthY = 400; // длина оси у
        axisLengthX = 600; // длина оси х
        // по умолчанию в начале на экран выводится график y=x
        sw = 1; // свич для переключения графика функции
        tABStep = (float) 0.011;//шаг табуляции
        this.tableModel=tableModel;
    }

    public void paint(Graphics g) {

        super.paint(g);
        //Разбиваем каждую ось на две части для удобства переноса центра координат
        // Ось Y
        g.drawLine((int) (axisLengthX * scaleFactorX + initialIndentationX), initialIndentationY,
                (int) (axisLengthX * scaleFactorX + initialIndentationX), axisLengthY + initialIndentationY);
        // Стрелки
        g.drawLine((int) (axisLengthX * scaleFactorX + initialIndentationX), initialIndentationY,
                (int) (axisLengthX * scaleFactorX + initialIndentationX) - 3, initialIndentationY + 10);
        g.drawLine((int) (axisLengthX * scaleFactorX + initialIndentationX), initialIndentationY,
                (int) (axisLengthX * scaleFactorX + initialIndentationX) + 3, initialIndentationY + 10);
        // Надпись
        g.drawString("Y", (int) (axisLengthX * scaleFactorX + initialIndentationX) - 10, initialIndentationY + 10);
        g.drawString("0", (int) (axisLengthX * scaleFactorX + initialIndentationX) - 10, (int) (axisLengthY * scaleFactorY + initialIndentationY) + 10);
        //Деления
        int l1 = (int) (axisLengthY * scaleFactorY);
        l2 = axisLengthY - l1;
        int k1 = (int) l1 / valueOfDivision;
        int k2 = (int) l2 / valueOfDivision;
        for (int i = 1; i < k1 + 1; i++) {
            g.drawLine((int) (axisLengthX * scaleFactorX - 2 + initialIndentationX), l1 - valueOfDivision + initialIndentationY,
                    (int) (axisLengthX * scaleFactorX + 2 + initialIndentationX), l1 - valueOfDivision + initialIndentationY);
            l1 = l1 - valueOfDivision;
        }
        l1 = axisLengthY - l2;
        for (int i = 1; i < k2 + 1; i++) {
            g.drawLine((int) (axisLengthX * scaleFactorX - 2 + initialIndentationX), l1 + valueOfDivision + initialIndentationY,
                    (int) (axisLengthX * scaleFactorX + 2 + initialIndentationX), l1 + valueOfDivision + initialIndentationY);
            l1 = l1 + valueOfDivision;
        }
        // Ось Х
        g.drawLine(initialIndentationX, (int) (axisLengthY * scaleFactorY + initialIndentationY), axisLengthX + initialIndentationX, (int) (axisLengthY * scaleFactorY + initialIndentationY));
        g.drawLine(axisLengthX + initialIndentationX, (int) (axisLengthY * scaleFactorY + initialIndentationY), axisLengthX + initialIndentationX - 10,
                (int) (axisLengthY * scaleFactorY + initialIndentationY) - 3);
        g.drawLine(axisLengthX + initialIndentationX, (int) (axisLengthY * scaleFactorY + initialIndentationY), axisLengthX + initialIndentationX - 10,
                (int) (axisLengthY * scaleFactorY + initialIndentationY) + 3);
        // Надпись
        g.drawString("Х", axisLengthX + initialIndentationY - 10, (int) (axisLengthY * scaleFactorY + initialIndentationY) - 10);
        // Деления
        l1 = (int) (axisLengthX * scaleFactorX);
        l2 = axisLengthX - l1;
        k1 = (int) l1 / valueOfDivision;
        k2 = (int) l2 / valueOfDivision;
        for (int i = 1; i < k1 + 1; i++) {
            g.drawLine(l1 - valueOfDivision + initialIndentationX, (int) (axisLengthY * scaleFactorY - 2 + initialIndentationY),
                    l1 - valueOfDivision + initialIndentationX, (int) (axisLengthY * scaleFactorY + 2 + initialIndentationY));
            l1 = l1 - valueOfDivision;
        }
        l1 = axisLengthX - l2;
        double xl = l1 / valueOfDivision;
        double xl1 = l2 / valueOfDivision;
        for (int i = 1; i < k2 + 1; i++) {
            g.drawLine(l1 + valueOfDivision + initialIndentationX, (int) (axisLengthY * scaleFactorY - 2 + initialIndentationY),
                    l1 + valueOfDivision + initialIndentationX, (int) (axisLengthY * scaleFactorY + 2 + initialIndentationY));
            l1 = l1 + valueOfDivision;
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

       // inscriptions(1, g);
        //funDraw(g);

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
        this.a = a;
        this.b = b;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public void funDraw(Graphics g) {
        for (int i=0; i<tableModel.getRowCount()-1; i++)
        {
            for (int j=0; j<tableModel.getColumnCount()-1; j++) {
                g.drawLine(initialIndentationX, initialIndentationY, initialIndentationX+(int)tableModel.getValueAt(i, j), initialIndentationY+(int)tableModel.getValueAt(i+1, j+1));
            }
        }
    }
}

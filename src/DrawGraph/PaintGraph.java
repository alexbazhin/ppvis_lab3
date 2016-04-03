package DrawGraph;

import java.awt.*;
import javax.swing.*;
import java.lang.*;

public class PaintGraph extends JPanel {
    private int valueOfDivision, nx, initialIndentationY, oyk, oyx, initialIndentationX, oxk, oxy, axisLengthY, axisLengthX, sw, xln, l2;
    private float xng, scaleFactorX, scaleFactorY, tABStep, yg, xk;

    public PaintGraph() {
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

        funcLine(g);

    }

    // группа методов рисующих графики функций
    // Метод рисующий линию

    void funcLine(Graphics g) {
        xln = (axisLengthX - l2);
        xk = 0;
        yg = 0;
        while ((xk + tABStep) * valueOfDivision < xln && (xk + tABStep) * valueOfDivision < axisLengthY - axisLengthY * scaleFactorY) {
            yg = xk;
            g.drawLine((int) (xln - xk * valueOfDivision + initialIndentationX),
                    (int) (axisLengthY * scaleFactorY + yg * valueOfDivision + initialIndentationY),
                    (int) (xln - (xk + tABStep) * valueOfDivision + initialIndentationX),
                    (int) (axisLengthY * scaleFactorY + (xk + tABStep) * valueOfDivision) + initialIndentationY);
            xk = xk + tABStep;
        }
        xk = 0;
        yg = 0;
        while ((xk + tABStep) * valueOfDivision < l2 && (xk + tABStep) * valueOfDivision < axisLengthY * scaleFactorY) {
            yg = xk;
            g.drawLine((int) (xln + xk * valueOfDivision + initialIndentationX),
                    (int) (axisLengthY * scaleFactorY - yg * valueOfDivision + initialIndentationY),
                    (int) (xln + (xk + tABStep) * valueOfDivision + initialIndentationX),
                    (int) (axisLengthY * scaleFactorY - (xk + tABStep) * valueOfDivision) + initialIndentationY);
            xk = xk + tABStep;
        }
    }

    // группа getXXX(), setXXX() - методов
    public int getNx() {
        return nx;
    }

    public void setNx(int nx) {
        this.nx = nx;
    }

    public int getValueOfDivision() {
        return valueOfDivision;
    }

    public void setValueOfDivision(int valueOfDivision) {
        this.valueOfDivision = valueOfDivision;
    }

    public float getScaleFactorY() {
        return scaleFactorY;
    }

    public void setScaleFactorY(float scaleFactorY) {
        this.scaleFactorY = scaleFactorY;
    }

    public float getScaleFactorX() {
        return scaleFactorX;
    }

    public void setScaleFactorX(float scaleFactorX) {
        this.scaleFactorX = scaleFactorX;
    }

    public float gettABStep() {
        return tABStep;
    }

    public void settABStep(float tABStep) {
        this.tABStep = tABStep;
    }

    public int getAxisLengthX() {
        return axisLengthX;
    }

    public void setAxisLengthX(int axisLengthX) {
        this.axisLengthX = axisLengthX;
    }

    public int getAxisLengthY() {
        return axisLengthY;
    }

    public void setAxisLengthY(int axisLengthY) {
        this.axisLengthY = axisLengthY;
    }

    public int getSw() {
        return sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }

    public int getInitialIndentationY() {
        return initialIndentationY;
    }

    public void setInitialIndentationY(int initialIndentationY) {
        this.initialIndentationY = initialIndentationY;
    }

    public int getInitialIndentationX() {
        return initialIndentationX;
    }

    public void setInitialIndentationX(int initialIndentationX) {
        this.initialIndentationX = initialIndentationX;
    }
}

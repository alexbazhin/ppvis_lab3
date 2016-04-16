package Controller;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PaintGraph extends JPanel {
    DefaultTableModel tableModel;
    JTable table;

    private int canvasSizeX = 630;
    private int canvasSizeY = 455;

    private double a = 0;
    private double b = 0;
    private double maxValueAxis = 4;
    private double minValueAxis = -4;
    private double maxValue = 1;
    private double minValue = -1;

    private final static double H = 0.1;
    private final static double minY = -4;
    private final static double maxY = 4;

    int centerX;
    int centerY;

    double coeffX;
    double coeffY;
    double x;
    double prevX, prevY;

    public PaintGraph() {
        super(true);

        setSize(new Dimension(canvasSizeX, canvasSizeY));
        setPreferredSize(new Dimension(canvasSizeX, canvasSizeY));
        addMouseWheelListener(e -> {
            if (e.isControlDown()) {
                if (canvasSizeX > 200 & canvasSizeY > 200 && e.getWheelRotation() < 0) {
                    canvasSizeX /= 1.2;
                    canvasSizeY /= 1.2;
                } else if (canvasSizeX < 10000 && canvasSizeY < 10000 && e.getWheelRotation() > 0) {
                    canvasSizeX *= 1.2;
                    canvasSizeY *= 1.2;
                }

                setPreferredSize(new Dimension(canvasSizeX, canvasSizeY));
                setSize(canvasSizeX, canvasSizeY);

                repaint();
            }
        });
    }
    public void setTable(DefaultTableModel tableModel, JTable table) {
        this.tableModel = tableModel;
        this.table = table;
    }

    public void setParam(double a, double b, double minValue, double maxValue) {
        this.a = a;
        this.b = b;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);

        drawAxis(g2);

        for(int i = 0; i < tableModel.getRowCount() - 1; ++i) {
            int x1 = (int) ((double) tableModel.getValueAt(i, 0) * coeffX);
            int y1 = (int) ((double) tableModel.getValueAt(i, 1) * coeffY);
            int x2 = (int) ((double) tableModel.getValueAt(i + 1, 0) * coeffX);
            int y2 = (int) ((double) tableModel.getValueAt(i + 1, 1) * coeffY);

            g2.drawLine(centerX + x1, centerY - y1, centerX + x2, centerY - y2);
        }
    }

    public void calculate() {
        if (tableModel.getRowCount() > 0) {
            for (int i = tableModel.getRowCount() - 1; i > -1; i--) {
                tableModel.removeRow(i);
            }
        }

        ThreadGroup group = new ThreadGroup("A");

        Graphics2D g = (Graphics2D) getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.clearRect(0, 0, canvasSizeX, canvasSizeY);

        g.setColor(Color.BLACK);
        drawAxis(g);

        Function fun = new Function();

        for(x = minValue; x <= maxValue; x += H) {
            if(x == minValue) {
                prevX = x;
                prevY = fun.getY(x, a, b);
            }

            Thread thread = new Thread(group, new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    addAndDraw(x, fun.getY(x, a, b), g);
                    Object[] row = {x, fun.getY(x, a, b)};
                    tableModel.addRow(row);
                }
            });
            thread.run();
        }

    }

    public void drawAxis(Graphics g) {
        coeffX = getWidth() / (maxValueAxis - minValueAxis);
        coeffY = getHeight() / (maxY - minY);

        if(Math.signum(minValueAxis) == Math.signum(maxValueAxis)) {
            g.drawLine(5, 0, 5, getHeight());
            centerX =  - (int) (minValueAxis * coeffX);
        } else {
            g.drawLine((int) (Math.abs(minValueAxis) * getWidth() / (maxValueAxis - minValueAxis)), 0,
                    (int) (Math.abs(minValueAxis) * getWidth() / (maxValueAxis - minValueAxis)), getHeight());
            centerX = (int) (coeffX * Math.abs(minValueAxis));
        }

        if(Math.signum(minY) == Math.signum(maxY)) {
            centerY = getHeight() - 5;
            g.drawLine(0, centerY, getWidth(), centerY);
        } else {
            g.drawLine(0, (int) (maxY * getHeight() / (maxY - minY)),
                    getWidth(), (int) (maxY * getHeight() / (maxY - minY)));

            centerY = (int) (coeffY * maxY);
        }

        for(int i = 0; i <= 10; ++i) {
            double x = minValueAxis + i * getWidth() / 10 / coeffX;
            double y = minY + i * getHeight() / 10 / coeffY;

            g.drawString(String.format("%.2f", x), i * getWidth() / 10, centerY);
            g.drawLine(i * getWidth() / 10, centerY-5, i * getWidth() / 10, centerY+5);
            g.drawString(String.format("%.2f", y), centerX, getHeight() - i * getHeight() / 10);
            g.drawLine(centerX-5, getHeight() - i * getHeight() / 10, centerX+5, getHeight() - i * getHeight() / 10);
        }
    }

    public synchronized void addAndDraw(double x, double y, Graphics g) {
        g.drawLine(centerX + (int) (prevX * coeffX), centerY - (int) (prevY * coeffY),
                centerX + (int)(x * coeffX), centerY - (int)(y * coeffY));
        prevX = x;
        prevY = y;
    }
}

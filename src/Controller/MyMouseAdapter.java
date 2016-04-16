package Controller;
import View.MainPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseAdapter extends MouseAdapter {

    int prevX, prevY;
    MainPanel frame;

    public MyMouseAdapter(MainPanel frame) {
        this.frame = frame;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);

        JScrollBar verticalScrollBar = frame.getScrollCanvas().getVerticalScrollBar();
        JScrollBar horizontalScrollBar = frame.getScrollCanvas().getHorizontalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getValue() - e.getY() + prevY);
        horizontalScrollBar.setValue(horizontalScrollBar.getValue() - e.getX() + prevX);

        prevX = e.getX();
        prevY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        prevX = e.getX();
        prevY = e.getY();
    }
}

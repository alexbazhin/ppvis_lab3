package DrawGraph;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SettingsPanel extends JPanel {
    private JTextField textFieldA, textFieldB, textFieldX;
    private JLabel labelA, labelB, labelX;

    PaintGraph pg;

    public SettingsPanel(PaintGraph p) {
        setLayout(new FlowLayout());
        pg = p;

        textFieldA = new JTextField(10);
        textFieldB = new JTextField(10);
        textFieldX = new JTextField(10);

        labelA = new JLabel("Введите а:");
        labelB = new JLabel("Введите b:");
        labelX = new JLabel("Введите x:");

        add(labelA);
        add(textFieldA);
        add(labelB);
        add(textFieldB);
        add(labelX);
        add(textFieldX);
    }
}

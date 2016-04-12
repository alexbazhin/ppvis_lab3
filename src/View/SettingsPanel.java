package View;

import Controller.PaintGraph;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class SettingsPanel extends JPanel {
    private JTextField textFieldA, textFieldB, textFieldXFrom, textFieldXBefore;
    private JLabel labelA, labelB, labelX, labelXFrom, labelXBefore;
    public JButton buildGraphButton;

    PaintGraph pg;

    public SettingsPanel(PaintGraph p) {
        setLayout(new FlowLayout());
        pg = p;

        textFieldA = new JTextField(10);
        textFieldB = new JTextField(10);
        textFieldXFrom = new JTextField(10);
        textFieldXBefore = new JTextField(10);

        labelA = new JLabel("Введите а:");
        labelB = new JLabel("Введите b:");
        labelX = new JLabel("Введите x:");
        labelXFrom = new JLabel("От");
        labelXBefore = new JLabel("До");

        buildGraphButton = new JButton("Построить график");

        buildGraphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaintGraph pg = new PaintGraph();

                //pg.setParam(getTextFieldA(), getTextFieldB(), getTextFieldXFrom(), getTextFieldXBefore());
            }
        });

        add(labelA);
        add(textFieldA);
        add(labelB);
        add(textFieldB);
        add(labelX);
        add(labelXFrom);
        add(textFieldXFrom);
        add(labelXBefore);
        add(textFieldXBefore);
        add(buildGraphButton);
    }

    public int getTextFieldA() {
        return Integer.parseInt(textFieldA.getText());
    }

    public int getTextFieldB() {
        return Integer.parseInt(textFieldB.getText());
    }

    public int getTextFieldXFrom() {
        return Integer.parseInt(textFieldXFrom.getText());
    }

    public int getTextFieldXBefore() {
        return Integer.parseInt(textFieldXBefore.getText());
    }
}

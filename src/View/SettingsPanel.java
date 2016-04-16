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

    PaintGraph paintGraph;

    public SettingsPanel(PaintGraph p) {
        setLayout(new FlowLayout());
        paintGraph = p;

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

                paintGraph.setParam(getTextFieldA(), getTextFieldB(), getTextFieldXFrom(), getTextFieldXBefore());
                paintGraph.calculate();
                paintGraph.repaint();
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

    public double getTextFieldA() {
        return Double.parseDouble(textFieldA.getText());
    }

    public double getTextFieldB() {
        return Double.parseDouble(textFieldB.getText());
    }

    public double getTextFieldXFrom() {
        return Double.parseDouble(textFieldXFrom.getText());
    }

    public double getTextFieldXBefore() {
        return Double.parseDouble(textFieldXBefore.getText());
    }
}

package View;

import Controller.PaintGraph;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainPanel {
    PaintGraph pg;
    SettingsPanel rp;
    JTable tableOfValues;
    static String[] columnNames = {
            "X",
            "Y"
    };
    public static DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

    public MainPanel() {
        super();
    }

    public JFrame createFrame(String name) {
        JFrame frame = new JFrame();
        Container c = frame.getContentPane();
        c.setLayout(new BorderLayout());

        tableOfValues = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableOfValues);
        scrollPane.setSize(10, 50);

        pg = new PaintGraph(tableModel, 0, 0, 0, 0);
        JScrollPane scrollPane2 = new JScrollPane(pg);
        pg.setSize(600, 430);
        c.add(scrollPane2, BorderLayout.CENTER);

        c.add(scrollPane, BorderLayout.WEST);
        rp = new SettingsPanel(pg);
        c.add(rp, BorderLayout.SOUTH);
        frame.setSize(1150, 600);
        frame.setName(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        return frame;
    }
}

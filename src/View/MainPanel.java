package View;

import Controller.MyMouseAdapter;
import Controller.PaintGraph;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainPanel {
    PaintGraph paintGraph;
    SettingsPanel settingsPanel;
    JTable tableOfValues;
    JScrollPane scrollPane2;
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

        paintGraph = new PaintGraph();
        paintGraph.setTable(tableModel, tableOfValues);
        scrollPane2 = new JScrollPane(paintGraph);
        paintGraph.setSize(600, 430);
        c.add(scrollPane2, BorderLayout.CENTER);

        c.add(scrollPane, BorderLayout.WEST);
        settingsPanel = new SettingsPanel(paintGraph);
        c.add(settingsPanel, BorderLayout.SOUTH);
        frame.setSize(1150, 600);
        frame.setName(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        MyMouseAdapter adapter = new MyMouseAdapter(this);
        paintGraph.addMouseListener(adapter);
        paintGraph.addMouseMotionListener(adapter);
        return frame;
    }

    public JScrollPane getScrollCanvas() {
        return scrollPane2;
    }
}

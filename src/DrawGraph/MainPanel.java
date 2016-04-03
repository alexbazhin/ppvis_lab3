package DrawGraph;

import java.awt.*;
import javax.swing.*;

public class MainPanel {
    PaintGraph pg; // класс вывода графика функции
    SettingsPanel rp; // класс задания функции
    JTable tableOfValues;

    public MainPanel() {
        super();
    }

    public JFrame createFrame(String name) {
        JFrame frame = new JFrame();
        Container c = frame.getContentPane();
        c.setLayout(new BorderLayout()); // установка менеджера размещения

        String[] columnNames = {
                "X",
                "Y"
        };
        String[][] data = new String[3][2];

        tableOfValues = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(tableOfValues);
        scrollPane.setSize(10, 50);

        pg = new PaintGraph(); // инициализация класса построения графика функции
        JScrollPane scrollPane2 = new JScrollPane(pg);
        pg.setSize(600, 430); // задание размеров
        c.add(scrollPane2, BorderLayout.CENTER); // задание размещения

        c.add(scrollPane, BorderLayout.WEST);
        rp = new SettingsPanel(pg);
        c.add(rp, BorderLayout.SOUTH);// инициализация класса выбора графика функции
        frame.setSize(1150, 600); // задание размеров
        frame.setName(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // задание параметров
        // главного окна при закрытии
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        return frame;
    }
}

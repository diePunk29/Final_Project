import javax.swing.*;

public class Table {
    // the code below is from https://www.javatpoint.com/java-jtable
    // dynamically updating table: https://stackoverflow.com/questions/24918884/dynamically-updating-jtable
    JFrame f;

    Table() {
        f = new JFrame();
        String[][] data = {{"101", "Amit", "670000"},
                {"102", "Jai", "780000"},
                {"101", "Sachin", "700000"}};
        String[] column = {"ID", "NAME", "SALARY"};
        JTable jt = new JTable(data, column);
        jt.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(jt);
        f.add(sp);
        f.setSize(300, 400);
        f.setVisible(true);
    }
}
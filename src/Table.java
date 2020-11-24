import javax.swing.*;

/*
so i was thinking that we end up moving the code from here to wherever the table is supposed
to be. i'm gonna get the dynamic table working.
    - the obama guy
 */

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
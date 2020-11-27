import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends JFrame {

    public Main() {
        // I think the main menu is suppose to be set up here idk tho
        JPanel panel = new JPanel();
        JMenuBar mainBar = new JMenuBar();
        setJMenuBar(mainBar);
        TableModel tableModel = new TableModel();
        JTable table = new JTable(tableModel);
        Controller co = new Controller(tableModel);

        JOptionPane aboutInfo = new JOptionPane();
        aboutInfo.setMessage(JOptionPane.PLAIN_MESSAGE);
        aboutInfo.setMessage("TEAM NAME: DA AMAZING FELLAS\n" +
                "TEAM MEMBERS: \nCristian Mosqueda\nAlbert Schaffer\nTyler Vaillancourt\nWill Lord\nEric Fahy\n");

        co.aboutMenu.add(aboutInfo);

        mainBar.add(co.fileMenu);
        mainBar.add(co.aboutMenu);
        setTitle("FINAL PROJECT CSE 360 <3");


        table.setBounds(30, 40, 200, 300);
        JScrollPane scroll = new JScrollPane(table);

        //Add the menu bar and table to a panel
        panel.setLayout(new BorderLayout());
        panel.add(mainBar, BorderLayout.NORTH);
        panel.add(scroll);

        //Add panel to the frame
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(800, 600);
    }

    //This is for the dynamically updating JTable
    class TableModel extends AbstractTableModel {

        private String[] columnNames = {"ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};

        //ArrayList for storing entries in the table (can be changed if needed)
        private ArrayList<ArrayList> rows;

        public TableModel() {
            rows = new ArrayList<ArrayList>();
            // these were for testing
            //makeDummyRows();
            //makeDummyRows();
        };
        public void makeDummyRows() {
            ArrayList<String> tempRow = new ArrayList<String>();
            tempRow.add("bleh");
            tempRow.add("Obama");
            tempRow.add("Tyler Vanillacourt");
            tempRow.add("killME.txt");
            tempRow.add("mamba");
            tempRow.add("NFDL");
            rows.add(tempRow);
        }
        //So I'm unsure if I need more methods for the table, we might need
        //one for grabbing the shit from the CSV or Repository class
        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public int getRowCount() {
            return rows.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            ArrayList column = rows.get(rowIndex);
            return column.get(columnIndex);
        }

        public void updateTable(String[] row) {
            ArrayList<String> col = new ArrayList<>(row.length);
            col.addAll(Arrays.asList(row));
            rows.add(col);
            fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
        }

    }

    public static void main(String[] args) {
        //Main daMain = new Main();
        //daMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //daMain.setVisible(true);
        //daMain.setSize(800, 600);

        Main daMain = new Main();

        //This makes graph show up if you want to test
        /*
        SwingUtilities.invokeLater(() -> {
            ScatterPlot example = new ScatterPlot("Scatter Chart Example");
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
        */

        //Table table = new Table();
    }
}

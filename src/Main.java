import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;


public class Main extends JFrame {
    private TableModel tableModel;

    public Main() {

        // I think the main menu is suppose to be set up here idk tho
        JPanel panel = new JPanel();
        JMenuBar mainBar = new JMenuBar();
        setJMenuBar(mainBar);
        tableModel = new TableModel();
        JTable table = new JTable(tableModel);
        Controller co = new Controller(tableModel);

        String message = "TEAM NAME: DA AMAZING FELLAS\n" +
                "TEAM MEMBERS: \nCristian Mosqueda\nAlbert Schaffer\nTyler Vaillancourt\nWill Lord\nEric Fahy\n";
        
        JOptionPane aboutInfo = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[] {}, null);
        
        JDialog test = new JDialog();
        test.setTitle("ABOUT");
        test.setModal(true);
        
        test.setContentPane(aboutInfo);

        co.aboutMenu.add(aboutInfo);

        mainBar.add(co.fileMenu);
        mainBar.add(co.aboutMenu);
        setTitle("FINAL PROJECT CSE 360 <3");


        // Figure out how to set table minimum width (horizontal)
        table.setBounds(30, 40, 200, 300);
        //table.setBounds(table.getBounds());
        JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

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
        private ArrayList<AttendanceInfo> missingStuds;


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

        // adds the date column to the table header
        public void setColumnName(String date) {
            // searches through array and ignores duplicate dates
            for (int i = 0; i < columnNames.length; i++) {
                if (date.equals(columnNames[i])) return;
            }

            // create a new array with one more capacity to be the new column names
            String[] newNames = new String[columnNames.length+1];
            for (int i = 0; i < columnNames.length; i++) newNames[i] = columnNames[i];
            newNames[columnNames.length] = date;
            columnNames = newNames;
            for (int i = 0; i < rows.size(); i++) {
                rows.get(i).add("0");
            }
            fireTableStructureChanged();
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

        public void updateTable(StudentInfo row) {

            // breaks function if student is already in the table
            String tableID;
            for (int i = 0; i < rows.size(); i++) {
                tableID = rows.get(i).get(5).toString().replace(" ","");
                if (tableID.equals(row.getAsurite().replace(" ", ""))) {
                    return;
                }
            }
            ArrayList<String> col = new ArrayList<>(7);
            //add all student info elements to the col
            col.add(row.getStudentId());
            col.add(row.getFirstName());
            col.add(row.getLastName());
            col.add(row.getProgPlan());
            col.add(row.getAcademicLvl());
            col.add(row.getAsurite());

            rows.add(col);
            fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
        }

        // adds the elapsed time to the table for a give student
        public int updateWithAttendance(AttendanceInfo info, ArrayList<AttendanceInfo> ms) {
            int columnIndex = 0;
            int dataLoadCount = 0;

            // find the correct date column to be adding to
            for (int i = 0; i < columnNames.length; i++) {
                if (columnNames[i].equals(info.getDate())) {
                    columnIndex = i;
                    break;
                }
            }
            // iterates over all the rows in the table to find given student
            for (int i = 0; i < rows.size(); i++) {

                // not sure why but table has a space before the asurite
                String tableID = rows.get(i).get(5).toString().replace(" ", "");

                // if the asurite in the table matches the incoming asurite, update the value
                if (tableID.equals(info.getAsurite())) {
                    dataLoadCount++;
                    int newValue = Integer.parseInt(rows.get(i).get(columnIndex).toString().replace(" ", ""));
                    newValue += Integer.parseInt(info.getTimeElapsed().replace(" ", ""));
                    rows.get(i).set(columnIndex, "" + newValue);
                    return dataLoadCount;
                }
            }
            // if we get here the student was not in the original roster
            recordMissingStudents(info, ms);
            return 0;
        }

        // adds missing student in roster to ms array list
        public void recordMissingStudents(AttendanceInfo info, ArrayList<AttendanceInfo> ms) {
            ms.add(info);
        }

        public void removeRow(int row) {
            rows.remove(row);
        }

    }

    public static void main(String[] args) {
        Main daMain = new Main();
    }
}

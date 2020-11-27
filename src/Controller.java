import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Controller implements ActionListener {

    // fields
    Main.TableModel tableModel;
    JMenu fileMenu;
    JMenu aboutMenu;
    JMenuItem loadRost;
    JMenuItem addAttendance;
    JMenuItem save;
    JMenuItem plotData;
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;
    UtilDateModel mod;
    Properties p;
    private final String delimiter = ",";
    protected ArrayList<StudentInfo> studentEntries;
    protected ArrayList<AttedanceInfo> attendanceEntries;

    // constructor + methods
    public Controller(Main.TableModel tableModel) {

        // menus in menu bar
        fileMenu = new JMenu("File");
        aboutMenu = new JMenu("About");

        // JMenu items for File menu component
        loadRost = new JMenuItem("Load a Roster");
        addAttendance = new JMenuItem("Add Attendance");
        save = new JMenuItem("Save");
        plotData = new JMenuItem("Plot Data");

        // adding menu items to file menu
        fileMenu.add(loadRost);
        fileMenu.add(addAttendance);
        fileMenu.add(save);
        fileMenu.add(plotData);

        // instantiating the arrayList for load roster
        studentEntries = new ArrayList<>();

        // action listeners
        loadRost.addActionListener(this);
        addAttendance.addActionListener(this);
        save.addActionListener(this);
        plotData.addActionListener(this);

        // controller needs access to the table model
        this.tableModel = tableModel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadRost) {
            // declarations
            BufferedReader bufR;
            File csvFile;
            FileReader fReadr;
            int selection;

            // starts within project folder!
            JFileChooser chooser = new JFileChooser(".");
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            selection = chooser.showOpenDialog(null);
            studentEntries = new ArrayList<>();

            if(selection == JFileChooser.APPROVE_OPTION) {
                csvFile = chooser.getSelectedFile();
                if (csvFile.getName().endsWith(".txt")) {
                    try {
                        fReadr = new FileReader(csvFile);
                        bufR = new BufferedReader(fReadr);

                        // if the file is a file
                        if (csvFile.isFile()) {
                            String tempData;
                            String[] dataColumns = new String[6];
                            while ((tempData = bufR.readLine()) != null) {
                                dataColumns = tempData.split(delimiter);
                                // setting up student info
                                // we still need to add checks for data field type
                                StudentInfo studentI = new StudentInfo();
                                studentI.setStudentId(dataColumns[0]);
                                studentI.setFirstName(dataColumns[1]);
                                studentI.setLastName(dataColumns[2]);
                                studentI.setProgPlan(dataColumns[3]);
                                studentI.setAcademicLvl(dataColumns[4]);
                                studentI.setAsurite(dataColumns[5]);

                                // push finalized student info into arraylist of all student entries
                                studentEntries.add(studentI);

                                // put the student into a String[] so types work for table
                                String[] temp = new String[dataColumns.length];
                                for (int i = 0; i < dataColumns.length; i++) {
                                    temp[i] = dataColumns[i];
                                }
                                // update table with info from the String[]
                                tableModel.updateTable(temp);
                            }
                            bufR.close();

                        }
                    } catch (FileNotFoundException error) {
                        error.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
        else if (e.getSource() == addAttendance) {
            // declarations
            BufferedReader buff;
            File csvFile;
            FileReader fR;
            int choice;

            JFileChooser chooser = new JFileChooser(".");
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            choice = chooser.showOpenDialog(null);
            attendanceEntries = new ArrayList<>();

            if(choice == JFileChooser.APPROVE_OPTION) {
                csvFile = chooser.getSelectedFile();
                // making sure only .txt files are read
                if(csvFile.getName().endsWith(".txt")) {
                    try {
                        fR = new FileReader(csvFile);
                        buff = new BufferedReader(fR);

                        if(csvFile.isFile()) {
                            String currCol;
                            String[] dataCol = new String[2];
                            while((currCol = buff.readLine()) != null) {
                                dataCol = currCol.split(delimiter);
                                AttedanceInfo studentAttInfo = new AttedanceInfo();
                                studentAttInfo.setAsurite(dataCol[0]);
                                studentAttInfo.setTimeElapsed(dataCol[1]);

                                mod = new UtilDateModel();
                                p = new Properties();
                                p.put("text.today", "Today");
                                p.put("text.month", "Month");
                                p.put("text.year", "Year");

                                datePanel = new JDatePanelImpl(mod, p);
                                datePicker = new JDatePickerImpl(datePanel, new DateFormatter());




                            }
                        }

                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }
            }

        }
        else if (e.getSource() == save) {
            System.out.println("Save");
        }
        else if (e.getSource() == plotData) {
            System.out.println("Plot");
        }

    }
}

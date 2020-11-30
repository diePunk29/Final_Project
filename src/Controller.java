/**
 * CSE360
 * Final Project
 * This class is the controller that deals
 * with the file menu.
 */

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/*
Date picker found at: https://github.com/LGoodDatePicker/LGoodDatePicker
 */

public class Controller implements ActionListener {

    // fields
    Main.TableModel tableModel;
    JMenu fileMenu;
    JMenu aboutMenu;
    JMenuItem loadRost;
    JMenuItem addAttendance;
    JMenuItem save;
    JMenuItem plotData;
    private int attendanceCount = 0;
    private Boolean hasLoadedRost = false;
    private Boolean hasLoadedAttendance = false;
    private JFrame cal;
    private AttendanceInfo studentAttInfo;
    private DatePicker dp;
    private final String delimiter = ",";
    protected ArrayList<StudentInfo> studentEntries;
    protected ArrayList<AttendanceInfo> attendanceEntries;
    protected ArrayList<AttendanceInfo> ms;

    /**
     * This is the constructor for the Controller class.
     * It also creates the look of the frame.
     * @param tableModel The table model being used
     */
    public Controller(Main.TableModel tableModel) {

        // menus in menu bar
        fileMenu = new JMenu("File");
        aboutMenu = new JMenu("About");
        dp = new DatePicker();
        ms = new ArrayList<>();

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
    /**
     * This method handles all of the events that handles on the main frame.
     * It will activate everything that needs to happen when a button or option is selected.
     * @param e  The event that occured
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadRost) {

            // clear table
            studentEntries.clear();
            while (tableModel.getRowCount() > 0) {
                tableModel.removeRow(0);
            }

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

            if (selection == JFileChooser.APPROVE_OPTION) {

                csvFile = chooser.getSelectedFile();
                if (csvFile.getName().endsWith(".txt")) {
                    try {
                        fReadr = new FileReader(csvFile);
                        bufR = new BufferedReader(fReadr);

                        // if the file is a file
                        if (csvFile.isFile()) {
                            // roster has been loaded
                            hasLoadedRost = true;

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

                                // update the table with this student info
                                tableModel.updateTable(studentI);
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
        } else if (e.getSource() == addAttendance) {
            // if the roster has been loaded then proceed if not then..
            if(hasLoadedRost) {
                // calendar frame
                cal = new JFrame();
                cal.setUndecorated(true);
                cal.setLocationRelativeTo(null);
                cal.setLayout(new FlowLayout());
                cal.setVisible(true);
                cal.setSize(300, 100);
                DatePickerSettings datePickerSettings = new DatePickerSettings();
                datePickerSettings.setAllowEmptyDates(false);
                dp = new DatePicker(datePickerSettings);
                cal.add(dp);
                JButton lilB = new JButton("SAVE");

                lilB.setLayout(new FlowLayout());
                cal.add(lilB);
                lilB.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // declarations
                        BufferedReader buff;
                        File csvFile;
                        FileReader fR;
                        int choice;

                        // closes datepicker frame
                        cal.dispose();

                        JFileChooser chooser = new JFileChooser(".");
                        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                        choice = chooser.showOpenDialog(null);
                        attendanceEntries = new ArrayList<>();
                        attendanceCount = 0;
                        if (choice == JFileChooser.APPROVE_OPTION) {
                            csvFile = chooser.getSelectedFile();
                            // making sure only .txt files are read
                            if (csvFile.getName().endsWith(".txt")) {
                                try {
                                    fR = new FileReader(csvFile);
                                    buff = new BufferedReader(fR);

                                    if (csvFile.isFile()) {
                                        hasLoadedAttendance = true;
                                        String currCol;
                                        String[] dataCol = new String[2];

                                        // adds a date column to the table
                                        tableModel.setColumnName(dp.getDate().format(DateTimeFormatter.ofPattern("MMM d")).toString());

                                        while ((currCol = buff.readLine()) != null) {
                                            dataCol = currCol.split(delimiter);
                                            studentAttInfo = new AttendanceInfo();
                                            studentAttInfo.setAsurite(dataCol[0]);
                                            studentAttInfo.setTimeElapsed(dataCol[1]);
                                            studentAttInfo.setDate(dp.getDate().format(DateTimeFormatter.ofPattern("MMM d")).toString());
                                            attendanceEntries.add(studentAttInfo);

                                            attendanceCount += tableModel.updateWithAttendance(studentAttInfo, ms);
                                        }
                                        tableModel.fireTableDataChanged();
                                    }

                                } catch (FileNotFoundException fileNotFoundException) {
                                    fileNotFoundException.printStackTrace();
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }

                            }
                        }
                        // notifies user about additional attendees not in roster
                        if(!ms.isEmpty()) {
                            int countOfMs = 0;
                            String message = "";
                            for(int indx = 0; indx < ms.size(); indx++) {
                                countOfMs++;
                                message += ms.get(indx).getAsurite() + " Connected for: " + ms.get(indx).getTimeElapsed() + " minutes \n";
                            }
                            // JFrame will act as a pop up
                            JFrame fj = new JFrame();
                            fj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            fj.setVisible(false);
                            JOptionPane.showMessageDialog(fj, "Data Loaded for " + attendanceCount + " user(s) in roster.\n" +
                                            countOfMs + " additional attendee(s) found:\n" + message
                                    , "ALERT", JOptionPane.INFORMATION_MESSAGE);
                            ms.clear();
                        }
                    }
                });


            }
            else {
                JFrame ffj = new JFrame();
                ffj.setVisible(false);
                ffj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JOptionPane.showMessageDialog(ffj, "ROSTER MUST BE LOADED FOR ATTENDANCE" +
                        " TO BE ADDED!", "ERROR: ROSTER NOT LOADED", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == save) {
            System.out.println("Save");

            //Declarations
            int selection;

            JFileChooser chooser = new JFileChooser(".");
            chooser.addChoosableFileFilter(new TextFileFilter());
            selection = chooser.showSaveDialog(null);

            if (selection == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                String filename = file.getName();
                //String path = file.getParent();

                try {
                    if (!file.exists()) {
                        file.createNewFile();
                        FileWriter csvOut = new FileWriter(file);

                        //write table headers
                        for (int i = 0; i < tableModel.getColumnCount(); i++) {
                            csvOut.write(tableModel.getColumnName(i));
                            if (i != tableModel.getColumnCount() - 1)
                                csvOut.write(",");
                        }
                        csvOut.write("\n");

                        //write student info
                        for (int i = 0; i < studentEntries.size(); i++) {
                            boolean duplicateStudent = isDuplicateStudent(studentEntries.get(i).getStudentId(), i);

                            if (duplicateStudent == false) {
                                csvOut.write(studentEntries.get(i).getStudentId() + ",");
                                csvOut.write(studentEntries.get(i).getFirstName() + ",");
                                csvOut.write(studentEntries.get(i).getLastName() + ",");
                                csvOut.write(studentEntries.get(i).getProgPlan() + ",");
                                csvOut.write(studentEntries.get(i).getAcademicLvl() + ",");
                                csvOut.write(studentEntries.get(i).getAsurite().replace(" ", ""));

                                // used in the for loop below
                                String studentEntry, attendanceEntry;

                                //if there are attendance entries in the table, we need to save them
                                for (int j = 0; j < attendanceEntries.size(); j++) {
                                    studentEntry = studentEntries.get(i).getAsurite().replace(" ", "");
                                    attendanceEntry = attendanceEntries.get(j).getAsurite().replace(" ", "");

                                    if (studentEntry.equals(attendanceEntry)) {
                                        csvOut.write("," + attendanceEntries.get(j).getTimeElapsed().replace(" ", ""));
                                    }
                                }
                            }

                            //new line if not the last entry in the table
                            if (i != studentEntries.size() - 1)
                                csvOut.write("\n");
                        }

                        csvOut.close();
                        System.out.println("File saved successfully");
                    } else {
                        String message = "File: " + filename + " already exists";
                        String title = "Warning!";
                        JOptionPane.showConfirmDialog(null, message, title, JOptionPane.DEFAULT_OPTION);
                    }
                } catch (IOException error) {
                    System.out.println("Failed to save");
                }
            }
        } else if (e.getSource() == plotData) {
            if(hasLoadedRost && hasLoadedAttendance) {
                SwingUtilities.invokeLater(() -> {
                    ScatterPlot example = new ScatterPlot("Scatter Chart Example");
                    example.setSize(800, 400);
                    example.setLocationRelativeTo(null);
                    example.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    example.setVisible(true);
                });
            }
            else {
                JFrame fj = new JFrame();
                fj.setVisible(false);
                JOptionPane.showMessageDialog(fj, "ROSTER AND ATTENDANCE MUST BE LOADED\nIN ORDER TO USE " +
                        "PLOT.", "ERROR: ROSTER AND ATTENDANCE NOT LOADED", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    /**
     * This is a helper method to determine if a student is a duplicate.
     * It is used for save.
     * @param id The id to check.
     * @param index The max index to check.
     * @return
     */
    public boolean isDuplicateStudent(String id, int index) {
        for (int i = 0; i < index; i++) {
            if (id.equals(studentEntries.get(i).getStudentId())) {
                return true;
            }
        }

        return false;
    }
}

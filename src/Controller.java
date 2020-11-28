
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Controller implements ActionListener {

    // fields
    Main.TableModel tableModel;
    JMenu fileMenu;
    JMenu aboutMenu;
    JMenuItem loadRost;
    JMenuItem addAttendance;
    JMenuItem save;
    JMenuItem plotData;
    private JFrame cal;
    private AttedanceInfo studentAttInfo;
    private DatePicker dp;
    private String attendDate;
    private final String delimiter = ",";
    protected ArrayList<StudentInfo> studentEntries;
    protected ArrayList<AttedanceInfo> attendanceEntries;

    // constructor + methods
    public Controller(Main.TableModel tableModel) {

        // menus in menu bar
        fileMenu = new JMenu("File");
        aboutMenu = new JMenu("About");

        dp = new DatePicker();

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
                            String tempData;
                            String[] dataColumns = new String[6];
                            while ((tempData = bufR.readLine()) != null) {
                                dataColumns = tempData.split(delimiter);
                                // setting up student info
                                // we still need to add checks for data field type
                                /*
                                StudentInfo studentI = new StudentInfo();
                                studentI.setStudentId(dataColumns[0]);
                                studentI.setFirstName(dataColumns[1]);
                                studentI.setLastName(dataColumns[2]);
                                studentI.setProgPlan(dataColumns[3]);
                                studentI.setAcademicLvl(dataColumns[4]);
                                studentI.setAsurite(dataColumns[5]);

                                // push finalized student info into arraylist of all student entries
                                studentEntries.add(studentI);
                                */
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
        } else if (e.getSource() == addAttendance) {

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

                    if (choice == JFileChooser.APPROVE_OPTION) {
                        csvFile = chooser.getSelectedFile();
                        // making sure only .txt files are read
                        if (csvFile.getName().endsWith(".txt")) {
                            try {
                                fR = new FileReader(csvFile);
                                buff = new BufferedReader(fR);

                                if (csvFile.isFile()) {
                                    String currCol;
                                    String[] dataCol = new String[2];

                                    while ((currCol = buff.readLine()) != null) {
                                        dataCol = currCol.split(delimiter);
                                        studentAttInfo = new AttedanceInfo();
                                        studentAttInfo.setAsurite(dataCol[0]);
                                        studentAttInfo.setTimeElapsed(dataCol[1]);
                                        studentAttInfo.setDate(dp.getDate().toString());
                                        attendanceEntries.add(studentAttInfo);
                                    }
                                }

                            } catch (FileNotFoundException fileNotFoundException) {
                                fileNotFoundException.printStackTrace();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }

                        }
                    }
                    for (int i = 0; i < attendanceEntries.size(); i++) {
                        System.out.println(attendanceEntries.get(i).getAsurite());
                        System.out.println(attendanceEntries.get(i).getTimeElapsed());
                        System.out.println(attendanceEntries.get(i).getDate());
                        System.out.println();
                    }
                }
            });


        } else if (e.getSource() == save) {
            System.out.println("Save");


            //Declarations
            BufferedReader bufR;
            File csvFile;
            FileReader fReadr;
            int selection;

            JFileChooser chooser = new JFileChooser(".");
            chooser.addChoosableFileFilter(new TextFileFilter());
            selection = chooser.showSaveDialog(null);

            if (selection == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                String filename = file.getName();
                String path = file.getParent();

                try {
                    if (!file.exists()) {
                        file.createNewFile();
                        FileWriter csvOut = new FileWriter(file);

                        for (int i = 0; i < studentEntries.size(); i++) {
                            csvOut.write(studentEntries.get(i).getStudentId() + ",");
                            csvOut.write(studentEntries.get(i).getFirstName() + ",");
                            csvOut.write(studentEntries.get(i).getLastName() + ",");
                            csvOut.write(studentEntries.get(i).getProgPlan() + ",");
                            csvOut.write(studentEntries.get(i).getAcademicLvl() + ",");
                            csvOut.write(studentEntries.get(i).getAsurite() + "\n");
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
            System.out.println("Plot");
        }

    }
}

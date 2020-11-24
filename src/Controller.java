import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Controller implements ActionListener {

    // fields
    JMenu fileMenu;
    JMenu aboutMenu;
    JMenuItem loadRost;
    JMenuItem addAttendance;
    JMenuItem save;
    JMenuItem plotData;
    ArrayList<StudentInfo> studentEntries;

    // constructor + methods
    public Controller() {

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
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadRost) {
            System.out.println("Load");

            // declarations
            File csvFile;
            Scanner fileInput;
            int selection = 0;

            // starts within project folder!
            JFileChooser chooser = new JFileChooser(".");
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            selection = chooser.showOpenDialog(null);

            if(selection == JFileChooser.APPROVE_OPTION) {
                csvFile = chooser.getSelectedFile();
                if (csvFile.getName().endsWith(".txt")) {
                    try {
                        fileInput = new Scanner(csvFile);

                        // if the file is a file
                        if (csvFile.isFile()) {
                            // setting up comma as the file delimiter
                            fileInput.useDelimiter(",");
                            // traversing the csv file
                            while (fileInput.hasNext()) {
                                System.out.println(fileInput.next());
                            }
                            fileInput.close();
                        }
                    } catch (FileNotFoundException error) {
                        error.printStackTrace();
                    }
                }
            }
        }
        else if (e.getSource() == addAttendance) {
            System.out.println("Add");
        }
        else if (e.getSource() == save) {
            System.out.println("Save");
        }
        else if (e.getSource() == plotData) {
            System.out.println("Plot");
        }

    }
}

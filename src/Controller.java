import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller implements ActionListener {

    // fields
    JMenu fileMenu;
    JMenu aboutMenu;
    JMenuItem loadRost;
    JMenuItem addAttendance;
    JMenuItem save;
    JMenuItem plotData;

    // constructor + methods
    public Controller() {
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

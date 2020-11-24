import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller implements ActionListener {

    // fields
    JMenu fileMenu;
    JMenu aboutMenu;
    JOptionPane dia;
    JMenuItem loadRost;
    JMenuItem addAttendance;
    JMenuItem save;
    JMenuItem plotData;

    // constructor + methods
    public Controller() {
        fileMenu = new JMenu("File");
        aboutMenu = new JMenu("About");

        dia = new JOptionPane();
        dia.setMessage(" TEAM NAME: DA AMAZING FELLAS!\n" +
                " TEAM MEMBERS: Cristian Mosqueda, Albert Schaeffer, Tyler Vaillancourt," +
                "Will Lord, I forgot the rest lol.\n");
        aboutMenu.add(dia);

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
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println();
    }
}

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        Controller co = new Controller();
        // I think the main menu is suppose to be set up here idk tho
        JMenuBar mainBar = new JMenuBar();
        setJMenuBar(mainBar);

        // file and about menus components
        JMenu fileMenu = new JMenu("File");
        JMenu aboutMenu = new JMenu("About");

        mainBar.add(fileMenu);
        mainBar.add(aboutMenu);

        JOptionPane dia = new JOptionPane();
        dia.setMessage(" TEAM NAME: DA AMAZING FELLAS!\n" +
                " TEAM MEMBERS: Cristian Mosqueda, Albert Schaeffer, I forgot the rest lol.\n");
        aboutMenu.add(dia);

        // JMenu items for File menu component
        JMenuItem loadRost = new JMenuItem("Load a Roster");
        JMenuItem addAttendance = new JMenuItem("Add Attendance");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem plotData = new JMenuItem("Plot Data");

        // adding menu items to file menu
        fileMenu.add(loadRost);
        fileMenu.add(addAttendance);
        fileMenu.add(save);
        fileMenu.add(plotData);





    }

    public static void main(String[] args) {
        Main daMain = new Main();
        daMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        daMain.setVisible(true);
        daMain.setSize(800, 600);


    }
}

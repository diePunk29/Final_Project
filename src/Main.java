import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        Controller co = new Controller();
        // I think the main menu is suppose to be set up here idk tho
        JMenuBar mainBar = new JMenuBar();
        setJMenuBar(mainBar);

        // file and about menus
        JMenu fileMenu = new JMenu("File");
        JMenu aboutMenu = new JMenu("About");

        mainBar.add(fileMenu);
        mainBar.add(aboutMenu);


    }

    public static void main(String[] args) {
        Main daMain = new Main();
        daMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        daMain.setVisible(true);
        daMain.setSize(800, 600);


    }
}

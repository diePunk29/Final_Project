import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        // I think the main menu is suppose to be set up here idk tho
        JMenuBar mainBar = new JMenuBar();
        setJMenuBar(mainBar);
        Controller co = new Controller();

        mainBar.add(co.fileMenu);
        mainBar.add(co.aboutMenu);
    }

    public static void main(String[] args) {
        Main daMain = new Main();
        daMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        daMain.setVisible(true);
        daMain.setSize(800, 600);

        Table table = new Table();
    }
}

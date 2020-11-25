import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    //JFrame frame;

    public Main() {
        // I think the main menu is suppose to be set up here idk tho
        //frame = new JFrame();
        JPanel panel = new JPanel();
        JMenuBar mainBar = new JMenuBar();
        setJMenuBar(mainBar);
        Controller co = new Controller();

        JOptionPane aboutInfo = new JOptionPane();
        aboutInfo.setMessage(JOptionPane.PLAIN_MESSAGE);
        aboutInfo.setMessage("TEAM NAME: DA AMAZING FELLAS\n" +
                "TEAM MEMBERS: \nCristian Mosqueda\nAlbert Schaffer\nTyler Vaillancourt\nWill Lord\nEric Fahy\n");

        co.aboutMenu.add(aboutInfo);

        mainBar.add(co.fileMenu);
        mainBar.add(co.aboutMenu);

        //Table creation (right now it's static)
        String[] column = {"ID", "First Name", "Last Name",
                                "Program", "Level", "ASURITE"};
        String[][] data = {{"1000000000", "Barack", "Obama", "Political Science", "Graduate", "bobama"}};

        JTable table = new JTable(data, column);
        table.setBounds(30, 40, 200, 300);
        JScrollPane scroll = new JScrollPane(table);

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


    public static void main(String[] args) {
        //Main daMain = new Main();
        //daMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //daMain.setVisible(true);
        //daMain.setSize(800, 600);

        Main daMain = new Main();

        //Table table = new Table();
    }
}

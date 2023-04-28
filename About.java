package notepadapp;

import java.awt.Font;
import javax.swing.*;

public class About extends JFrame {

    String s = "<html>Created By human_666</HTML>";

    About() {
        setBounds(100, 100, 500, 400);
        setTitle("About Notepad Application");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel text = new JLabel(s);
        text.setBounds(100, 100, 100, 100);
        text.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        add(text);
    }
}

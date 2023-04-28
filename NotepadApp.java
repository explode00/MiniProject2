package notepadapp;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.Font;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class NotepadApp extends JFrame implements ActionListener {

    JMenuBar menubar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu edit = new JMenu("Edit");
    JMenu help = new JMenu("Help");

    JMenuItem newFile = new JMenuItem("New");
    JMenuItem openFile = new JMenuItem("Open");
    JMenuItem saveFile = new JMenuItem("Save");
    JMenuItem printFile = new JMenuItem("Print");
    JMenuItem exit = new JMenuItem("Exit");

    JMenuItem cut = new JMenuItem("Cut");
    JMenuItem copy = new JMenuItem("Copy");
    JMenuItem paste = new JMenuItem("Paste");
    JMenuItem selectall = new JMenuItem("Select All");

    JMenuItem about = new JMenuItem("About");

    JTextArea textarea = new JTextArea();

    NotepadApp() {
        setTitle("Notepad Application");
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setJMenuBar(menubar);
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(printFile);
        file.add(exit);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);

        help.add(about);

        JScrollPane scrollpane = new JScrollPane(textarea);
        add(scrollpane);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setBorder(BorderFactory.createEmptyBorder());

        textarea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        printFile.addActionListener(this);
        exit.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        about.addActionListener(this);

        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        printFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String check = e.getActionCommand();
        switch (check) {
            case "New" -> {
                textarea.setText(null);
            }
            case "Open" -> {
                JFileChooser filechooser = new JFileChooser();
                FileNameExtensionFilter textfilter = new FileNameExtensionFilter("Only Text Files(.txt)", "txt");
                filechooser.setAcceptAllFileFilterUsed(false);
                filechooser.addChoosableFileFilter(textfilter);

                int action = filechooser.showOpenDialog(null);
                if (action != JFileChooser.APPROVE_OPTION) {
                    return;
                } else {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(filechooser.getSelectedFile()));
                        textarea.read(reader, null);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            case "Save" -> {
                JFileChooser filechooser = new JFileChooser();
                FileNameExtensionFilter textfilter = new FileNameExtensionFilter("Only Text Files(.txt)", "txt");
                filechooser.setAcceptAllFileFilterUsed(false);
                filechooser.addChoosableFileFilter(textfilter);

                int action = filechooser.showSaveDialog(null);
                if (action != JFileChooser.APPROVE_OPTION) {
                    return;
                } else {
                    String filename = filechooser.getSelectedFile().getAbsolutePath().toString();
                    if (!filename.contains(".txt")) {
                        filename = filename + ".txt";
                    }
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                        textarea.write(writer);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            case "Print" -> {
                try {
                    textarea.print();
                } catch (PrinterException ex) {
                    Logger.getLogger(NotepadApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "Exit" -> {
                System.exit(0);
            }
            case "Cut" -> {
                textarea.cut();
            }
            case "Copy" -> {
                textarea.copy();
            }
            case "Paste" -> {
                textarea.paste();
            }
            case "Select All" -> {
                textarea.selectAll();
            }
            case "About" -> {
                new About().setVisible(true);
            }
            default -> {
            }
        }
    }

    public static void main(String[] args) throws Exception {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new NotepadApp().setVisible(true);
    }
}

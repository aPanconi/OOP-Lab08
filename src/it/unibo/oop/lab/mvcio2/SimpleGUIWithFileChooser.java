package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
    private final JFrame frame = new JFrame();

    public SimpleGUIWithFileChooser(final Controller control) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        final JTextArea text = new JTextArea();
        final JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    control.save(text.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "An error occurred", JOptionPane.ERROR_MESSAGE); 
                }
            }
        });
        mainPanel.add(text, BorderLayout.CENTER);
        mainPanel.add(save, BorderLayout.SOUTH);

        final JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        final JTextField currentFile = new JTextField(control.getCurrentPath());
        currentFile.setEditable(false);
        final JButton choose = new JButton("Browse...");
        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser("Choose file");
                fileChooser.setSelectedFile(control.getCurrent());
                final int result = fileChooser.showSaveDialog(frame);
                switch (result) {
                case JFileChooser.APPROVE_OPTION:
                    final File newCurrent = fileChooser.getSelectedFile();
                    control.setCurrent(newCurrent);
                    currentFile.setText(newCurrent.getPath());
                    break;
                case JFileChooser.CANCEL_OPTION:
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, result, "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        subPanel.add(currentFile, BorderLayout.CENTER);
        subPanel.add(choose, BorderLayout.LINE_END);

        mainPanel.add(subPanel, BorderLayout.NORTH);

        frame.setContentPane(mainPanel);

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();

        frame.setSize(sw / 4, sh / 4);
        frame.setLocationByPlatform(true);
    }

    private void display() {
        frame.setVisible(true);
    }

    public static void main(final String... args) {
        final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.display();
    }
}

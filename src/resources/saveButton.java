package resources;

import java.awt.Color;
import java.awt.EventQueue;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class saveButton extends JFrame implements ActionListener {
    mainFrame mainFrame;
    private JPanel componentPane;
    private JPanel saveBackround;
    private JTextArea toSave;
    private JButton saveFile;
    public saveButton() {
        UIFrame();
    }

    public void UIFrame() {
        getContentPane().setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500, 600);
        this.setTitle("Save File");
        this.setLocationRelativeTo(null);
        componentPane = new JPanel();
        componentPane.setLayout(null);

        saveBackround = new JPanel();
        saveBackround.setBounds(0, 0, 0, 0);
        saveBackround.setBackground(Color.LIGHT_GRAY);
        saveBackround.setLayout(null);
        componentPane.add(saveBackround);

        toSave = new JTextArea();
        toSave.setFont(new Font("Monospaced", Font.PLAIN, 20));
        toSave.setBounds(10, 58, 466, 494);
        toSave.setText("**************************************\n" + "\tGRADE" + "\t\tUNITS\n\t");
        displayGrade();
        toSave.setEditable(false);
        componentPane.add(toSave);

        this.setContentPane(componentPane);

        saveFile = new JButton("Save");
        saveFile.setBounds(10, 28, 89, 23);
        saveFile.addActionListener(this);
        saveFile.setBackground(Color.WHITE);
        saveFile.addMouseListener(new mouseAdapter());
        componentPane.add(saveFile);
        this.setVisible(true);
    }

    public void displayGrade() {
    	//THIS WILL DISPLAY THE GRADES TO THE TEXTAREA
        for (int i = 0; i < mainFrame.getTotalUserSubject(); i++) {
            if (!mainFrame.getTotalGwa().equals("") && !mainFrame.getTotalUnits().equals("")) {
                //TEXT AREA WILL BE APPENDED
            	toSave.append(Double.toString(mainFrame.getTotalGwa()[i]) + "\t\t" + Double.toString(mainFrame.getTotalUnits()[i]) + "\n\t");
            }
        }
        toSave.append("Total GWA:      " + new BigDecimal(mainFrame.displayFinalGrade).setScale(4, RoundingMode.HALF_UP) + "\n");
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (saveFile == clicked) {
        	// THIS WILL SAVE THE TEXTAREA TO THE FILE MANAGER OF YOUR PC
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int r = fileChooser.showSaveDialog(fileChooser);

            if (r == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                //THIS WILL CREATE A FILE WITH A .WORD ENDING
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file + ".doc"))) {
                    writer.write(toSave.getText());
                    JOptionPane.showMessageDialog(null, "File Saved Successfully");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error in saving file");
                }
            }
        }
    }
}
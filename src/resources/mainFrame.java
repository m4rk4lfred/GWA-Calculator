package resources;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;

import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.SwingConstants;

public class mainFrame extends JFrame implements ActionListener {
    private static mainFrame mainFrame;
    private JTable gradesTable;
    private DefaultTableModel model;
    private JPanel mainPanel;
    private static int totalUserSubjects = 0;
    private static double[] totalUnits = new double[50];
    JButton addSubjects;
    private JTextField gradeField;
    private JTextField unitField;
    private static double[] totalGwa = new double[50];
    private JButton getAverageButton;
    private JLabel AverageLabel;
    private JButton saveButton;
    private JPanel finalGradePanel;
    public static double displayFinalGrade;
    JButton removeButton;
    public mainFrame() {
        initialize();
    }

    public static mainFrame getInstance() {
        if (mainFrame == null) {
            mainFrame = new mainFrame();
        }

        return mainFrame;
    }

    private void initialize() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(100, 100, 880, 637);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("General Weighted Average Calculator");
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Lenovo\\OneDrive\\Pictures\\gradeCalculator.png");
        icon.getScaledInstance(1, 1, Image.SCALE_SMOOTH);
        this.setIconImage(icon);
        this.setIconImage(icon);
        this.setResizable(false);
        getContentPane().setLayout(null);
        

        UIManager.put("ToolTip.background", new Color(251, 248, 241));
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(251, 248, 241));
        mainPanel.setBounds(0, 0, 866, 600);
        mainPanel.setLayout(null);


        //THIS IS THE 3 HEADER FOR THE JTABLE
        String labels[] = {
            "ID",
            "Grades",
            "Units"
        };
        //LINES BELOW IS JUST SETTING ALL THE NODES TO THE FRAME
        model = new DefaultTableModel(new Object[0][0], labels);

        gradesTable = new JTable();
        gradesTable.setBackground(new Color(240, 235, 227));
        gradesTable.setSelectionBackground(new Color (84, 186, 185));
        gradesTable.setModel(model);
        gradesTable.setBounds(36, 46, 548, 532);
        gradesTable.getTableHeader().setReorderingAllowed(false);
        gradesTable.getTableHeader().setBackground(new Color(233, 218, 193));
        
        JScrollPane scroller = new JScrollPane();
        scroller.setLocation(46, 106);
        scroller.setSize(new Dimension(792, 436));
        scroller.setViewportView(gradesTable);
        scroller.getViewport().setBackground(new Color(247, 236, 222));

        addSubjects = new JButton("Add Subject");
        addSubjects.setBackground(new Color(251, 248, 241));
        addSubjects.setFont(new Font("Tahoma", Font.PLAIN, 11));
        addSubjects.setBounds(46, 44, 95, 31);
        addSubjects.addActionListener(this);
        addSubjects.addMouseListener(new mouseAdapter());
        mainPanel.add(addSubjects);



        mainPanel.add(scroller);
        getContentPane().add(mainPanel);

        gradeField = new JTextField();
        gradeField.setBackground(Color.WHITE);
        gradeField.setBounds(149, 44, 130, 30);
        gradeField.setColumns(10);
        gradeField.setToolTipText("ENTER GRADE HERE");
        mainPanel.add(gradeField);


        unitField = new JTextField();
        unitField.setBackground(Color.WHITE);
        unitField.setBounds(289, 44, 130, 31);
        unitField.setToolTipText("ENTER UNITS HERE");
        mainPanel.add(unitField);
        unitField.setColumns(10);

        getAverageButton = new JButton("Compute");
        getAverageButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        getAverageButton.setBackground(new Color(251, 248, 241));
        getAverageButton.setBounds(423, 42, 95, 31);
        getAverageButton.addActionListener(this);
        getAverageButton.addMouseListener(new mouseAdapter());
        mainPanel.add(getAverageButton);

        JLabel labelFinalGrade = new JLabel("Final Grade");
        labelFinalGrade.setHorizontalAlignment(SwingConstants.CENTER);
        labelFinalGrade.setBounds(139, 11, 130, 31);
        mainPanel.add(labelFinalGrade);

        JLabel labelUnits = new JLabel("Units");
        labelUnits.setHorizontalAlignment(SwingConstants.CENTER);
        labelUnits.setBounds(279, 11, 130, 31);
        mainPanel.add(labelUnits);

        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        saveButton.setBackground(new Color(251, 248, 241));
        saveButton.setBounds(528, 42, 95, 31);
        saveButton.addActionListener(this);
        saveButton.addMouseListener(new mouseAdapter());
        mainPanel.add(saveButton);

        finalGradePanel = new JPanel();
        finalGradePanel.setBounds(46, 553, 792, 36);
        finalGradePanel.setLayout(null);
        mainPanel.add(finalGradePanel);


        AverageLabel = new JLabel();
        AverageLabel.setText("Final Grade:");
        AverageLabel.setBounds(10, 0, 782, 36);
        AverageLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        finalGradePanel.add(AverageLabel);

        removeButton = new JButton("Remove");
        removeButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        removeButton.setBackground(new Color(251, 248, 241));
        removeButton.setBounds(633, 42, 95, 31);
        removeButton.addActionListener(this);
        removeButton.addMouseListener(new mouseAdapter());
        mainPanel.add(removeButton);



        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        try {
            if (addSubjects == clicked) {

                try {
                	//THIS NEW DATA WILL SERVE US THE ARRAY THAT WILL STORE THAT DATA TO THE MODEL.AND THE UNITS AND THE FINAL GRADE WILL BE STORED SEPERATELY DUE TO THE COMPUTATION LATER
                    String[] newData = new String[3];
                    newData[0] = Integer.toString(totalUserSubjects + 1);
                    newData[1] = gradeField.getText();
                    newData[2] = unitField.getText();


                    totalGwa[totalUserSubjects] = Double.parseDouble(newData[1]);
                    totalUnits[totalUserSubjects] = Double.parseDouble(newData[2]);
                    model.addRow(newData);
                    totalUserSubjects++;
                } catch (Exception a) {
                    JOptionPane.showMessageDialog(null, "Invalid Input", "Enter a Integer", JOptionPane.ERROR_MESSAGE);
                }
                gradeField.setText("");
                unitField.setText("");
            }

            if (clicked == getAverageButton) {
                double showGwa = computeGwa();
                AverageLabel.setText("Final Grade: " + new BigDecimal(showGwa).setScale(4, RoundingMode.HALF_UP));

                if (showGwa > 3.0) {
                    finalGradePanel.setBackground(Color.RED);
                    finalGradePanel.setBorder(null);

                }
                if (showGwa <= 3 && showGwa >= 1) {
                    finalGradePanel.setBackground(Color.GREEN);
                    finalGradePanel.setBorder(null);
                }

            }
            if (clicked == saveButton) {
                new saveButton();

            }

            if (clicked == removeButton) {
                removeGwa();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Please Input a data first", "Enter the Needed data", JOptionPane.ERROR_MESSAGE);
        }


    }
    public double computeGwa() {
        double[] multipliedGwa = new double[20];
        double AddedGwa = 0;
        double addedUnits = 0;
        double finalGwa;
        
        //THIS FOR LOOP WILL FIRST MULTIPLY THE TOTAL GRADES TO THE TOTAL UNITS, THIS IS THE REASON WHY THE UNITS AND GRADES IS SEPERATED
        for (int i = 0; i < totalUserSubjects; i++) {
            multipliedGwa[i] = totalUnits[i] * totalGwa[i];
            
        }
        //THIS WILL ADD ALL THE MULTIPLED GRADES AND UNITS TO THE ADDED GRADES SO THAT WE CAN DIVIDE THEIR TOTAL TO EACH OTHER
        for (int j = 0; j < totalUserSubjects; j++) {
            AddedGwa += multipliedGwa[j];
            addedUnits += totalUnits[j];
        }
        
        finalGwa = AddedGwa / addedUnits;

        displayFinalGrade = finalGwa;
        return finalGwa;
    }

    public void removeGwa() {
        //REMOVING THE SELECTED ROW FROM THE TABLE MODEL AND TO THE ARRAY
    	int selected = gradesTable.getSelectedRow();
        model.removeRow(selected);
        
        //MAKING THE SELECTED ROW VALUE TO 0
        totalGwa[selected] = 0.0;
        totalUnits[selected] = 0.0;
        totalUserSubjects--;
        for (int i = 0; i < totalUserSubjects; i++) {
           //WHENEVER THIS IF STATEMENT ENCOUNTER 0 ALONG IT'S WAY  THE CURRENT ITERATION WILL GET THE VALUE OF THE ELEMENT NEXT TO IT AND THE VALUE OF THE ELEMENT THAT BEEN MOVE WILL BECOME 0
           //SO THAT WHEN THE ZERO IS AT THE END OF THE ARRAY IT WILL STOP PRINTING BECAUSE THE TOTAL USER SUBJECTS WILL DECREMENT
           // FOR EXAMPLE INT[] GRADE = {1,2,3,4,5}; THE 3 WILL BE REMOVE
           //INT[] GRADE = {1,2,0,4,5};BECAUSE IT ENCOUNTER 0 THE 4 WILL BE ASSIGN TO THE 0 AND THE 4 WILL BECOME ZER0
           // GOES LIKE THIS {1,2,4,0,5};{1,2,4,5,0} 
           //WHENEVER WE REMOVE A GRADE THE TOTALUSERSUBEJCT WILL BE DECREMENTED TOO SO THAT THE 0 AT THE END WONT BE PRINTED
        	if (totalGwa[i] == 0 && totalUnits[i] == 0) {
                totalGwa[i] = totalGwa[i + 1];
                totalGwa[i + 1] = 0.0;

                totalUnits[i] = totalUnits[i + 1];
                totalUnits[i + 1] = 0.0;
            }
        }


    }


    public static double[] getTotalGwa() {
        return totalGwa;
    }
    public static int getTotalUserSubject() {
        return totalUserSubjects;
    }
    public static double[] getTotalUnits() {
        return totalUnits;
    }


}
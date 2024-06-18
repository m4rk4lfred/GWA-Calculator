package resources;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class mouseAdapter extends MouseAdapter {
      
    public void mouseEntered(MouseEvent e) {
       JButton button = (JButton)e.getSource();
       button.setBackground(Color.LIGHT_GRAY);
    }
    
    public void mouseExited(MouseEvent e) {
    	JButton button = (JButton)e.getSource();
    	button.setBackground(Color.WHITE);
    }
}
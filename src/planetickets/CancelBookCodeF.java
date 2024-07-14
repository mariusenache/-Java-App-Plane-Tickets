
package planetickets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CancelBookCodeF  extends JFrame{
    

    private FlightsAgenda fa;
    private JButton b;  
    private GestorEvenimenteCBC ec;
    private JTextField code;

    public CancelBookCodeF(){
        super("Cancel Booking");

        setSize(420,120);
        setLocation(420, 420);
        setBackground(Color.lightGray);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        ec = new GestorEvenimenteCBC();

        JPanel p = new JPanel();
        p.add(new JLabel("Input reservation code: "));
        p.add(code = new JTextField(10));

        b = new JButton("Next");
        b.addActionListener(ec);
        
        add(p);
        add(b);
    }
    private class GestorEvenimenteCBC implements ActionListener {
        private JFrame f;
        GestorEvenimenteCBC(){
			fa = FlightsAgenda.getInstance();
		}
        public void actionPerformed(ActionEvent e){  
            
            if(e.getSource()==b){
                if(fa.findReservation(code.getText()))
                {
                    f = new CancelBookConfirmF();
                    f.setLocation(420,100);
                    f.setVisible(true);
                    CancelBookCodeF.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No flight found with this code", "opa", JOptionPane.ERROR_MESSAGE);
                }                
            }
        }  
    }
}

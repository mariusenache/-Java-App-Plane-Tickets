package planetickets;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModifyBookCodeF  extends JFrame{
    private FlightsAgenda fa;
    private JButton b;  
    private GestorEvenimenteCBC ec;
    private JTextField code;

    public ModifyBookCodeF(){
        super("Modify Booking");
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
                if(fa.findReservation(code.getText()))   //(!) fa.setReservationToEdit(/this/) has also been called (!)   
                {
                    fa.setOutboundFlight(fa.getReservationToEdit().getFlOut());
                    f = new ModifyBookOptionsF();
                    f.setLocation(420,100);
                    f.setVisible(true);
                    ModifyBookCodeF.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No reservation found with this code", "opa", JOptionPane.ERROR_MESSAGE);
                }                
            }
        }  
    }
}

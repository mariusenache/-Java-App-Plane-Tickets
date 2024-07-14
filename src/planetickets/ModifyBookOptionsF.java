package planetickets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModifyBookOptionsF extends JFrame {
    
    private FlightsAgenda fa;
    private JButton b1,b2;  
    private GestorEvenimenteCC ec;
    private JTextArea ta;
    private JTextField fn, nm, ct, cn, ed;
   
    public ModifyBookOptionsF(){
        super("Modify Booking Options");
        fa = FlightsAgenda.getInstance();
        ec = new GestorEvenimenteCC();

        setSize(420,550);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setBackground(new Color(160,190,220));


        JPanel p0 = new JPanel();    
        p0.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        p0.add(new JLabel("----------------------------------Booking Modify----------------------------------"));
        p0.add(new JLabel("Reservation details:"));

        JPanel p1 = new JPanel();
        JTextArea jta= new JTextArea("\nPassenger name:"+fa.getReservationToEdit().getName()+"\n\n"
                                        +"Reservation code: "+fa.getReservationToEdit().getCodR()+"\n\n"
                                        +"Outbound flight:\n"+fa.getReservationToEdit().getFlOut()+"\n\n"
                                        +"Inbound flight:\n"+fa.getReservationToEdit().getFlIn()+"\n\n"
                                        +"Travel class: "+fa.getReservationToEdit().gettClass()+"\n\n"
                                        +"Seat Inbound: "+fa.getReservationToEdit().getRSeatI()+"\n"
                                        +"Seat Outbound: "+fa.getReservationToEdit().getRSeatO()+"\n");
        jta.setEditable(false);
        p1.add(jta);

        JPanel p2 = new JPanel();    
        p2.add(new JLabel("Please choose what do you want to modify"));

        JPanel p3 = new JPanel();
        b1=new JButton("Change both departure and return dates");  
        b1.addActionListener(ec);  
        p3.add(b1);
        
        JPanel p4 = new JPanel();
        p4.setBorder(BorderFactory.createEmptyBorder(0,30,0,30));
        b2=new JButton("Change only the return date");  
        b2.addActionListener(ec);  
        p4.add(b2);

        add(p0);
        add(p1);
        add(p2);
        add(p3);
        add(p4);
    }
    private class GestorEvenimenteCC implements ActionListener {
        private JFrame f;
        GestorEvenimenteCC(){
			fa = FlightsAgenda.getInstance();
		}
        public void actionPerformed(ActionEvent e){  
        
            if(e.getSource() == b1) {
                fa.setModifyingReservation(true);
                f = new SearchFlightFrame();
				f.setSize(400, 300);
				f.setLocation(420,420);
                f.setVisible(true);
                ModifyBookOptionsF.this.dispose();

            } else 
            if (e.getSource() == b2){
                fa.setModifyingReservation(true);
                fa.setReturnDate(fa.getReservationToEdit().getFlIn().getDate());
                if(fa.generateInboundFlights())
                {
                    f = new InputReturnDateF();
                    f.setVisible(true);
                    ModifyBookOptionsF.this.dispose();
                } else JOptionPane.showMessageDialog(null, "No other return flights found. Sorry.", "opa", JOptionPane.ERROR_MESSAGE);

                
                
            }
        }   
    }  
}


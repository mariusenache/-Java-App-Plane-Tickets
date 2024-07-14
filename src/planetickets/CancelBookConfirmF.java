package planetickets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CancelBookConfirmF extends JFrame{
    private FlightsAgenda fa;
    private JButton b;  
    private GestorEvenimenteCC ec;
    private JTextArea ta;
    private JTextField fn, nm, ct, cn, ed;
   
    public CancelBookConfirmF(){
        super("Confirm Cancel Booking");
        fa = FlightsAgenda.getInstance();
        ec = new GestorEvenimenteCC();

        setSize(420,600);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setBackground(new Color(160,190,220));


        JPanel p0 = new JPanel();    
        p0.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        p0.add(new JLabel("----------------------------------Booking Canceling----------------------------------"));
        p0.add(new JLabel("Reservation details:"));

        JPanel p1 = new JPanel();
        JTextArea jta= new JTextArea("\nPassenger name:"+fa.getReservationToEdit().getName()+"\n\n"
                                        +"Reservation code: "+fa.getReservationToEdit().getCodR()+"\n\n"
                                        +"Outbound flight:\n"+fa.getReservationToEdit().getFlOut()+"\n\n"
                                        +"Inbound flight:\n"+fa.getReservationToEdit().getFlIn()+"\n\n"
                                        +"Travel class: "+fa.getReservationToEdit().gettClass()+"\n\n"
                                        +"Seat Inbound: "+fa.getReservationToEdit().getRSeatI()+"\n"
                                        +"Seat Outbond: "+fa.getReservationToEdit().getRSeatO()+"\n");
        jta.setEditable(false);
        p1.add(jta);

        JPanel p2 = new JPanel();    
        p2.add(new JLabel("Please input card details to get the refund"));

        JPanel p3 = new JPanel();    //card details
		p3.setLayout(new GridLayout(4,2,0,5)); 
        p3.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));

        p3.add(new JLabel("Name on card:"));
        nm = new JTextField(10);
        p3.add(nm);
        p3.add(new JLabel("Card type:"));
        String[] cardtype = {"MasterCard", "Visa"};
        JComboBox<String>  ct = new JComboBox<String>(cardtype);    
        p3.add(ct);
        p3.add(new JLabel("Card number:"));
        cn = new JTextField(10);
        p3.add(cn);
        p3.add(new JLabel("Expiration date:"));
        ed = new JTextField(10);
        p3.add(ed);

        JPanel p4 = new JPanel();
        p4.setBorder(BorderFactory.createEmptyBorder(0,30,0,30));
        p4.add(new JLabel("I agree with the terms and conditions."));
        b=new JButton("Confirm and cancel reservation");  
        b.addActionListener(ec);  
        p4.add(b);

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
        
            // if(nm.getText() == fa.getReservationToEdit().getName() 
            // && ct.getText() == fa.getReservationToEdit().getCardType()
            // && cn.getText() == fa.getReservationToEdit().getCardNo()
            // && ed.getText() == fa.getReservationToEdit().getCardExp())
            // {
            //     fa.deleteReservation(fa.getReservationToEdit());
            //     JOptionPane.showMessageDialog(null, "The reservation has been canceled. "+(int)fa.getReservationToEdit().getRPrice()
            //                     +" euro will be transfered to your account.", "Canceling Successful", JOptionPane.INFORMATION_MESSAGE);
            //     fa.saveBookings();
            // } else {
            //     JOptionPane.showMessageDialog(null, "Card details wrong; guess again", "opa", JOptionPane.ERROR_MESSAGE);
            // } 

            fa.deleteReservation(fa.getReservationToEdit());
            JOptionPane.showMessageDialog(null, "The reservation has been canceled. "+(int)fa.getReservationToEdit().getRPrice()
                            +" euro will be transfered to your account.", "Canceling Successful", JOptionPane.INFORMATION_MESSAGE);
            fa.saveBookings();
            CancelBookConfirmF.this.dispose();
        }  
    }
}

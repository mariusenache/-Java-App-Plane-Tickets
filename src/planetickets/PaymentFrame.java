package planetickets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class PaymentFrame extends JFrame{
    private FlightsAgenda fa;
    private JButton b;  
    private GestorEvenimentePF ec;
    private JTextField nm, cn, ed;
    private JComboBox<String>  cardComboBox;

    public PaymentFrame(){
        super("Payment 100% secure");
        fa = FlightsAgenda.getInstance();
        ec = new GestorEvenimentePF();

        setSize(350,270);
        setBackground(Color.lightGray);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel p1 = new JPanel();    
        p1.add(new JLabel("Please input card details:"));

        JPanel p2 = new JPanel();    //card details
		p2.setLayout(new GridLayout(4,2,0,5)); 
        p2.setBorder(BorderFactory.createEmptyBorder(0,30,0,30));

        p2.add(new JLabel("Name on card:"));
        nm = new JTextField(10);
        p2.add(nm);

        p2.add(new JLabel("Card type:"));
        String[] cardtype = {"MasterCard", "Visa"};
        cardComboBox = new JComboBox<String>(cardtype);   
        //cardComboBox.addActionListener(ec);     
        p2.add(cardComboBox);

        p2.add(new JLabel("Card number:"));
        cn = new JTextField(10);
        p2.add(cn);
        p2.add(new JLabel("Expiration date:"));
        ed = new JTextField(10);
        p2.add(ed);

        JPanel p3 = new JPanel();
        p3.setBorder(BorderFactory.createEmptyBorder(0,30,0,30));
        p3.add(new JLabel("You will get a seat reservation on the ticket."));
        b=new JButton("Finish payment");  
        b.addActionListener(ec);  
        p3.add(b);

        add(p1);
        add(p2);
        add(p3);
    }

    private class GestorEvenimentePF implements ActionListener {
        private JFrame f;
        GestorEvenimentePF(){
			fa = FlightsAgenda.getInstance();
		}
        public void actionPerformed(ActionEvent e){  
            String codRez, cardType;
            codRez=fa.generateRezervCod();
            int giveSeat = (int)(Math.random() * 400) + 1;
  
           
            Reservation newR;
            try {
                newR = new Reservation(codRez, nm.getText(), (String)cardComboBox.getSelectedItem(), cn.getText(), ed.getText(), fa.getOutboundFlight(), 
                                                    fa.getInboundFlight(), fa.getTravelClass(), fa.getSeat(fa.getInboundFlight()),fa.getSeat(fa.getOutboundFlight()), fa.getRoundTripPrice());
                                                    fa.setNewReserv(newR);
            fa.addReserv(newR);
                                                    
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }    
            // Flight ftemp1 = new Flight("F111","10.10.2022","AAA","BBB","11:12","13:13","1720");
            // Flight ftemp2 = new Flight("F112","15.07.2023","BBB","AAA","11:12","13:13","1720");
            // Reservation newR = new Reservation(codRez, "dana banana", "cardType", "cardNo",  "cardExp",ftemp1,ftemp2, "business", fa.getSeat(), 1720);
            
            
            fa.saveBookings();
            
            f = new TicketFrame();
            f.setLocation(420,100);
            f.setVisible(true);
            PaymentFrame.this.dispose();
        }  
    }
 
}

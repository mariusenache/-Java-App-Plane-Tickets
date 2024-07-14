package planetickets;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChooseClassF extends JFrame{
    private ButtonGroup checkBoxes;             //ca sa bifezi doar 1x
    private JCheckBox cb1,cb2; 
    private FlightsAgenda fa;
    private JButton b;  
    private GestorEvenimenteCCF ec;

    public ChooseClassF(){
        super("Choose Your Class");
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS ));

        fa = FlightsAgenda.getInstance();
        ec = new GestorEvenimenteCCF();
        

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout(0, 10));
		p1.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));
		p1.setBackground(new Color(104,131,188));
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(2,2,10,10));
		p2.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		p2.setBackground(new Color(160,190,220));
		JPanel p3 = new JPanel();
		//p3.setLayout(null);
		p3.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		p3.setBackground(new Color(104,131,188));


        JTextArea textRoundTrip= new JTextArea(fa.getOutboundFlight().toString()+"\n"+fa.getInboundFlight().toString());
        textRoundTrip.setEditable(false);
        JLabel textYourF = new JLabel("Your flight:");
        JLabel textChoose = new JLabel("Choose class:");
        p1.add(textYourF, BorderLayout.NORTH);
        p1.add(textRoundTrip, BorderLayout.CENTER);
        p1.add(textChoose, BorderLayout.SOUTH);
        
        checkBoxes = new ButtonGroup();
        cb1 = new JCheckBox("Economy class      ||  price: "+fa.getRoundTripPrice()+" euro");
        JLabel textEc = new JLabel(">>> budget friendly travel");
        cb2 = new JCheckBox("Bussiness class    ||  price: "+fa.getRoundTripPrice()*1.7+" euro");
        JLabel textBu = new JLabel("<html>>>> flatbed seating fully dedicated cabin, access to airport lounges,</br> dedicated check-in and faster security lines.</html>");
        checkBoxes.add(cb1);
        checkBoxes.add(cb2);

        p2.add(cb1);
        p2.add(textEc);
        p2.add(cb2);
        p2.add(textBu);

        b=new JButton("Proceed to payment");  
        b.addActionListener(ec);  
        p3.add(b);

        add(p1); add(p2); add(p3);
    }

    private class GestorEvenimenteCCF implements ActionListener {
        private JFrame f;

        GestorEvenimenteCCF(){
			fa = FlightsAgenda.getInstance();
		}

        public void actionPerformed(ActionEvent e){  

            if(cb1.isSelected()) fa.setTravelClass("economy");
            if(cb2.isSelected()) fa.setTravelClass("bussiness");

            if(fa.isModifyingReservation()){
                f = new ModifyBookPaymentF();
                f.setSize(480, 320);
                f.setLocation(420,100);
                f.setVisible(true);
                ChooseClassF.this.dispose();
            } else {
                f = new PaymentFrame();
                f.setSize(480, 320);
                f.setLocation(420,100);
                f.setVisible(true);
                ChooseClassF.this.dispose();
            }
        }  
    }

}
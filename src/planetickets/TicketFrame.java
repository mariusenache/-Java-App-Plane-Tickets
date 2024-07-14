package planetickets;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicketFrame extends JFrame{
    private FlightsAgenda fa;
    private JButton b;  
    private GestorEvenimentePF ec;

    public TicketFrame(){
        super("Ticket");
        fa = FlightsAgenda.getInstance();
        ec = new GestorEvenimentePF();
        setSize(420,400);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setBackground(new Color(160,190,220));

        JPanel p0 = new JPanel();    
        p0.setBorder(BorderFactory.createEmptyBorder(10,0,10,+0));
        p0.add(new JLabel("----------------------------------Flight ticket----------------------------------"));
        JPanel p1 = new JPanel();
        JTextArea pname= new JTextArea("\nPassenger name:"+fa.getNewReserv().getName()+"\n\n"
                                        +"Reservation code: "+fa.getNewReserv().getCodR()+"\n\n"
                                        +"Outbound flight:\n"+fa.getNewReserv().getFlOut()+"\n\n"
                                        +"Inbound flight:\n"+fa.getNewReserv().getFlIn()+"\n\n"                                        
                                        +"Travel class: "+fa.getNewReserv().gettClass()+"\n\n"
                                        +"Outbound Seat: "+fa.getNewReserv().getRSeatO()+"; "
                                        +"Inbound Seat: "+fa.getNewReserv().getRSeatI()+"\n"
                                        +"Price: "+fa.getNewReserv().getRPrice()+"euro.\n");
        pname.setEditable(false);
        p1.add(pname);

        JPanel p2 = new JPanel(); 
        p2.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        b = new JButton("OK");
        b.addActionListener(ec);
        p2.add(b);

        add(p0);
        add(p1);
        add(p2);
    }

    private class GestorEvenimentePF implements ActionListener {
        GestorEvenimentePF(){
			fa = FlightsAgenda.getInstance();
		}
        public void actionPerformed(ActionEvent e){  
            if (e.getSource() == b) {
				TicketFrame.this.dispose();
			  } 
        }  
    }
}

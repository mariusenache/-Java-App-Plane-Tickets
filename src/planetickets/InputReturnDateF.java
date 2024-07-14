
package planetickets;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputReturnDateF  extends JFrame{
    private FlightsAgenda fa;
    private JButton b;  
    private GestorEvenimenteCBC ec;
    private JTextField retDat;

    public InputReturnDateF(){
        super("Return Date");
        setSize(320,120);
        setLocation(420, 420);
        setBackground(Color.lightGray);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        ec = new GestorEvenimenteCBC();

        JPanel p = new JPanel();
        p.add(new JLabel("Input Return Date: "));
        p.add(retDat = new JTextField(10));

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
                    fa.setReturnDate(retDat.getText());
                    if( fa.returnExists(fa.getReturnDate()) && fa.generateInboundSelection() )
                     {
                        System.out.println(fa.getInboundFlights());
                        f = new FlightInboundF();
                        f.setSize(550, 260);
					    f.setLocation(420,100);
                        f.setVisible(true);
                        InputReturnDateF.this.dispose();
                    }else {
                        JOptionPane.showMessageDialog(null, "This date didn't return any flights.", "opa", JOptionPane.ERROR_MESSAGE);
}             }
        }  
    }
}

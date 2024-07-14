package planetickets;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchFlightFrame extends JFrame {
    private GestorEvenimenteSFF ec;
    private JTextArea ta;
    private JTextField ft, tt, dt, rt;
    private JButton search, showList;
    private String returnDate;

    public SearchFlightFrame() {
        super("Seach for a flight");

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(5, 2, 10, 10));
        p1.setBackground(Color.lightGray);
        p1.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        p1.add(new JLabel("From:"));
        ft = new JTextField(10);
        p1.add(ft);
        p1.add(new JLabel("To"));
        tt = new JTextField(10);
        p1.add(tt);
        p1.add(new JLabel("Departure date:"));
        dt = new JTextField(10);
        p1.add(dt);
        p1.add(new JLabel("Return date:"));
        rt = new JTextField(10);
        p1.add(rt);

        ec = new GestorEvenimenteSFF();
        showList = new JButton("List available flights");
		showList.addActionListener(ec);
		p1.add(showList, BorderLayout.CENTER);
       
        search = new JButton("Search flights");
        search.addActionListener(ec);
        p1.add(search);

        add(p1,BorderLayout.NORTH);

        ta = new JTextArea(10, 50);
        add(ta);
		setSize(300, 200);
		setLocation(300, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private class GestorEvenimenteSFF implements ActionListener {
        private FlightsAgenda fa;
        private Frame f;

        GestorEvenimenteSFF() {
            fa = FlightsAgenda.getInstance();
        }

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == search) {
                String searchInput = ft.getText() + " " + tt.getText() + " " + dt.getText() + " " + rt.getText();
                //ta.setText(fa.findSearchedFlight(searchInput));

                if(fa.findSearchedFlight(searchInput)) 
                {
                    ta.setText("Flight(s) found!");
                    returnDate = rt.getText(); 
                    fa.setReturnDate(returnDate);      
                    f = new FlightOutboundF();     
				    f.setSize(830, 618);
				    f.setLocation(300,100);
				    f.setVisible(true);
                    SearchFlightFrame.this.dispose();
                }
                ta.setText("No flights found for this search...");
                dt.setText("");
                rt.setText("");
            }

            if (e.getSource() == showList) {
                f = new UsefulShowFF();
				f.setSize(480, 320);
				f.setLocation(420,100);
				f.setVisible(true);
            }

        }
    }
}

package planetickets;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class FlightsListFrame extends JFrame {
	private FlightsAgenda fa;
	private GestorEvenimenteFLF ec;
	private JTextArea ta;
	private JTextField ft,tt,dt,rt;
	private JButton goSearch, choice, cancel;
	private JButton[] b;
	private BufferedReader br;
    private String l;
	public String[] flightsArr;
	private String[] s;

	public FlightsListFrame() {
		super("Flights Agenda");
		setSize(860, 710);
		setLocation(300,50);
		setLayout(new BorderLayout());

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setBackground(Color.lightGray);
		p1.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
		p1.setBackground(new Color(104,131,188));

		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(16, 2, 10, 10));
		p2.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		p2.setBackground(new Color(160,190,220));

		ec = new GestorEvenimenteFLF();
		goSearch = new JButton("Search for a flight");
		goSearch.addActionListener(ec);
		p1.add(goSearch, BorderLayout.CENTER);
		
		fa = FlightsAgenda.getInstance();
		
		String allFlights = fa.getFlights();
		flightsArr= allFlights.split("\n");
		b= new JButton[22];
	
		for (int i=0; i<flightsArr.length; i++)						
		{
			JLabel flightDetailsText = new JLabel(flightsArr[i]);		//System.out.println(c[i]);
			flightDetailsText.setBounds(20, 20, 100, 5);
			b[i] = new JButton("Choose flight");
			b[i].addActionListener(ec);

        	p2.add(flightDetailsText);
			p2.add(b[i], BorderLayout.CENTER);
		}

        add(p1, BorderLayout.CENTER);
		add(p2, BorderLayout.SOUTH);

		cancel = new JButton("Cancel");
		cancel.addActionListener(ec);
		p2.add(cancel, BorderLayout.SOUTH);
	}
	
	private class GestorEvenimenteFLF implements ActionListener {

		private FlightsAgenda fa;
		private JFrame f;

		GestorEvenimenteFLF(){
			fa = FlightsAgenda.getInstance();
		}
	
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == goSearch) {
				f = new SearchFlightFrame();
				f.setSize(400, 300);
				f.setLocation(420,420);
				f.setVisible(true);
				FlightsListFrame.this.dispose();
			}

			for(int i=0; i<b.length;i++){
				if(e.getSource()==b[i]){

					String codeSelected = flightsArr[i].toString().split(" ")[0];
					fa.setOutboundFlight(fa.findFlightByCode(codeSelected));
					f = new InputReturnDateF();
					f.setVisible(true);
					FlightsListFrame.this.dispose();
				}
			}

			if (e.getSource() == cancel) {
				FlightsListFrame.this.dispose();
			  } 
		}

	  }
}
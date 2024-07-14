
package planetickets;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class FlightOutboundF extends JFrame{

    private FlightsAgenda fa;
	private GestorEvenimenteSRF ec;
	private JButton choice, cancel;
	private JButton[] b;
	public String[] chosen;
	public String[] flightCodesArr = new String[11];

    public FlightOutboundF(){
		super("Search Results - Outbound Flights:");
		setLocation(150, 10);	//locatia freameului
		setLayout(new BorderLayout());

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setBorder(BorderFactory.createEmptyBorder(15,30,15,30));
		p1.setBackground(new Color(104,131,188));
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(13, 2, 10, 10));
		p2.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		p2.setBackground(new Color(160,190,220));
		JPanel p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		p3.setBorder(BorderFactory.createEmptyBorder(15,30,15,30));
		p3.setBackground(new Color(104,131,188));


		JLabel selectText = new JLabel("Select Outbound Flight:");
		p1.add(selectText);

		ec = new GestorEvenimenteSRF();		
		fa = FlightsAgenda.getInstance();
		String[] c;
		String searchReturned = fa.getSearchedFlights();
		c= searchReturned.split("\n");
		b= new JButton[12];
		System.out.println("getsf: --- " + fa.getSearchedFlights()+"\n----------");
		
		int i=0;
	
		for (i=0; i<c.length; i++)						
		{
			JLabel flightDetailsText = new JLabel(c[i]);	
			flightDetailsText.setBounds(20, 20, 100, 5);
			b[i] = new JButton("Choose flight");
			b[i].addActionListener(ec);
			flightCodesArr[i] = c[i].split(" ")[0]; 		System.out.println("======cod: "+flightCodesArr[i] +"\n");
        	p2.add(flightDetailsText);
			p2.add(b[i], BorderLayout.CENTER);
		}

		add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);
		add(p3, BorderLayout.SOUTH);

		cancel = new JButton("Cancel");
		cancel.addActionListener(ec);
		p3.add(cancel, BorderLayout.SOUTH);
		
	}
	

    private class GestorEvenimenteSRF implements ActionListener {
		private JFrame f;

		GestorEvenimenteSRF(){
			fa = FlightsAgenda.getInstance();
		}
	
		public void actionPerformed(ActionEvent e) {
			
			for(int i=0;i<b.length;i++)
			{
				if (e.getSource() == b[i]){
					fa.generateInboundFlights();
					fa.setOutboundFlight(fa.findFlightByCode(flightCodesArr[i]));
					f = new FlightInboundF();
					f.setSize(550, 260);
					f.setLocation(420,100);
					f.setVisible(true);
					FlightOutboundF.this.dispose();
				}
			}
			if (e.getSource() == cancel) {
				FlightOutboundF.this.dispose();
			  } 
		}
	  }
}




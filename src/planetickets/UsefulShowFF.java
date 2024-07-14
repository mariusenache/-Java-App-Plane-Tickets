package planetickets;

import java.awt.*;
import javax.swing.*;

public class UsefulShowFF extends JFrame {
	private JTextArea ta;
	private FlightsAgenda fa;
	private JButton goSearch;

	public UsefulShowFF() {

		super("The Show Available Flights Frame");

		fa = FlightsAgenda.getInstance();

		String[] c;
		String carnat = fa.getFlights();
		c= carnat.split("\n");

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(12, 2, 10, 10));
		p.setBorder(BorderFactory.createEmptyBorder());

		for (int i=0; i<c.length; i++)
		{
			JLabel text1 = new JLabel(c[i]);
			text1.setBounds(20, 20, 100, 10);
        	p.add(text1);
		}

		add(p);
	}

}
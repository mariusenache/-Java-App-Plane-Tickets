
package planetickets;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MainFrame extends JFrame {
	private GestorEvenimenteMF ec;
	private JButton[] b;

	public MainFrame() {
		super("Welcome to the flying app!");
        
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4, 1, 10, 10));
        p.setBackground(Color.LIGHT_GRAY);
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel text1 = new JLabel("<html>This is the flying reservation app.<br>Go ahead and make your choice.</html>");
        text1.setBounds(20, 20, 100, 10);
        p.add(text1);

		String[] s = new String[] { "Book a flight", "Modify booking", "Cancel booking"};
		ec = new GestorEvenimenteMF();
		b = new JButton[4];
		for (int i = 0; i < s.length; i++) {
			b[i] = new JButton(s[i]);
			b[i].addActionListener(ec);
			p.add(b[i]);
		}
		add(p);
	}

	private class GestorEvenimenteMF implements ActionListener {
		private JFrame f;

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == b[0]) {
				f = new FlightsListFrame();
				f.setVisible(true);

			} else if (e.getSource() == b[1]) {
				f = new ModifyBookCodeF();
				f.setVisible(true);
			}

			else if (e.getSource() == b[2]) {
				f = new CancelBookCodeF();
				f.setVisible(true);
			}
		}
	}

}
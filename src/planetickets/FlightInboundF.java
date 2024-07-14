package planetickets;

import java.awt.*;
import javax.swing.*;  
import java.awt.event.*; 

public class FlightInboundF extends JFrame {
    private JLabel lb;  
    private JCheckBox[] cb;  
    private String[] flightCodesArr = new String[11];
	private JButton b;  
    private FlightsAgenda fa;
    private GestorEvenimenteIFF ec;
    private ButtonGroup checkBoxGroup;          //ca sa bifezi maxim o casuta

    
    public FlightInboundF(){  
        super("Inbound Flight");
        setLocation(150, 10);	//locatia freameului
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); 

        lb=new JLabel("Choose Return Flight:");  
        lb.setBounds(50,50,300,20);
        add(lb);

        fa = FlightsAgenda.getInstance();
		String[] inf;
		String inbFlights = fa.getInboundFlights();
        checkBoxGroup = new ButtonGroup();
        ec = new GestorEvenimenteIFF();
		inf= inbFlights.split("\n");
        cb = new JCheckBox[inf.length];
        
        for (int i=0; i<inf.length; i++)						
		{
			cb[i]  = new JCheckBox("Flight: "+inf[i]);
            checkBoxGroup.add(cb[i]);
            flightCodesArr[i] = inf[i].split(" ")[0];
            add(cb[i]);
		}


        b=new JButton("Choose flight");  
        b.setBounds(100,250,80,30);  
        b.addActionListener(ec);  
        add(b);  

        setVisible(true);  
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
    }  

    private class GestorEvenimenteIFF implements ActionListener {
        private JFrame f;

        GestorEvenimenteIFF(){
			fa = FlightsAgenda.getInstance();
		}

        public void actionPerformed(ActionEvent e){  
            for(int i=0;i<cb.length;i++)
            {
                if (cb[i].isSelected()){
                   
                    fa.setInboundFlight(fa.findFlightByCode(flightCodesArr[i]));
                    f = new ChooseClassF();
                    f.setSize(650, 420);
					f.setLocation(420,100);
					f.setVisible(true);
                    FlightInboundF.this.dispose();
                }
            }
        }  
    }
    
}
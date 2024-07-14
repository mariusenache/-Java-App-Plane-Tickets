package planetickets;
import java.util.*;
import java.io.*;
import java.util.regex.*;

public class FlightsAgenda {
	private TreeSet<Flight> ts, tsSearched, tsInbound;
	private TreeSet<Reservation> tsReserv;
	private BufferedReader br;
	private PrintWriter pw;
	private String l ,returnDate,travelClass;
	private Flight flight, flightOut, flightIn;
	private Reservation ReservationToEdit, newReserv;
	private static FlightsAgenda instance;
	private boolean success=false;
	private ArrayList<Integer> seatList = new ArrayList<>();
	private boolean ModifyingReservation = false;

	private FlightsAgenda() {
		File f = new File("flights.txt");
		ts = new TreeSet<>();
		tsReserv = new TreeSet<>();
		String[] s;
		if (f.exists()) {
			try {
				br = new BufferedReader(new FileReader(f));
				while ((l = br.readLine()) != null) {
					s = l.split(" ");		
					flight = new Flight(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7]);
					ts.add(flight);
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		} else
			System.out.println("File flights.txt doesn't exist.");
	}

	public void setReturnDate(String newRetDate){
		this.returnDate = newRetDate;
	}
	public String getReturnDate(){
		return this.returnDate;
	}
	public boolean returnExists(String retDt){
		for(Flight f : ts){
			if(retDt.equals(f.getDate())) return true;
		} 
		return false;
	}

	public String getFlights() {
		String rez = "";
		Iterator<Flight> it = ts.iterator();
		while (it.hasNext())
			rez += it.next() + "\n";
		return rez;
	}
	public String getSearchedFlights() {
		String rez = "";
		Iterator<Flight> it = tsSearched.iterator();
		while (it.hasNext())
			rez += it.next() + "\n";
		return rez;
	}
	public TreeSet<Flight> getSearchedFlightsTS() {		
		return tsSearched;
	}
	public String getInboundFlights() {
		String rez = "";
		Iterator<Flight> it = tsInbound.iterator();
		while (it.hasNext())
			rez += it.next() + "\n";
		return rez;
	}
	public String getReservations() {
		String rez = "";
		Iterator<Reservation> it = tsReserv.iterator();
		while (it.hasNext())
			rez += it.next() + "\n";
		return rez;
	}

	public float getRoundTripPrice() {				
		float tot = 0;
		tot += Float.valueOf(flightOut.getPrice()) + Float.valueOf(flightIn.getPrice()); 
		return tot;
	}

	public Flight findFlightByCode(String cod){
		for(Flight f : ts){
			if(cod.equals(f.getCod())) return f;
		} 
		throw new IllegalArgumentException("findFlightByCode is ~!~!~ null");
	}
	public void setOutboundFlight(Flight flOut){
		this.flightOut = flOut;
	}
	public Flight getOutboundFlight(){
		return this.flightOut;
	}
	public void setInboundFlight(Flight flIn){
		this.flightIn = flIn;
	}
	public Flight getInboundFlight(){
		return this.flightIn;
	}

	public boolean findReservation(String cod) {
		for (Reservation res : tsReserv)
		{
			if (cod.equals(res.getCodR()))
			{
				setReservationToEdit(res);
				return true;
			}
		}			
		return false;
	}
	public void setReservationToEdit(Reservation r){
		this.ReservationToEdit = r;
	}
	public Reservation getReservationToEdit(){
		return this.ReservationToEdit;
	}


	public void deleteReservation(Reservation canceling){
		tsReserv.remove(canceling);
	}
	public void setModifyingReservation(boolean trueOrFalse){
		this.ModifyingReservation = trueOrFalse;
	}
	public boolean isModifyingReservation(){
		return this.ModifyingReservation;
	}


	public void saveBookings(){
		try {
			pw = new PrintWriter(new FileWriter("reservations.txt"));
			for (Reservation r : tsReserv)
				pw.println(r);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public boolean findSearchedFlight(String searched) {
		tsSearched = new TreeSet<>();
		String iterator = "";
		String iterator2 = "";
		String[] ss;
		int i=0;
		ss=searched.split(" ");	//"from(ss[0]) to(ss[1]) dept(ss[2]) return(ss[3])" //System.out.println("entered findSearchedFlight:" +ss[0]+" "+ss[1]+" "+ss[2]+" "+ss[3]);  
		this.success=false;

		for (Flight fOut : ts)		//----outbound----
		{			
			iterator = fOut.getDepartCity() +" "+ fOut.getArrivalCity() +" "+ fOut.getDate();
			if ( (ss[0] +" "+ ss[1] +" "+ ss[2]).equals(iterator) )
			{
				for (Flight fi : ts)		//----outbound----
				{			
					iterator2 = fi.getArrivalCity() +" "+ fi.getDepartCity() +" "+ fi.getDate();
					if ( (ss[0] +" "+ ss[1] +" "+ ss[3]).equals(iterator2) )
					{
						System.out.println("tsSearched:\n"+iterator2+"----\n");
						this.success = true;
					}
				}
				tsSearched.add(fOut);
				// this.success = true;
				i++;
				
			}	
		}
		
		if(this.success == true) {
			System.out.println("tsSearched:\n"+getSearchedFlights()+"----\n");
			return true;
		}
		return false;
	}


	public boolean generateInboundFlights() {
		tsInbound = new TreeSet<>();
		String iterator;
		int i=0;
		for (Flight fIn : ts)		//----inbound----
		{			
			//System.out.println("retdate = "+returnDate+"..findate = "+fIn.getDate());
			iterator = fIn.getDate(); 
			if ( returnDate.equals(iterator) )
			{
				tsInbound.add(fIn);
				this.success = true;
				i++;
			}	
		}							
		if(this.success == true) {
			return true;
		}
		return false;
	}


	public boolean generateInboundSelection() {
		tsInbound = new TreeSet<>();
		int i=0;
		for (Flight fIn : ts)		//----inbound----
		{			
			if ( returnDate.equals(fIn.getDate()) &&  this.flightOut.getDepartCity().equals(fIn.getArrivalCity())
												&& this.flightOut.getArrivalCity().equals(fIn.getDepartCity()))
			{
				tsInbound.add(fIn);
				this.success = true;
				i++;
			}	
		}							
		if(this.success == true) {System.out.println("~true");
			return true; 
		}System.out.println("~false");
		return false;
	}


	public void setTravelClass(String x){
		this.travelClass = x;
	}

	public String getTravelClass() {
        return travelClass;
    }
	public int getSeat(Flight f)throws IOException  {
		if(Integer.parseInt(f.getNoSeats())>0){
			int last = Integer.parseInt(f.getNoSeats());
			
			String fileName = "flights.txt";
			String search = String.valueOf(last);
			int replace = last-1;
	
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line = "", oldtext = "";
			while((line = reader.readLine()) != null) {
				oldtext += line + "\r\n";
			}
			reader.close();
	
			String regex = "\\b" + search + "\\b";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(oldtext);
			String newtext = matcher.replaceAll(Integer.toString(replace));
	
			FileWriter writer = new FileWriter(fileName);
			writer.write(newtext);
			writer.close();
			return last;
		}
		
		System.out.println("error in getSeat(); seat not given?");
		return 0;
    }

	public Reservation getNewReserv(){
		return this.newReserv;
	}
	public void setNewReserv(Reservation r){
		this.newReserv = r;
	}
	public void addReserv(Reservation r){
		tsReserv.add(r);
	}
	public String generateRezervCod(){
		int cod = 1364;
		if(tsReserv.size()==0)
		{
			return String.valueOf(cod);
		} else {
			String x = tsReserv.last().getCodR();
			cod = Integer.parseInt(x)+1;
			return String.valueOf(cod);
		}		
	}
	public void salveaza() {
		try {
			pw = new PrintWriter(new FileWriter("fise.txt"));
			for (Flight f : ts)
				pw.println(f);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static FlightsAgenda getInstance() {
		if (instance == null)
		instance = new FlightsAgenda();
		return instance;
	}

}
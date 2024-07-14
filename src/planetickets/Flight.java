package planetickets;

public class Flight implements Comparable<Flight> {
    private String cod, date, departCity, depH, arrivalCity, arrH, price,noseats;


    public Flight(String cod, String date, String departCity, String depH, String arrivalCity, String arrH, String price,String nos) {
        this.cod = cod;
        this.date = date;
        this.departCity = departCity;
        this.depH = depH;
        this.arrivalCity = arrivalCity;
        this.arrH = arrH;
        this.price = price;
        this.noseats=nos;
        
    }
    public String getNoSeats(){
        return noseats;
    }
    public void modNoSeats(String mod){
        noseats=mod;
    }
    public String getCod() {
        return cod;
    }
    public String getDate() {
        return date;
    }
    public String getDepartCity() {
        return departCity;
    }
    public String getDepH() {
        return depH;
    }
    public String getArrivalCity() {
        return arrivalCity;
    }
    public String getArrH() {
        return arrH;
    }
    public String getPrice() {
        return price;
    }

    public String toString() {
        return cod+" "+date+" from: "+departCity+" to: "+arrivalCity+"; depart: "+depH+"; arrive: "+arrH+" || "+price+"\u20AC";
    }

    public int compareTo(Flight o) {
        return (cod).compareTo(o.getCod());
    }

}
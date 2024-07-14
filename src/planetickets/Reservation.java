package planetickets;

public class Reservation implements Comparable<Reservation> {

    private String codR, name, cardType, cardNo, cardExp, tClass;
    Flight flOut, flIn;
    int seatI,seatO;
    float price;
    
    public Reservation(String codR, String name, String cardType, String cardNo,  String cardExp, Flight flOut, Flight flIn, String tClass, int seatI,int seatO, float price) {
        this.codR = codR;
        this.name = name;
        this.cardType = cardType;
        this.cardNo = cardNo;
        this.cardExp = cardExp;
        this.flOut = flOut;
        this.flIn = flIn;
        this.tClass = tClass;
        this.seatI = seatI;
        this.seatO = seatO;
        this.price = price;
    }

    public String getCodR() {
        return codR;
    }
    public void setCodR(String codR) {
        this.codR = codR;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCardType() {
        return cardType;
    }
    public void getCardType(String cardType) {
        this.cardType = cardType;
    }
    public String getCardNo() {
        return cardNo;
    }
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    public String getCardExp() {
        return cardExp;
    }
    public void getCardExp(String cardExp) {
        this.cardExp = cardExp;
    }
    public String gettClass() {
        return tClass;
    }
    public Flight getFlOut() {
        return flOut;
    }
    public void setFlOut(Flight flOut) {
        this.flOut = flOut;
    }
    public Flight getFlIn() {
        return flIn;
    }
    public void setFlIn(Flight flIn) {
        this.flIn = flIn;
    }
    public void settClass(String tClass) {
        this.tClass = tClass;
    }
    public int getRSeatI() {
        return seatI;
    }
    public void setRSeatI(int seat) {
        this.seatI = seatI;
    }
    public int getRSeatO() {
        return seatO;
    }
    public void setRSeatO(int seat) {
        this.seatO = seatO;
    }
    public float getRPrice() {
        return this.price;
    }
    public void setRPrice(float p) {
        this.price = p;
    }

    @Override
    public String toString() {
        return "Reservation "+"code="+codR+", name="+name+", cardType="+cardType+", cardNo="+cardNo+", cardExp="+cardExp
                +",\n\nOutbound Flight:  "+flOut+",\n\nInbound Flight: "+flIn+",\n\nTravel class="+tClass+", inbound seat="+seatI+", outbound seat="+seatO+", final price="+price+"euro\n\n";
    }

    public int compareTo(Reservation o) {
        return (codR).compareTo(o.getCodR());
    }
}

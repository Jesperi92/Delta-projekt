package delta.projekt;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Booking{
    private int id;
    private String ship;
    private int slot;
    String date;
    private List<Truck> truckid;
    private List<Person> personid;
   
    public Booking(String ship, int slot,
			Time date){
        this.slot=slot;
        this.date=date.toString();
        this.ship=ship;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShip() {
        return ship;
    }

    public void setShip(String ship) {
        this.ship = ship;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Truck> getTruckid() {
        return truckid;
    }

    public void setTruckid(List<Truck> truckid) {
        this.truckid = truckid;
    }

    public List<Person> getPersonid() {
        return personid;
    }

    public void setPersonid(List<Person> personid) {
        this.personid = personid;
    }
    
   
    @Override
    public String toString(){
	return id + " | " + ship + " | " + slot + " | " +
	    date;
    }

    
}

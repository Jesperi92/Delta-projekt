package delta.projekt;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Booking{
    private int id;
    private int ship;
    private int slot;
    String date;
    private List<Integer> truckid;
    private List<Integer> personid;
   
    public Booking(int ship, int slot,
			String date){
        this.slot=slot;
        this.date=date;
        this.ship=ship;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShip() {
        return ship;
    }

    public void setShip(int ship) {
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

    public List<Integer> getTruckid() {
        return truckid;
    }

    public void setTruckid(List<Integer> truckid) {
        this.truckid = truckid;
    }

    public List<Integer> getPersonid() {
        return personid;
    }

    public void setPersonid(List<Integer> personid) {
        this.personid = personid;
    }
    
   
    @Override
    public String toString(){
	return id + " | " + ship + " | " + slot + " | " +
	    date;
    }

    
}

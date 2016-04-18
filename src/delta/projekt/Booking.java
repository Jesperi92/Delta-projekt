package delta.projekt;

import java.util.List;

public class Booking{
    private int id;
    private String ship;
    private int slot;
    private int date;
    private List<Truck> truckid;
    private List<Person> personid;
   
    public Booking(String ship, int slot,
			int date){
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

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
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

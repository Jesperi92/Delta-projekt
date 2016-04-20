package delta.projekt;
import java.util.List;

public interface InterfaceDB{
 
    public List<Person>getAllPersons();
    public List<Truck>getAllTrucks();
    public List<Ship>getAllShip();
    public void addTruck(Truck m);
    public void addShip(Ship m);
    public void addPerson(Person m);
    public void removePerson(Person person);
    public void removeTruck(Truck truck);
    public void removeShip(Ship ship);
    public void updatePerson(Person p);
    public void updateShip(Ship p);
    public void updateTruck(Truck p);
    public int getBookingCountFromDateAndSlot(String time, int slot);
    public List<Integer>getShipIDFromBooking(String date);
}

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
    public List<Person>getAllPersonsForBooking(String schema, String körkort, String date);
    public List<Truck>getAllTrucksForBooking(String type);
    public List<Ship>getAllShipBooking(String date);
    public void addBooking(Booking m);
    public List<Ship>getAllShipForStatistics(String date);
    public Booking getBooking(String time, int Ship);
    public Person getAllPersonsForStat(int id);
    public Truck getAllTrucksForStat(int id);
    public List<Booking> getBookingList(List<String> list);
    public Ship getAllShipForStatisticsWeekList(int id);
}

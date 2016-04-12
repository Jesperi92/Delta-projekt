package delta.projekt;
import java.util.List;

public interface InterfaceDB{
 
    public List<Person>getAllPersons();
    public List<Truck>getAllTrucks();
    public List<Ship>getAllShip();
    public void addTruck(Truck m);
    public void removePerson(Person person);
    public void removeTruck(Truck truck);
    public void removeShip(Ship ship);
}

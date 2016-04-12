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
    public void updatePerson(Person p, String column, String uppdate);
    public void updateShip(Ship p, String column, String uppdate);
    public void updateTruck(Truck p, String column, String uppdate);
}

package delta.projekt;
import java.util.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An implementation of MunicipalityDB that uses the DBUtils to connect to an SQLite database.
 */
public class Database implements InterfaceDB{
    DBUtils db = DBUtils.getInstance();
    
    public List<Person>getAllPersons(){
	ArrayList<Person> list = new ArrayList<Person>();
	ResultSet rs = db.executeQuery("Select p.PersonnelID, p.FirstName, p.LastName, l.Licence, w.Status, s.Schedule, wa.Wage FROM Personnel p, Licences l, WorkStatuses w, WorkSchedules s, Wages wa WHERE p.Licence=l.LicenceID AND p.WorkStatus=w.WorkStatusID AND p.Schedule=s.ScheduleID AND p.Wage=wa.WageID");
	try{
	    Person m=null;
	    while(rs.next()){
		m=new Person(rs.getString("FirstName"),
				   rs.getString("LastName"),
				   rs.getString("Licence"),
                                   rs.getString("Status"),
				   rs.getString("Schedule"));
                m.setID(rs.getInt("PersonnelID"));
                m.setWage(rs.getString("Wage"));
		
		list.add(m);
	    }
	    db.closeIt(rs);
	    return list;
            
	}catch(Exception e){
	    System.err.println("Getting all municipalities: " + e.getMessage());
	    db.closeIt(rs);
	}
	return null;
    }
    public List<Truck>getAllTrucks(){
	ArrayList<Truck> list = new ArrayList<Truck>();
	ResultSet rs = db.executeQuery("SELECT * FROM Truck");
	try{
	    Truck m=null;
	    while(rs.next()){
		m=new Truck(rs.getString("Trucktyp"),
				   rs.getString("Status"));
				  
		m.setID(rs.getInt("TruckID"));
		list.add(m);
	    }
	    db.closeIt(rs);
	    return list;
            
	}catch(Exception e){
	    System.err.println("Getting all municipalities: " + e.getMessage());
	    db.closeIt(rs);
	}
	return null;
    }
    public List<Ship>getAllShip(){
	ArrayList<Ship> list = new ArrayList<Ship>();
	ResultSet rs = db.executeQuery("SELECT * FROM Ship");
	try{
	    Ship m=null;
	    while(rs.next()){
		m=new Ship(rs.getString("Name"),
				   rs.getString("Bolag"),
				   rs.getString("Volymtyp"));
                                   
		m.setID(rs.getInt("ShipID"));
		list.add(m);
	    }
	    db.closeIt(rs);
	    return list;
            
	}catch(Exception e){
	    System.err.println("Getting all municipalities: " + e.getMessage());
	    db.closeIt(rs);
	}
	return null;
    }
    
    public void updatePerson(Person p, String column, String uppdate){
	int id = p.getID();
	String SQL="UPDATE Personnel SET FirstName='"+p.förnamn()+"',LastName='"+p.efternamn()+"',Licence='"+p.körkort()+"',WorkStatus='"+p.status()+"',Schedule='"+p.schema()+"',Wage='"+p.körkort()+"' WHERE PersonnelID='"+p.getID()+"'";
	System.out.println(db.executeUpdate(SQL) 
			   + " rows updated");
    
    }
    public void updateTruck(Truck p, String column, String uppdate){
	int id = p.getID();
	String SQL="UPDATE Truck SET "+column+"="+uppdate+
                "WHERE TruckID="+id;
	System.out.println(db.executeUpdate(SQL) 
			   + " rows updated");
    
    }
    public void updateShip(Ship p, String column, String uppdate){
	int id = p.getID();
	String SQL="UPDATE Ship SET "+column+"="+uppdate+
                "WHERE ShipID="+id;
	System.out.println(db.executeUpdate(SQL) 
			   + " rows updated");
    
    }
    
    public void removePerson(Person person){
	int id = person.getID();
        
	String SQL="DELETE FROM Personnel"+
	    " WHERE PersonnelID="+id;
	System.out.println(db.executeUpdate(SQL) +
			   " rows deleted");
	
    }
    public void removeTruck(Truck truck){
	int id = truck.getID();
        
	String SQL="DELETE FROM Truck"+
	    " WHERE TruckID="+id;
	System.out.println(db.executeUpdate(SQL) +
			   " rows deleted");
	
    }
    public void removeShip(Ship ship){
	int id = ship.getID();
        
	String SQL="DELETE FROM Ship"+
	    " WHERE ShipID="+id;
	System.out.println(db.executeUpdate(SQL) +
			   " rows deleted");
	
    }
    
    public void addTruck(Truck m){
	int id=m.getID();
	
	String SQL="INSERT INTO Truck"+
	    "(Trucktyp, Status)" +
	    " VALUES('"+m.gettrucktyp()+"', "+
	    "'"+m.gettruckstatus()+"')";
	System.out.println(db.executeUpdate(SQL)+
			   " rows inserted");
    }
     public void addPerson(Person m){
	int id=m.getID();
        ResultSet licences = db.executeQuery("SELECT LicenceID FROM Licences WHERE Licence='"+m.körkort()+"'");
        
        ResultSet workstatus = db.executeQuery("SELECT WorkStatusID FROM WorkStatuses WHERE Status='"+m.status()+"'");
        
        ResultSet schedules = db.executeQuery("SELECT ScheduleID FROM WorkSchedules WHERE Schedule='"+m.schema()+"'");
        
	String SQL = null;
        try {
            SQL = "INSERT INTO Personnel"+
                    "(FirstName, LastName, Licence, WorkStatus, Schedule,Wage)" +
                    " VALUES('"+m.förnamn()+"', '"+m.efternamn()+"','"+licences.getInt("LicenceID")+"','"+workstatus.getInt("WorkStatusID")+"','"+schedules.getInt("ScheduleID")+"','"+licences.getInt("LicenceID")+"')";
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
	System.out.println(db.executeUpdate(SQL)+
			   " rows inserted");
    }
      public void addShip(Ship m){
	int id=m.getID();
	
	
	String SQL="INSERT INTO Ship"+
	    "(Namn, Bolag, VolymID)" +
	    " VALUES('"+m.namn()+"', "+m.bolag()+"', "+
	    "'"+m.volymid()+"')";
	System.out.println(db.executeUpdate(SQL)+
			   " rows inserted");
    }
  

}

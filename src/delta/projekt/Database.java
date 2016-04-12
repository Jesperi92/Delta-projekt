package delta.projekt;
import java.util.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 * An implementation of MunicipalityDB that uses the DBUtils to connect to an SQLite database.
 */
public class Database implements InterfaceDB{
    DBUtils db = DBUtils.getInstance();
    
    public List<Person>getAllPersons(){
	ArrayList<Person> list = new ArrayList<Person>();
	ResultSet rs = db.executeQuery("SELECT * FROM Person");
	try{
	    Person m=null;
	    while(rs.next()){
		m=new Person(rs.getString("FirstName"),
				   rs.getString("LastName"),
				   rs.getString("Licence"),
                                   rs.getString("WorkStatus"),
				   rs.getString("Schedual"));
		m.setID(rs.getInt("PersonnelID"));
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
	String SQL="UPDATE Person SET "+column+"="+uppdate+
                "WHERE PersonID="+id;
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
        
	String SQL="DELETE FROM Person"+
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
	
	String SQL="INSERT INTO Person"+
	    "(Förnamn, Efternamn, Körkort, Status, Schema)" +
	    " VALUES('"+m.förnamn()+"', '"+m.efternamn()+"', '"+m.körkort()+"', '"+m.status()+"', '"+m.schema()+"')";
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
    /*
    public Municipality getByName(String name){
	String SQL="SELECT * FROM municipalities WHERE name='"+name+"'";

	System.out.println("DEBUG: SQL: " + SQL);
	ResultSet rs = db.executeQuery(SQL);
	Municipality m = null;
	try{
	    if(rs.next()){
		m = new Municipality(rs.getString("Name"), 
				     rs.getString("URL"),
				     rs.getString("Server"),
				     rs.getBoolean("HTTPS"));
		m.setID(rs.getInt("MunicipalityID"));
	    }
	    return m;
	}catch(Exception e){
	    System.err.println("getByName: " + e.getMessage());
	}finally{
	    db.closeIt(rs);
	}
	return null;
    }

    public int updateHTTPSbyName(String name, boolean https){
        String SQL="UPDATE municipalities SET HTTPS="+(https?"1":"0")+" WHERE name='"+name+"'";
        System.out.println("DEBUG: SQL: " + SQL);
        int rows = db.executeUpdate(SQL);
        return rows;
    }
    
    /* 
    // What's this? See if you can find information about it!
    public int updateHTTPSbyName2(String name, boolean https){
	String sql = "UPDATE municipalities SET HTTPS=? WHERE name= ?";
	int result=0;
	try{
	    PreparedStatement pStm = db.preparedStatement(sql);
	    pStm.setInt(1, (https?1:0));
	    pStm.setString(2,name);
	    result=pStm.executeUpdate();
	    return result;
	}catch(Exception e){
	    System.err.println("Error creating prepared stm: "+e.getMessage());
	    return -1;
	}
    }
    */

}

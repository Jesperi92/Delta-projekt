package delta.projekt;
import java.util.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Database implements InterfaceDB{
    DBUtils db = DBUtils.getInstance();
    
    @Override
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
	    System.err.println("Getting all persons: " + e.getMessage());
	    db.closeIt(rs);
	}
	return null;
    }
    @Override
    public List<Truck>getAllTrucks(){
	ArrayList<Truck> list = new ArrayList<Truck>();
	ResultSet rs = db.executeQuery("SELECT t.TruckID, tt.Type, ts.Status, tf.Fee From Trucks t, TruckTypes tt, TruckStatuses ts, TruckFees tf WHERE t.Type=tt.TypeID AND t.Status=ts.StstusID AND t.Fee=tf.FeeID");
	try{
	    Truck m=null;
	    while(rs.next()){
		m=new Truck(rs.getString("Type"),
				   rs.getString("Status"));
				  
		m.setID(rs.getInt("TruckID"));
                m.setFee(rs.getInt("Fee"));
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
    @Override
    public List<Ship>getAllShip(){
	ArrayList<Ship> list = new ArrayList<Ship>();
	ResultSet rs = db.executeQuery("SELECT b.BoatID, b.Name, s.Name AS Owner, v.Volume FROM Boats b, Shippers s, Volumes v WHERE b.Owner=s.ShipperID AND b.Volume=v.VolumeID");
	try{
	    Ship m=null;
	    while(rs.next()){
		m=new Ship(rs.getString("Name"),
				   rs.getString("Owner"),
				   rs.getString("Volume"));
                                   
		m.setID(rs.getInt("BoatID"));
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
    
    @Override
    public void updatePerson(Person p){
        
        ResultSet licences = db.executeQuery("SELECT LicenceID FROM Licences WHERE Licence='"+p.körkort()+"'");
        
        ResultSet workstatus = db.executeQuery("SELECT WorkStatusID FROM WorkStatuses WHERE Status='"+p.status()+"'");
        
        ResultSet schedules = db.executeQuery("SELECT ScheduleID FROM WorkSchedules WHERE Schedule='"+p.schema()+"'");
        
        
	String SQL=null;
        try {
            SQL = "UPDATE Personnel SET FirstName='"+p.förnamn()+"',LastName='"+p.efternamn()+"',Licence='"+licences.getInt("LicenceID")+"',WorkStatus='"+workstatus.getInt("WorkStatusID")+"',Schedule='"+schedules.getInt("ScheduleID")+"',Wage='"+licences.getInt("LicenceID")+"' WHERE PersonnelID='"+p.getID()+"'";
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
	System.out.println(db.executeUpdate(SQL) 
			   + " rows updated");
        db.closeIt(licences);
        db.closeIt(workstatus);
        db.closeIt(schedules);
    
    }
    @Override
    public void updateTruck(Truck p){
	int id = p.getID();
        
        ResultSet trucktypes = db.executeQuery("SELECT TypeID FROM TruckTypes WHERE Type='"+p.gettrucktyp()+"'");
        
        ResultSet truckstatus = db.executeQuery("SELECT StstusID FROM TruckStatuses WHERE Status='"+p.gettruckstatus()+"'");
        
        
        
	String SQL=null;
        try {
            SQL = "UPDATE Trucks SET Type='"+trucktypes.getInt("TypeID")+"',Status='"+truckstatus.getInt("StstusID")+"',Fee='"+trucktypes.getInt("TypeID")+"' WHERE TruckID='"+p.getID()+"'";
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
	System.out.println(db.executeUpdate(SQL) 
			   + " rows updated");
        db.closeIt(trucktypes);
        db.closeIt(truckstatus);
    
    }
    @Override
    public void updateShip(Ship p){
	int id = p.getID();
        ResultSet owner = db.executeQuery("SELECT ShipperID FROM Shippers WHERE Name='"+p.bolag()+"'");
        ResultSet volumeid = db.executeQuery("SELECT VolumeID FROM Volumes WHERE Volume='"+p.volymid()+"'");
        
        
        
	String SQL = null;
        
        
        try {
            SQL = "UPDATE Boats SET Name='"+p.namn()+"',Owner='"+owner.getInt("ShipperID")+"',Volume='"+volumeid.getInt("VolumeID")+"' WHERE BoatID='"+p.getID()+"'";
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
	System.out.println(db.executeUpdate(SQL) 
			   + " rows updated");
        
        db.closeIt(owner);
        
    
    }
    
    @Override
    public void removePerson(Person person){
	int id = person.getID();
        
	String SQL="DELETE FROM Personnel"+
	    " WHERE PersonnelID="+id;
	System.out.println(db.executeUpdate(SQL) +
			   " rows deleted");
	
    }
    @Override
    public void removeTruck(Truck truck){
	int id = truck.getID();
        
	String SQL="DELETE FROM Trucks"+
	    " WHERE TruckID="+id;
	System.out.println(db.executeUpdate(SQL) +
			   " rows deleted");
	
    }
    @Override
    public void removeShip(Ship ship){
	int id = ship.getID();
        
	String SQL="DELETE FROM Ship"+
	    " WHERE ShipID="+id;
	System.out.println(db.executeUpdate(SQL) +
			   " rows deleted");
	
    }
    
    @Override
    public void addTruck(Truck m){
	int id=m.getID();
	 ResultSet trucktypes = db.executeQuery("SELECT TypeID FROM TruckTypes WHERE Type='"+m.gettrucktyp()+"'");
        
         ResultSet truckstatus = db.executeQuery("SELECT StstusID FROM TruckStatuses WHERE Status='"+m.gettruckstatus()+"'");
        
	String SQL=null;
        try {
            SQL = "INSERT INTO Trucks"+
                    "(Type, Status, Fee)" +
                    " VALUES('"+trucktypes.getInt("TypeID")+"', "+"'"+truckstatus.getInt("StstusID")+"','"+trucktypes.getInt("TypeID")+"')";
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
	System.out.println(db.executeUpdate(SQL)+
			   " rows inserted");
    }
    @Override
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
    @Override
      public void addShip(Ship m){
	int id=m.getID();
	
       
	String SQL = null;
        try {
            ResultSet owner = db.executeQuery("SELECT ShipperID FROM Shippers WHERE Name='"+m.bolag()+"'");
        
            ResultSet volumeid = db.executeQuery("SELECT VolumeID FROM Volumes WHERE Volume='"+m.volymid()+"'");
	
            SQL = "INSERT INTO Boats"+
                    "(Name, Owner, Volume)" +
                    " VALUES('"+m.namn()+"', '"+owner.getInt("ShipperID")+"', '"+volumeid.getInt("VolumeID")+"')";
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
	System.out.println(db.executeUpdate(SQL)+
			   " rows inserted");
    }
  

}

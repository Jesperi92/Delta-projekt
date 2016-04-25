package delta.projekt;
import java.util.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
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
	    System.err.println("Getting all trucks: " + e.getMessage());
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
	    System.err.println("Getting all ships: " + e.getMessage());
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
            db.closeIt(licences);
            db.closeIt(workstatus);
            db.closeIt(schedules);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            db.closeIt(licences);
            db.closeIt(workstatus);
            db.closeIt(schedules);
        }
	System.out.println(db.executeUpdate(SQL) 
			   + " rows updated");

    
    }
    @Override
    public void updateTruck(Truck p){
	int id = p.getID();
        
        ResultSet trucktypes = db.executeQuery("SELECT TypeID FROM TruckTypes WHERE Type='"+p.gettrucktyp()+"'");
        
        ResultSet truckstatus = db.executeQuery("SELECT StstusID FROM TruckStatuses WHERE Status='"+p.gettruckstatus()+"'");
        
        
        
	String SQL=null;
        try {
            SQL = "UPDATE Trucks SET Type='"+trucktypes.getInt("TypeID")+"',Status='"+truckstatus.getInt("StstusID")+"',Fee='"+trucktypes.getInt("TypeID")+"' WHERE TruckID='"+p.getID()+"'";
            db.closeIt(trucktypes);
            db.closeIt(truckstatus);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            db.closeIt(trucktypes);
            db.closeIt(truckstatus);
        }
	System.out.println(db.executeUpdate(SQL) 
			   + " rows updated");
        
    
    }
    @Override
    public void updateShip(Ship p){
	int id = p.getID();
        ResultSet owner = db.executeQuery("SELECT ShipperID FROM Shippers WHERE Name='"+p.bolag()+"'");
        ResultSet volumeid = db.executeQuery("SELECT VolumeID FROM Volumes WHERE Volume='"+p.volymid()+"'");
        
        
        
	String SQL = null;
        
        
        try {
            SQL = "UPDATE Boats SET Name='"+p.namn()+"',Owner='"+owner.getInt("ShipperID")+"',Volume='"+volumeid.getInt("VolumeID")+"' WHERE BoatID='"+p.getID()+"'";
            db.closeIt(owner);
            db.closeIt(volumeid);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            db.closeIt(owner);
            db.closeIt(volumeid);
        }
        
            
	System.out.println(db.executeUpdate(SQL) 
			   + " rows updated");
        
        
        
    
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
        
	String SQL="DELETE FROM Boats"+
	    " WHERE BoatID="+id;
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
            db.closeIt(trucktypes);
            db.closeIt(truckstatus);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            db.closeIt(trucktypes);
            db.closeIt(truckstatus);
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
            db.closeIt(licences);
            db.closeIt(workstatus);
            db.closeIt(schedules);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            db.closeIt(licences);
            db.closeIt(workstatus);
            db.closeIt(schedules);
        }
	System.out.println(db.executeUpdate(SQL)+
			   " rows inserted");
    }
    @Override
      public void addShip(Ship m){
	int id=m.getID();
	
       
	String SQL = null;
        ResultSet owner = null;
        ResultSet volumeid = null;
        try {
            owner = db.executeQuery("SELECT ShipperID FROM Shippers WHERE Name='"+m.bolag()+"'");
        
            volumeid = db.executeQuery("SELECT VolumeID FROM Volumes WHERE Volume='"+m.volymid()+"'");
	
            SQL = "INSERT INTO Boats"+
                    "(Name, Owner, Volume)" +
                    " VALUES('"+m.namn()+"', '"+owner.getInt("ShipperID")+"', '"+volumeid.getInt("VolumeID")+"')";
            db.closeIt(owner);
            db.closeIt(volumeid);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            db.closeIt(owner);
            db.closeIt(volumeid);
        }
	System.out.println(db.executeUpdate(SQL)+
			   " rows inserted");
    }
      
      public List<Booking> getBooking(String time, int Ship){
          ArrayList<Booking> list = new ArrayList<Booking>();
          ArrayList<Integer> personnelid = new ArrayList<Integer>();
          ArrayList<Integer> truckid = new ArrayList<Integer>();
	ResultSet rs = db.executeQuery("Select * FROM Booking WHERE Fartyg='"+Ship+"', Dag='"+time+"'"); 
	ResultSet personnel =null;
        ResultSet trucks = null;
        try{
	    Booking m=null;
            personnel = null;
            trucks = null;
	    while(rs.next()){
		m=new Booking(rs.getInt("Fartyg"),
				   rs.getInt("Slot"),
				   rs.getTime("Dag").toString());
                m.setId(rs.getInt("LastID"));
                
                
		
		list.add(m);
                personnel = db.executeQuery("Select * FROM BookedPersonnel WHERE LastID = '"+m.getId()+"'");
                while(personnel.next()){
                    personnelid.add(personnel.getInt("PersonnelID"));
                }
                trucks = db.executeQuery("Select * FROM BookedTrucks WHERE LastID = '"+m.getId()+"'");
                while(trucks.next()){
                    truckid.add(trucks.getInt("TruckID"));
                }
	    }
	    db.closeIt(rs);
            db.closeIt(personnel);
            db.closeIt(trucks);
	    return list;
            
	}catch(Exception e){
	    System.err.println("Getting all persons: " + e.getMessage());
	    db.closeIt(rs);
	}
	return null;
      }
      public int getBookingCountFromDateAndSlot(String time, int slot){
          int count = -1;
	ResultSet rs = db.executeQuery("Select count(LastID) FROM Booking WHERE Slot='"+slot+"' AND Dag='"+time+"'"); 
	
	    
        try {
            count = rs.getInt("count(LastID)");
            db.closeIt(rs);
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            db.closeIt(rs);
        }
	    
	    return count;
	
      }
   
    
    @Override
    public List<Ship>getAllShipBooking(String date){
        
        
        
	ArrayList<Ship> list = new ArrayList<Ship>();
	ResultSet rs = db.executeQuery("SELECT b.BoatID, b.Name, s.Name AS Owner, v.Volume FROM Boats b, Shippers s, Volumes v WHERE b.Owner=s.ShipperID AND b.Volume=v.VolumeID AND b.BoatID not in(Select Fartyg From Booking Where Dag='"+date+"')");
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
    public List<Person>getAllPersonsForBooking(String schema, String körkort, String date){
	ArrayList<Person> list = new ArrayList<Person>();
        
        
        ResultSet licences = db.executeQuery("SELECT LicenceID FROM Licences WHERE Licence='"+körkort+"'");
       
        ResultSet schedules = db.executeQuery("SELECT ScheduleID FROM WorkSchedules WHERE Schedule='"+schema+"'");
        
        ResultSet rs = null;
	
	try{
            rs = db.executeQuery("Select p.PersonnelID, p.FirstName, p.LastName, l.Licence, w.Status, s.Schedule, wa.Wage FROM Personnel p, Licences l, WorkStatuses w, WorkSchedules s, Wages wa WHERE p.Licence=l.LicenceID AND p.WorkStatus=w.WorkStatusID AND p.Schedule=s.ScheduleID AND p.Wage=wa.WageID AND p.WorkStatus in(1,2) AND p.Schedule ='"+schedules.getInt("ScheduleID")+"' AND p.Licence ='"+licences.getInt("LicenceID")+"' AND p.PersonnelID not in (Select PersonnelID From BookedPersonnel Where Dag = '"+date+"')");
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
            db.closeIt(licences);
            db.closeIt(schedules);
	    db.closeIt(rs);
	    return list;
            
	}catch(Exception e){
	    System.err.println("Getting all persons: " + e.getMessage());
            db.closeIt(licences);
            db.closeIt(schedules);
	    db.closeIt(rs);
	}
	return null;
    }
    @Override
    public List<Truck>getAllTrucksForBooking(String type){
	ArrayList<Truck> list = new ArrayList<Truck>();
        ResultSet trucktypes = db.executeQuery("SELECT TypeID FROM TruckTypes WHERE Type='"+type+"'");
        
	ResultSet rs=null;
	try{
            rs = db.executeQuery("SELECT t.TruckID, tt.Type, ts.Status, tf.Fee From Trucks t, TruckTypes tt, TruckStatuses ts, TruckFees tf WHERE t.Type=tt.TypeID AND t.Status=ts.StstusID AND t.Fee=tf.FeeID AND t.Type='"+trucktypes.getInt("TypeID")+"' AND t.Status IN(1)");
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
            db.closeIt(trucktypes);
	}
	return null;
    }
    public int getBookingRightAmountOfRecourses(String volym){
          int count = -1;
	ResultSet rs = db.executeQuery("Select Count FROM Volumes WHERE Volume='"+volym+"'"); 
	
	    
        try {
            count = rs.getInt("Count");
            db.closeIt(rs);
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            db.closeIt(rs);
        }
	    
	    return count;
	
      }
      @Override
      public void addBooking(Booking b){
	
	int lastid = 0;
       
	String SQL = null;
        String SQL2 = null;
        String SQL3 = null;
        ResultSet owner = null;
        ResultSet volumeid = null;
        SQL = "INSERT INTO Booking"+
                "(Fartyg, Dag, Slot)" +
                " VALUES('"+b.getShip()+"', '"+b.getDate()+"', '"+b.getSlot()+"')";
        
	System.out.println(db.executeUpdate(SQL)+
			   " rows inserted");
        ResultSet bookid = db.executeQuery("Select LastID From Booking Where Dag='"+b.getDate()+"' AND Slot='"+b.getSlot()+"'");
        try {
            lastid = bookid.getInt("LastID");
            db.closeIt(bookid);
        } catch (SQLException ex) {
            db.closeIt(bookid);
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i : b.getPersonid()){
        SQL2 = "INSERT INTO BookedPersonnel VALUES('"+lastid+"','"+i+"','"+b.getDate()+"')";
        db.executeUpdate(SQL2);
        }
        for(int i : b.getTruckid()){
        SQL3 = "INSERT INTO BookedTrucks VALUES('"+lastid+"','"+i+"','"+b.getDate()+"')";
        db.executeUpdate(SQL3);
        }
        
    }
   
}

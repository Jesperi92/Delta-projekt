package delta.projekt;

public class Truck{
    private int id;
    private String trucktyp;
    private String status;
    
    
   
    public Truck(String trucktyp, String status){
	this.trucktyp=trucktyp;
	this.status=status;

    }

    public String gettrucktyp(){return this.trucktyp;}

    public String gettruckstatus(){return this.status;}
    
    public int getID (){ return this.id;}

    @Override
    public String toString(){
	return trucktyp + " | " + status;
    }
    public void settrucktyp(String trucktyp){
	this.trucktyp=trucktyp;
    }
 
    public void setstatus(String status){
	this.status=status;
    }
    public void setID(int id){
	this.id=id;
    }
}

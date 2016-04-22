package delta.projekt;

public class Truck{
    private int id;
    private String trucktyp;
    private String status;
    private int fee;
    
   
    public Truck(String trucktyp, String status){
	this.trucktyp=trucktyp;
	this.status=status;

    }
    public int gettruckfee(){return this.fee;}
    public String gettrucktyp(){return this.trucktyp;}

    public String gettruckstatus(){return this.status;}
    
    public int getID (){ return this.id;}

    @Override
    public String toString(){
        return String.format("%02d", id);
	
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
    public void setFee(int fee){
        this.fee=fee;
    }
}

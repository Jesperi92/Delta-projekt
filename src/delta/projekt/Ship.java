package delta.projekt;
/**
 * A class that represents a municipality.
 */
public class Ship{
    private int id;
    private String namn;
    private String bolag;
    private String volymid;
   
    public Ship(String name, String bolag,
			String volymid){
	this.namn=name;
        this.bolag=bolag;
        this.volymid=volymid;
    }

    public int getID(){return this.id;}

    public String namn(){return this.namn;}

    public String bolag(){return this.bolag; }
    
    public String volymid(){return this.volymid; }
    
    @Override
    public String toString(){
	return namn + " | " + bolag + " | " +
	    volymid;
    }

    public void setnamn(String namn){
	this.namn=namn;
    }
  
    public void setID(int id){
	this.id=id;
    }
    public void setbolag(String bolag){
	this.bolag=bolag;
    }
    public void setID(String voymid){
	this.volymid = volymid;
    }
}

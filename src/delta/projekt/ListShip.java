package delta.projekt;

import javafx.beans.property.SimpleStringProperty;

/**
 * A class that represents a municipality.
 */
public class ListShip{
    private SimpleStringProperty shipid;
    private SimpleStringProperty namn;
    private SimpleStringProperty bolag;
    private SimpleStringProperty volymid;
   
    public ListShip(Ship s){
	this.namn= new SimpleStringProperty(s.namn());
        this.bolag=new SimpleStringProperty(s.bolag());
        this.volymid=new SimpleStringProperty(s.volymid());
        this.shipid = new SimpleStringProperty(Integer.toString(s.getID()));
    }

    public String getID(){return this.shipid.get();}

    public String getNamn(){return this.namn.get();}

    public String getBolag(){return this.bolag.get(); }
    
    public String getVolymid(){return this.volymid.get(); }
    
    

    public void setNamn(String namn){
	this.namn.set(namn);
    }
  
    public void setId(String id){
	this.shipid.set(id);
    }
    public void setBolag(String bolag){
	this.bolag.set(bolag);
    }
    public void setVolumeId(String volymid){
	this.volymid.set(volymid);
    }
}

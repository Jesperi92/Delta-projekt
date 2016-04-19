package delta.projekt;

import javafx.beans.property.SimpleStringProperty;

public class ListTruck{
    private SimpleStringProperty id;
    private SimpleStringProperty trucktyp;
    private SimpleStringProperty status;
    private SimpleStringProperty fee;
    
   
    public ListTruck(Truck t){
	this.trucktyp=new SimpleStringProperty(t.gettrucktyp());
	this.status=new SimpleStringProperty(t.gettruckstatus());
        this.id = new SimpleStringProperty(Integer.toString(t.getID()));
        this.fee = new SimpleStringProperty(Integer.toString(t.gettruckfee()));

    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getTrucktyp() {
        return trucktyp.get();
    }

    public void setTrucktyp(String trucktyp) {
        this.trucktyp.set(trucktyp);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getFee() {
        return fee.get();
    }

    public void setFee(String fee) {
        this.fee.set(fee);
    }
    
}

package delta.projekt;

import javafx.beans.property.SimpleStringProperty;

public class ListPerson{
    private SimpleStringProperty id;
    private SimpleStringProperty förnamn;
    private SimpleStringProperty efternamn;
    private SimpleStringProperty körkort;
    private SimpleStringProperty status;
    private SimpleStringProperty schema;
    private SimpleStringProperty wage;
    
     
    public ListPerson(Person p){
                this.efternamn = new SimpleStringProperty(p.förnamn());
                this.förnamn=new SimpleStringProperty(p.efternamn());
                this.körkort=new SimpleStringProperty(p.körkort());
                this.status=new SimpleStringProperty(p.status());
                this.schema=new SimpleStringProperty(p.schema());
                this.id=new SimpleStringProperty(Integer.toString(p.getID()));
                this.wage = new SimpleStringProperty(p.getwage());
                
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getFörnamn() {
        return förnamn.get();
    }

    public void setFörnamn(String förnamn) {
        this.förnamn.set(förnamn);
    }

    public String getEfternamn() {
        return efternamn.get();
    }

    public void setEfternamn(String efternamn) {
        this.efternamn.set(efternamn);
    }

    public String getKörkort() {
        return körkort.get();
    }

    public void setKörkort(String körkort) {
        this.körkort.set(körkort);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getSchema() {
        return schema.get();
    }

    public void setSchema(String schema) {
        this.schema.set(schema);
    }

    public String getWage() {
        return wage.get();
    }

    public void setWage(String wage) {
        this.wage.set(wage);
    }

  
    
    
}

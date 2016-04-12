package delta.projekt;

public class Person{
    private int id;
    private String förnamn;
    private String efternamn;
    private String körkort;
    private String status;
    private String schema;
  
    public Person(String förname, String efternamn,
			String körkort, String status, String schema){
                this.efternamn = efternamn;
                this.förnamn=förnamn;
                this.körkort=körkort;
                this.status=status;
                this.schema=schema;
                
    }

  
    public String förnamn(){return this.förnamn;}

    
    public String efternamn(){return this.efternamn;}

  
    public String körkort(){return this.körkort; }

   
    public String status(){return this.status;}


    public String schema(){return this.schema;}
    
    public int getID() { return this.id;}
    
   
    @Override
    public String toString(){
	return förnamn + " | " + efternamn + " | " +
	    körkort + " | " + 
	    status+" | "+schema;
    }

    public void setförnamn(String förnamn){
	this.förnamn = förnamn;
    }
   
    public void setefternamn(String efternamn){
	this.efternamn=efternamn;
    }
    public void setschema(String schema){
	this.schema=schema;
    }
    public void setkörkort(String körkort){
	this.körkort=körkort;
    }
    public void setstatus(String status){
	this.status=status;
    }
    public void setID(int id){
	this.id=id;
    }
    
}

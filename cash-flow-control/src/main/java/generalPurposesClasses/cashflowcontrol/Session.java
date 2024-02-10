package generalPurposesClasses.cashflowcontrol;

public class Session {
    private int id;
    private String name;

    private String description;
    public Session(int id , String name){
        this.id = id;
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setDescription(String description){
        this.description = description;
    }
}

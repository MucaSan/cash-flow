package generalPurposesClasses.cashflowcontrol;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private Integer id;
    private String name;
    private List<ImageView> actions;
    public Session(int id , String name, List<ImageView> actions){
       this.id = id;
       this.name = name;
       this.actions = new ArrayList<>(actions);
    }
    public String getName(){
        return this.name;
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
}

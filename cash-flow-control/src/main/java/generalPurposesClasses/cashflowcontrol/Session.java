package generalPurposesClasses.cashflowcontrol;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private Integer id;
    private String name;
    public ImageView actions;
    public Session(int id , String name, ImageView actions){
       this.id = id;
       this.name = name;
       this.actions = actions;
    }
    public String getName() {
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
    public void setActions(ImageView imageView){
        this.actions = imageView;
    }
    public ImageView getActions(){
        return this.actions;
    }
}

package generalPurposesClasses.cashflowcontrol;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;
public class Payment{
    private Integer id;
    private String name;
    HBox actions;
    public Payment(int id , String name, HBox actions){
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
    public void setActions(HBox actions){
        this.actions = actions;
    }
    public HBox getActions(){
        return this.actions;
    }
}

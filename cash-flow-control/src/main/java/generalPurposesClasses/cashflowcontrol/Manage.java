package generalPurposesClasses.cashflowcontrol;

import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Manage {
    private int id;
    private String name;
    private String session;
    private String payment;
    private double price;
    private LocalDate dateTime;
     HBox actions;
    public Manage(int id, String name , String session, String payment, double price, LocalDate dateTime
    , HBox actions){
        this.id = id;
        this.name = name;
        this.session = session;
        this.payment = payment;
        this.price = price;
        this.dateTime = dateTime;
        this.actions = actions;
    }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getSession(){
        return session;
    }


    public String getPayment(){
        return this.payment;
    } public double getPrice(){
        return this.price;
    } public LocalDate getDateTime(){
        return this.dateTime;
    } public HBox getActions(){
        return this.actions;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSession(String session){
        this.session = session;
    }
    public void setPayment(String payment){
        this.payment = payment;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setDateTime(LocalDate dateTime){
        this.dateTime = dateTime;
    }
    public void setActions(HBox actions){
        this.actions = actions;
    }








}

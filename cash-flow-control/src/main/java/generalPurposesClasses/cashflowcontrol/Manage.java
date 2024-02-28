package generalPurposesClasses.cashflowcontrol;

import javafx.scene.layout.HBox;

import java.time.LocalDateTime;

public class Manage {
    private int id;
    private String name;
    private String session;
    private String payment;
    private double price;
    private LocalDateTime dateTime;
    private HBox actions;

    public Manage(int id, String name , String session, String payment, double price, LocalDateTime dateTime
    , HBox actions){
        this.id = id;
        this.name = name;
        this.session = session;
        this.payment = payment;
        this.price = price;
        this.dateTime = dateTime;
        this.actions = actions;
    }
    private int getId(){
        return this.id;
    }
    private String getName(){
        return this.name;
    }
    private String getSession(){
        return session;
    } private String getPayment(){
        return this.payment;
    } private double getPrice(){
        return this.price;
    } private LocalDateTime getDateTime(){
        return this.dateTime;
    } private HBox getActions(){
        return this.actions;
    }

    private void setId(int id){
        this.id = id;
    }
    private void setName(String name){
        this.name = name;
    }
    private void setSession(String session){
        this.session = session;
    }
    private void setPayment(String payment){
        this.payment = payment;
    }
    private void setPrice(double price){
        this.price = price;
    }
    private void setDateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }
    private void setActions(HBox actions){
        this.actions = actions;
    }








}

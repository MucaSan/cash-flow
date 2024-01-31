package generalPurposesClasses.cashflowcontrol;
import com.almasb.fxgl.entity.action.Action;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import generalVariables.cashflowcontrol.GlobalVariables;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeWindow <Event>{
    private Event action;
    private String pathToFXMLFile;
    public ChangeWindow(Event action, String pathToFXMLFile ){
        this.action = action;
        this.pathToFXMLFile = pathToFXMLFile;
    }
    public void setNewWindowFromMouseClick(MouseEvent event , String pathToFXMLFile) throws IOException {
        try {
            GlobalVariables.root = FXMLLoader.load(getClass().getResource(pathToFXMLFile));
            GlobalVariables.scene = new Scene(GlobalVariables.root);
            GlobalVariables.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            GlobalVariables.stage.setScene(GlobalVariables.scene);
            GlobalVariables.stage.show();
        } catch(IOException e){
            e.printStackTrace();
            e.getCause();
        }
    }
        public MouseEvent getActionMouse(){
        if (action instanceof MouseEvent){
            return (MouseEvent) action;
        } else{
            return null;
        }

    }
    public ActionEvent getActionEvent(){
        if (action instanceof ActionEvent){
            return (ActionEvent) action;
        } else{
            return null;
        }
    }
    public String getPathToFXMLFile(){
        return this.pathToFXMLFile;
    }

}

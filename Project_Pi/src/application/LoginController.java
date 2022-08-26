package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.DBConnector.DBConnector;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import com.DBConnector.*;

public class LoginController {
	public LoginController() {
		try {
			conn = DBConnector.DBcon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	Connection conn=null;
	PreparedStatement sqlquery=null;
	String sql;
	
	

    @FXML
    private Button btn_login;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_username;

    @FXML
    void btn_login_click(ActionEvent event) {
    	Boolean login=DBConnector.Login( txt_username.getText().toString().trim(),txt_password.getText().toString().trim());
    	try {
    		if(login) {
        		Stage stage1=new Stage();
                AnchorPane pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("Data.fxml"));
                stage1.hide();
                Scene scene = new Scene(pane1); 
                stage1.setScene(scene);
                stage1.show();
     	 
        	}
        	else {
        		 
        		  Alert alert=new Alert(AlertType.ERROR);
                  alert.setHeaderText("Error!");
                  alert.setContentText("Please Check Username and Password");
                  alert.showAndWait();
        	}
    		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    
    		 
    	

    }

}

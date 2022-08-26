package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import com.DBConnector.DBConnector;

public class User_SaveController {
	public User_SaveController() {
		try {
			conn = DBConnector.DBcon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane Anchor_Save;

	@FXML
	private Button Btn_Save;

	@FXML
	private CheckBox Check_Active;

	@FXML
	private ChoiceBox<String> choice_Role;

	@FXML
	private TextField txt_DisplayName;

	@FXML
	private TextField txt_Email;

	@FXML
	private PasswordField txt_Password;

	@FXML
	private TextField txt_Phone;

	@FXML
	private TextField txt_UserName;

	Connection conn;
	PreparedStatement sqlquery = null;
	String sql;

	@FXML
	void Btn_Save_Click(ActionEvent event) {

		sql = "INSERT INTO `user` ( `User_Name`, `User_DisplayName` , `User_Password`, `User_Phone`, `User_Email`, `User_Active`, `User_Role`)"
				+ " VALUES" + "(  ?, ?, ?, ?, ?,? , ?);";
		
		try {
			sqlquery = conn.prepareStatement(sql);
			sqlquery.setString(1, txt_UserName.getText().trim());
			sqlquery.setString(2, txt_DisplayName.getText().trim());
			sqlquery.setString(3, txt_Password.getText().trim());
			sqlquery.setString(4, txt_Phone.getText().trim());
			sqlquery.setString(5, txt_Email.getText().trim());
			if (Check_Active.isSelected()) {
				sqlquery.setString(6, "1");

			} else {
				sqlquery.setString(6, "0");
				System.out.println("seçili");
			}
			sqlquery.setString(7, choice_Role.getValue().toString());
			sqlquery.executeUpdate();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Okey!");

			alert.showAndWait();

		} catch (Exception e) {
			// TODO: handle exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Please Try Again");
			alert.showAndWait();
			 
		}

	}

	public void combodeger() {
		sql = "select DISTINCT  User_Role from user";

		ObservableList<String> kayitlarliste = FXCollections.observableArrayList();
		try {
			sqlquery = conn.prepareStatement(sql);

			ResultSet getirilen = sqlquery.executeQuery();

			while (getirilen.next()) {
				kayitlarliste.add(getirilen.getString("User_Role"));
				choice_Role.setItems(kayitlarliste);

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());// alert içinse vermeye çalýþ sen

		}
	}

	@FXML
	void initialize() {
		combodeger();

		assert Anchor_Save != null : "fx:id=\"Anchor_Save\" was not injected: check your FXML file 'User_Save.fxml'.";
		assert Btn_Save != null : "fx:id=\"Btn_Save\" was not injected: check your FXML file 'User_Save.fxml'.";
		assert Check_Active != null : "fx:id=\"Check_Active\" was not injected: check your FXML file 'User_Save.fxml'.";
		assert choice_Role != null : "fx:id=\"choice_Role\" was not injected: check your FXML file 'User_Save.fxml'.";
		assert txt_DisplayName != null
				: "fx:id=\"txt_DisplayName\" was not injected: check your FXML file 'User_Save.fxml'.";
		assert txt_Email != null : "fx:id=\"txt_Email\" was not injected: check your FXML file 'User_Save.fxml'.";
		assert txt_Password != null : "fx:id=\"txt_Password\" was not injected: check your FXML file 'User_Save.fxml'.";
		assert txt_Phone != null : "fx:id=\"txt_Phone\" was not injected: check your FXML file 'User_Save.fxml'.";
		assert txt_UserName != null : "fx:id=\"txt_UserName\" was not injected: check your FXML file 'User_Save.fxml'.";

	}

}

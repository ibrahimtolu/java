package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
 

import com.DBConnector.DBConnector;
 

public class DataController {
	public DataController() {
		try {
			conn = DBConnector.DBcon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private AnchorPane Anchor_right;

	@FXML
	private Button Btn_NewUser;

	@FXML
	private CheckBox Check_ActiveUser;

	@FXML
	private TableColumn<Datas, String> Email;

	@FXML
	private TableColumn<Datas, String> Enable;

	@FXML
	private TableColumn<Datas, Integer> ID;

	@FXML
	private TableColumn<Datas, String> User_Name;

	@FXML
	private TableView<Datas> tableview;

	@FXML
	void Btn_NewUser_Click(ActionEvent event) {
		try {
			Stage stage1=new Stage();
			stage1.hide();
            AnchorPane pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("User_Save.fxml"));
            
            Scene scene = new Scene(pane1);
            
            stage1.setScene(scene);
            stage1.show();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@FXML
	void Check_ActiveUser_Click(ActionEvent event) {
		DegerlerGetir(tableview);

	}

	Connection conn = null;
	PreparedStatement sqlquery = null;
	ResultSet rs = null;
	String sql;

	public void DegerlerGetir(TableView tablo) {

		ObservableList<Datas> recordslist = FXCollections.observableArrayList();
		if (Check_ActiveUser.isSelected()) {
			sql = "SELECT User_ID ,User_Name,User_Email,User_Active  FROM user where User_Active=true";
			try {
				sqlquery = conn.prepareStatement(sql);

				ResultSet result = sqlquery.executeQuery();

				while (result.next()) {
					recordslist.add(new Datas(result.getInt("User_ID"), result.getString("User_Name"),
							result.getString("User_Email"), result.getBoolean("User_Active")));
					ID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
					User_Name.setCellValueFactory(new PropertyValueFactory<>("User_Name"));
					Email.setCellValueFactory(new PropertyValueFactory<>("User_Email"));
					Enable.setCellValueFactory(new PropertyValueFactory<>("User_Active"));

					tablo.setItems(recordslist);

				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage().toString());// alert içinse vermeye çalýþ sen
			}

		} else {

			sql = "SELECT User_ID ,User_Name,User_Email,User_Active  FROM user ";
			try {
				sqlquery = conn.prepareStatement(sql);

				ResultSet getirilen = sqlquery.executeQuery();

				while (getirilen.next()) {
					recordslist.add(new Datas(getirilen.getInt("User_ID"), getirilen.getString("User_Name"),
							getirilen.getString("User_Email"), getirilen.getBoolean("User_Active")));
					ID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
					User_Name.setCellValueFactory(new PropertyValueFactory<>("User_Name"));
					Email.setCellValueFactory(new PropertyValueFactory<>("User_Email"));
					Enable.setCellValueFactory(new PropertyValueFactory<>("User_Active"));

					tableview.setItems(recordslist);

				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage().toString());// alert içinse vermeye çalýþ sen
			}

		}

	}

	@FXML
	void initialize() {

		sql = "SELECT a.urunAd,a.urunFiyat,b.KategoriAdi FROM urun a,kategori b WHERE a.KategoriID=b.KategoriID";
		DegerlerGetir(tableview);

	}

}

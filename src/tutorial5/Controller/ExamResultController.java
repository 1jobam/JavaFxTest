package tutorial5.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ExamResultController implements Initializable{

	@FXML Button inserts;
	@FXML Button cancel;
	@FXML TextField name;
	@FXML TextField guk;
	@FXML TextField su;
	@FXML TextField eng;
	@FXML Text txtname;
	@FXML Text txtguk;
	@FXML Text txtsu;
	@FXML Text txteng;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}

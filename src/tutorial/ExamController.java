package tutorial;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ExamController implements Initializable {

	@FXML 
	Button button;
	
	@FXML 
	TextArea txtarea;
	
	@FXML 
	TextField txtfield;
	
	@FXML 
	RadioButton radio1;
	
	@FXML 
	RadioButton radio2;

	@FXML 
	CheckBox check1;

	@FXML 
	CheckBox check2;

	@FXML 
	CheckBox check3;

	@FXML 
	CheckBox check4;

	@FXML 
	CheckBox check5;

	@FXML 
	CheckBox check6;

	@FXML 
	CheckBox check7;

	@FXML 
	CheckBox check8;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtfield.setPromptText("박종민");
		
		ToggleGroup gender = new ToggleGroup();
		radio1.setToggleGroup(gender);
		radio1.setUserData("남자");
		
		radio2.setToggleGroup(gender);
		radio2.setUserData("여자");
		
		radio1.setSelected(true);
		
		txtarea.setPromptText("선택하신 값에 따라 보기를 클릭하였을때 값이 변경됩니다.");
		
		Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
		alertConfirm.setTitle("이름/성별/취미 입력 프로그램");
		alertConfirm.setHeaderText("바이러스 발견 안내");
		alertConfirm.setContentText("해당 프로그램에는 바이러스가 있습니다. 실행하시겠습니까?");
		
		ButtonType confirmResult = alertConfirm.showAndWait().get();
		
		if(confirmResult == ButtonType.OK) {
			System.out.println("OK버튼을 눌렀습니다.");
		}else if(confirmResult == ButtonType.CANCEL) {
			System.exit(0);
		}
		
		button.setOnAction(e -> {
			String chk = "";
			
			if(check1.isSelected()) chk = check1.getText() + " ";
			if(check2.isSelected()) chk += check2.getText() + " ";
			if(check3.isSelected()) chk += check3.getText() + " ";
			if(check4.isSelected()) chk += check4.getText() + " ";
			if(check5.isSelected()) chk += check5.getText() + " ";
			if(check6.isSelected()) chk += check6.getText() + " ";
			if(check7.isSelected()) chk += check7.getText() + " ";
			if(check8.isSelected()) chk += check8.getText() + " ";
			
			
			String name = txtfield.getText();
			
			Object gende = gender.getSelectedToggle().getUserData();

			
			txtarea.setText("이름 : " + name + "\n\n성별 : " + gende + "\n\n취미 : " + chk);
			
		});
		
//		Optional<String> click = 
		
	}

}

package tutorial2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExamController implements Initializable{

	@FXML TextField memAddr;
	@FXML TextField memTel;
	@FXML TextField memName;
	@FXML TextField memId;
	
	@FXML Button memAdd;
	@FXML Button memEdit;
	@FXML Button memDel;
	@FXML Button memOk;
	@FXML Button memCancel;
	
	@FXML TableView<Member> table;
	@FXML TableColumn<Member, String> tabId;
	@FXML TableColumn<Member, String> tabName;
	@FXML TableColumn<Member, String> tabTel;
	@FXML TableColumn<Member, String> tabAddr;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//테스트 계정 추가 및 data 구성하기
		ObservableList<Member> data = FXCollections.observableArrayList(
				new Member("asd", "Asd", "asd", "Asd"),
				new Member("asd", "Asd", "asd", "Asd"),
				new Member("asd", "Asd", "asd", "Asd"),
				new Member("asd", "Asd", "asd", "Asd")
			);
		
		table.setItems(data);
		
		tabId.setCellValueFactory(new PropertyValueFactory<>("Id"));
		tabName.setCellValueFactory(new PropertyValueFactory<>("Name"));
		tabTel.setCellValueFactory(new PropertyValueFactory<>("Tel"));
		tabAddr.setCellValueFactory(new PropertyValueFactory<>("Addr"));
		
//		table.getColumns().addAll(tabId, tabName, tabTel, tabAddr);
		
		
		sok();
		
		
		//테이블 뷰 클릭시
		table.setOnMouseClicked(e -> {
			
			Member mem = table.getSelectionModel().getSelectedItem();
			
			memId.setText(mem.getId());
			memName.setText(mem.getName());
			memTel.setText(mem.getTel());
			memAddr.setText(mem.getAddr());
		});
		
		//추가 클릭시
		memAdd.setOnAction(e -> {
			memId.setEditable(true);
			memName.setEditable(true);
			memTel.setEditable(true);
			memAddr.setEditable(true);
			
			memEdit.setDisable(true);
			memDel.setDisable(true);
			memOk.setDisable(false);
			memCancel.setDisable(false);
			
		});
		
		//수정 클릭시
		memEdit.setOnAction(e -> {
			memId.setEditable(true);
			memName.setEditable(true);
			memTel.setEditable(true);
			memAddr.setEditable(true);
			
			memOk.setDisable(false);
			memCancel.setDisable(false);
			memAdd.setDisable(true);
			memDel.setDisable(true);
		});
		
		//삭제 클릭시
		memDel.setOnAction(e -> {
			memOk.setDisable(false);
			memCancel.setDisable(false);
			
			memAdd.setDisable(true);
			memEdit.setDisable(true);
		});
		
		//확인 클릭시
		memOk.setOnAction(e -> {
			
			if(!memAdd.isDisable()) {
				if(memId.getText().isEmpty() || memName.getText().isEmpty() || memTel.getText().isEmpty() || memAddr.getText().isEmpty()) {
					errMsg("작업 오류 발생", "항목중 비어있는 항목이 있습니다.");
					return;
				}
				data.add(new Member(memId.getText(), 
						memName.getText(),  
						memTel.getText(), 
						memAddr.getText()));
						
						memId.clear();
						memName.clear();
						memTel.clear();
						memAddr.clear();
				
			}else if(!memDel.isDisable()) {
				if(table.getSelectionModel().isEmpty()) {
					errMsg("작업 오류 발생", "삭제할 항목을 제대로 선택 하세요.");
				}
				data.remove(table.getSelectionModel().getSelectedIndex());
			}else if(!memEdit.isDisable()) {
				if(table.getSelectionModel().isEmpty()) {
					errMsg("작업 오류 발생", "수정할 항목을 선택 후 진행 해주세요.");
				}
				data.set(table.getSelectionModel().getSelectedIndex(), new Member(memId.getText(), memName.getText(), memTel.getText(), memAddr.getText()));
			}
			
		});
		
		//취소 클릭시
		memCancel.setOnAction(e -> {
			memAdd.setDisable(false);
			memEdit.setDisable(false);
			memDel.setDisable(false);
			
			memOk.setDisable(true);
			memCancel.setDisable(true);
			
			memId.setEditable(false);
			memName.setEditable(false);
			memTel.setEditable(false);
			memAddr.setEditable(false);
		});
		
	}
	
	public void sok() {
		memId.setEditable(false);
		memName.setEditable(false);
		memTel.setEditable(false);
		memAddr.setEditable(false);
		
		memOk.setDisable(true);
		memCancel.setDisable(true);
	}
	
	public void errMsg(String errTxt, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("에러 발생");
		errAlert.setHeaderText(errTxt);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
	
	
	public class Member{
		private String Id;
		private String Name;
		private String Tel;
		private String Addr;
		
		public Member(String id, String name, String tel, String addr) {
			super();
			Id = id;
			Name = name;
			Tel = tel;
			Addr = addr;
		}

		public String getId() {
			return Id;
		}

		public void setId(String id) {
			Id = id;
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public String getTel() {
			return Tel;
		}

		public void setTel(String tel) {
			Tel = tel;
		}

		public String getAddr() {
			return Addr;
		}

		public void setAddr(String addr) {
			Addr = addr;
		}	
		
		
	}
	

}

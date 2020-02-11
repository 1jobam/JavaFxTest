package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberMainController implements Initializable{

	@FXML 
	private TableView<MemberVO> table;
	@FXML 
	private TableColumn<MemberVO, String> mem_id;
	@FXML 
	private TableColumn<MemberVO, String> mem_name;
	@FXML 
	private TableColumn<MemberVO, String> mem_tel;
	@FXML 
	private TableColumn<MemberVO, String> mem_addr;

	@FXML 
	private Pagination paging;
	
	private int from, to, itemsForPage;
	
	private IMemberService memService;
	
	private ObservableList<MemberVO> data, currentPageData;
	
	private String memId;
	
	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tb();
	}
	
	public void tb() {
		data = FXCollections.observableArrayList();
		
		mem_id.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		mem_name.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		mem_tel.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
		mem_addr.setCellValueFactory(new PropertyValueFactory<>("mem_addr"));
		
		table.setItems(data);
	}
	
	public void reset() {
		itemsForPage = 8;
		int totPageCount = data.size() % itemsForPage == 0 ? data.size()/itemsForPage : data.size()/itemsForPage + 1;
		paging.setPageCount(totPageCount);
		paging.setMaxPageIndicatorCount(5);
		paging.setPageFactory(new Callback<Integer, Node>() {
			
			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage - 1;
				table.setItems(getTableViewData(from, to));
				
				return table;
			}
		});
	}
	
	protected ObservableList<MemberVO> getTableViewData(int from, int to) {
		
		// 현재 페이지의 데이터 초기화
		currentPageData = FXCollections.observableArrayList();
		
		int totSize = data.size();
		for(int i = from; i <= to && i < totSize; i++) {
		currentPageData.add(data.get(i));
		}
		
		return currentPageData;
	}

	@FXML 
	public void end() {
		Platform.exit();
	}
	
	@FXML 
	public void selectAllMember() {
		memService = MemberServiceImpl.getInstance();
		
		data = FXCollections.observableArrayList(memService.getAllMemberList());
		
		table.getItems().addAll(data);
		
		reset();
	}

	@FXML public void insertMember(ActionEvent event) {
		Stage register = new Stage(StageStyle.UTILITY);
		
		register.initModality(Modality.APPLICATION_MODAL);
		
		register.initOwner(primaryStage);
		
		register.setTitle("회원등록 화면");
		
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("MemberRegisterFXML.fxml"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		TextField id = (TextField) parent.lookup("#mem_id");
		TextField name = (TextField) parent.lookup("#mem_name");
		TextField tel = (TextField) parent.lookup("#mem_tel");
		TextField addr = (TextField) parent.lookup("#mem_addr");
		
		Button regis = (Button) parent.lookup("#register");
		regis.setOnAction(e -> {
			memService = MemberServiceImpl.getInstance();
			
				String b = id.getText();
			
				boolean a = memService.getMember(b);
				
				if(a) {
					return;
				}
				
				MemberVO mv = new MemberVO();
				mv.setMem_id(id.getText());
				mv.setMem_name(name.getText());
				mv.setMem_tel(tel.getText());
				mv.setMem_addr(addr.getText());
				
				memService.insertMember(mv);
				
				selectAllMember();
				
				register.close();
		});
		
		Button ex = (Button) parent.lookup("#cancel");
		
		ex.setOnAction(e -> {
			register.close();
		});
		
		Scene scene = new Scene(parent);
		
		register.setScene(scene);
		register.setResizable(true);
		register.show();
	}
	
	@FXML 
	public void deleteMember(ActionEvent event) {
		memService = MemberServiceImpl.getInstance();
		memId = table.getSelectionModel().getSelectedItem().getMem_id();
		
		Alert test = new Alert(AlertType.CONFIRMATION);
		test.setTitle("사용자 삭제 확인 메시지");
		test.setHeaderText(memId + "님의 삭제를 진행합니다");
		test.setContentText("정말로 삭제하시겠습니까?");
		
		ButtonType result = test.showAndWait().get();
		
		if(result == ButtonType.OK) {
			memService.deleteMember(memId);
			selectAllMember();
		}else if(result == ButtonType.CANCEL) {
			return;
		}
		
	}

	@FXML public void updateMember(ActionEvent event) {}

	@FXML public void selectMember(ActionEvent event) {}
	

	


}

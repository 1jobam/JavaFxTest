package board.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javax.xml.soap.Node;

import board.service.BoardService;
import board.service.BoardServiceImpl;
import board.vo.BoardVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import kr.or.ddit.member.vo.MemberVO;

public class BoardMainController implements Initializable{

	@FXML 
	private TextField tex;

	@FXML 
	private Button upda;

	@FXML 
	private Button dele;
	
	@FXML 
	private TableView<BoardVO> table;
	@FXML 
	private TableColumn<BoardVO, Integer> no;
	@FXML 
	private TableColumn<BoardVO, String> title;
	@FXML 
	private TableColumn<BoardVO, String> writer;
	@FXML 
	private TableColumn<BoardVO, Date> date;
	@FXML 
	private TableColumn<BoardVO, String> content;
	
	private int from, to, itemsForPage;
	
	private ObservableList<BoardVO> data, currentPageData;
	
	private BoardService boardservice;
	
	private Stage primaryStage;
	
	private int board_no;

	@FXML 
	Pagination pagination;


	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectAllBoard();
		
		table.setOnMouseClicked(e -> {
			if(e.getClickCount() > 1) {
				board_no = table.getSelectionModel().getSelectedItem().getBoard_no();
				upda.setDisable(false);
				dele.setDisable(false);
			}
		});
	}
	
	public void selectAllBoard() {
		boardservice = BoardServiceImpl.getInstance();
		
		data = FXCollections.observableArrayList(boardservice.getAllBoard());
		
		no.setCellValueFactory(new PropertyValueFactory<>("board_no"));
		title.setCellValueFactory(new PropertyValueFactory<>("board_title"));
		writer.setCellValueFactory(new PropertyValueFactory<>("board_writer"));
		date.setCellValueFactory(new PropertyValueFactory<>("board_date"));
		content.setCellValueFactory(new PropertyValueFactory<>("board_content"));
		
		table.setItems(data);;
		
		hidden();
	
	}
	
	public void reset() {
		itemsForPage = 8;
		int totPageCount = data.size() % itemsForPage == 0 ? data.size()/itemsForPage : data.size()/itemsForPage + 1;
		pagination.setPageCount(totPageCount);
		pagination.setMaxPageIndicatorCount(5);
		pagination.setPageFactory(new Callback<Integer, Node>() {
			
			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage - 1;
				table.setItems(getTableViewData(from, to));
				
				return table;
			}
		});
	}
	protected ObservableList<BoardVO> getTableViewData(int from, int to) {
			
			// 현재 페이지의 데이터 초기화
			currentPageData = FXCollections.observableArrayList();
			
			int totSize = data.size();
			for(int i = from; i <= to && i < totSize; i++) {
			currentPageData.add(data.get(i));
			}
			
			return currentPageData;
		}
	
	public void hidden() {
		upda.setDisable(true);
		dele.setDisable(true);
	}

	@FXML 
	public void register(MouseEvent event) {
		Stage reg = new Stage(StageStyle.UTILITY);
		
		reg.setTitle("게시글 등록");
		
		reg.initModality(Modality.APPLICATION_MODAL);
		
		reg.initOwner(primaryStage);
		
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("BoardRegisterFXML.fxml"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		TextField wri = (TextField) parent.lookup("#writer");
		TextField tit = (TextField) parent.lookup("#title");
		TextArea texa = (TextArea) parent.lookup("#textarea");
		
		Button regis = (Button) parent.lookup("#register");
		regis.setOnAction(e -> {
			boardservice = BoardServiceImpl.getInstance();
			
			String board_writer = wri.getText();
			String board_title = tit.getText();
			String board_content = texa.getText();
			
			BoardVO bv = new BoardVO();
			bv.setBoard_writer(board_writer);
			bv.setBoard_title(board_title);
			bv.setBoard_content(board_content);
			
			boardservice.insertBoard(bv);
			
			reg.close();
			
			selectAllBoard();
		});
		
		
		Button ex = (Button) parent.lookup("#cancel");
		ex.setOnAction(e -> {
			reg.close();
		});
		
		Scene scene = new Scene(parent);
		
		reg.setScene(scene);
		reg.setResizable(true);
		reg.show();

	}
	
	@FXML 
	public void delete(MouseEvent event) {
		boardservice = BoardServiceImpl.getInstance();
		BoardVO bv = new BoardVO();
		int board_no = table.getSelectionModel().getSelectedItem().getBoard_no();
		
		Alert test = new Alert(AlertType.CONFIRMATION);
		test.setTitle("게시글 삭제");
		test.setHeaderText(board_no + "번의 게시글 삭제 진행");
		test.setContentText("게시글을 정말 삭제하시겠습니까?");
		
		ButtonType result = test.showAndWait().get();
		
		if(result == ButtonType.OK) {
			bv.setBoard_no(board_no);
			
			boardservice.deleteBoard(bv);
			
			selectAllBoard();
		}else if(result == ButtonType.CANCEL){
			test.close();
		}

	}

	@FXML 
	public void update(MouseEvent event) {

		Stage updat = new Stage();
		
		updat.setTitle("수정화면");
		updat.initModality(Modality.APPLICATION_MODAL);		
		updat.initOwner(primaryStage);
		
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("BoardUpdateFXML.fxml"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		TextField wri = (TextField) parent.lookup("#writer");
		TextField tit = (TextField) parent.lookup("#title");
		TextArea text = (TextArea) parent.lookup("#textarea");
		
		wri.setText(table.getSelectionModel().getSelectedItem().getBoard_writer());
		tit.setText(table.getSelectionModel().getSelectedItem().getBoard_title());
		text.setText(table.getSelectionModel().getSelectedItem().getBoard_content());	

		Button up = (Button) parent.lookup("#up");
		up.setOnAction(e -> {
			boardservice = BoardServiceImpl.getInstance();
			String board_writer = wri.getText();
			String board_title = tit.getText();
			String board_content = text.getText(); 
			
			System.out.println(board_writer + board_title + board_content);
			
			BoardVO bv = new BoardVO();
			bv.setBoard_writer(board_writer);
			bv.setBoard_title(board_title);
			bv.setBoard_content(board_content);
			bv.setBoard_no(board_no);
			
			boardservice.updateBoard(bv);

			updat.close();
			selectAllBoard();
		});
		
		Button can = (Button) parent.lookup("#can");
		can.setOnAction(e -> {
			updat.close();
		});
		
		Scene scene = new Scene(parent);
		updat.setScene(scene);
		updat.setResizable(true);
		updat.show();
		
	}

	@FXML 
	public void select(MouseEvent event) {
		if(tex.getText().isEmpty()) {
			selectAllBoard();
		}else {
			boardservice = BoardServiceImpl.getInstance();
			BoardVO bv = new BoardVO();
			int board_no = Integer.parseInt(tex.getText());
			bv.setBoard_no(board_no);
			ObservableList<BoardVO> da = FXCollections.observableArrayList(boardservice.getSearchBoard(bv));
			table.setItems(da);
		}

	}
}

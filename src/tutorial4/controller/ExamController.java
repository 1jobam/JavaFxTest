package tutorial4.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tutorial4.vo.ExamVO;

public class ExamController implements Initializable {

	@FXML
	private ComboBox<String> prod_select;
	@FXML
	private ComboBox<String> prod_nameselect;

	@FXML
	private TableView<ExamVO> prod_table;

	@FXML
	private TableColumn<ExamVO, String> prod_id;
	@FXML
	private TableColumn<ExamVO, String> prod_name;
	@FXML
	private TableColumn<ExamVO, String> prod_lgu;
	@FXML
	private TableColumn<ExamVO, String> prod_buyer;
	@FXML
	private TableColumn<ExamVO, Integer> prod_cost;
	@FXML
	private TableColumn<ExamVO, Integer> prod_price;
	@FXML
	private TableColumn<ExamVO, Integer> prod_sale;
	@FXML
	private TableColumn<ExamVO, String> prod_outline;
	@FXML
	private TableColumn<ExamVO, String> prod_detail;

	private Connection conn = null;
	private Statement stat = null;
	private ResultSet res = null;
	
	private String url = "jdbc:oracle:thin:@localhost:1521/xe";
	private String userId = "PC02";
	private String password = "java";

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 콤보박스 설정
		String combo1 = "select * from lprod";
		combo1(combo1);
		prod_select.setValue("컴퓨터제품");
		String sqls = "select * from prod where prod_name = '모니터 삼성전자15인치칼라'";
		ExamDB(sqls);
		
		// 콤보박스 설정2
		String combo2 = "select * from prod where prod_lgu = 'P101'";
		prod_nameselect.setValue("모니터 삼성전자15인치 칼라");
		combo2(combo2);
		

		// 대분류 변경작업
		prod_select.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				String sqls;
				if(prod_select.getSelectionModel().getSelectedItem().equals("컴퓨터제품")) {
					prod_nameselect.setDisable(false);
					prod_nameselect.getItems().clear();
					sqls = "select prod_name from prod where prod_lgu = 'P101'";
					prod_nameselect.setValue("모니터 삼성전자15인치 칼라");
					combo2(sqls);
				}else if(prod_select.getSelectionModel().getSelectedItem().equals("전자제품")){
					prod_nameselect.setDisable(false);
					prod_nameselect.getItems().clear();
					sqls = "select prod_name from prod where prod_lgu = 'P102'";
					prod_nameselect.setValue("대우 칼라 TV 25인치");
					combo2(sqls);
				}else if(prod_select.getSelectionModel().getSelectedItem().equals("여성캐주얼")) {
					prod_nameselect.setDisable(false);
					prod_nameselect.getItems().clear();
					sqls = "select prod_name from prod where prod_lgu = 'P201'";
					prod_nameselect.setValue("여성 봄 셔츠 1");
					combo2(sqls);
				}else if(prod_select.getSelectionModel().getSelectedItem().equals("남성캐주얼")) {
					prod_nameselect.setDisable(false);
					prod_nameselect.getItems().clear();
					sqls = "select prod_name from prod where prod_lgu = 'P202'"; 
					prod_nameselect.setValue("남성 봄 셔츠 1");
					combo2(sqls);
				}else if(prod_select.getSelectionModel().getSelectedItem().equals("피혁잡화")) {
					prod_nameselect.setDisable(false);
					prod_nameselect.getItems().clear();
					sqls = "select prod_name from prod where prod_lgu = 'P301'";
					prod_nameselect.setValue("악어 가죽 혁대");
					combo2(sqls);
				}else if(prod_select.getSelectionModel().getSelectedItem().equals("화장품")) {
					prod_nameselect.setDisable(false);
					prod_nameselect.getItems().clear();
					sqls = "select prod_name from prod where prod_lgu = 'P302'";
					prod_nameselect.setValue("향수 NO 5");
					combo2(sqls);
				}else if(prod_select.getSelectionModel().getSelectedItem().equals("음반/CD")) {
					prod_nameselect.getItems().clear();
					prod_nameselect.setDisable(true);
				}else if(prod_select.getSelectionModel().getSelectedItem().equals("도서")) {
					prod_nameselect.getItems().clear();
					prod_nameselect.setDisable(true);
				}else if(prod_select.getSelectionModel().getSelectedItem().equals("문구류")) {
					prod_nameselect.getItems().clear();
					prod_nameselect.setDisable(true);
				}
			}
		});
		
		
		// 중분류 변경 작업 및 테이블 insert
		prod_nameselect.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println();
				String sqls = "select * from prod where prod_name = '" + newValue + "'";
				ExamDB(sqls);
				
			}
		});
		
		// 테이블 설정
		prod_id.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
		prod_name.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
		prod_lgu.setCellValueFactory(new PropertyValueFactory<>("prod_lgu"));
		prod_buyer.setCellValueFactory(new PropertyValueFactory<>("prod_buyer"));
		prod_cost.setCellValueFactory(new PropertyValueFactory<>("prod_cost"));
		prod_price.setCellValueFactory(new PropertyValueFactory<>("prod_price"));
		prod_sale.setCellValueFactory(new PropertyValueFactory<>("prod_sale"));
		prod_outline.setCellValueFactory(new PropertyValueFactory<>("prod_outline"));
		prod_detail.setCellValueFactory(new PropertyValueFactory<>("prod_detail"));

	}

	// 콤보박스 2 DB연동
	public void combo2(String sqls) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, userId, password);

			stat = conn.createStatement();

			res = stat.executeQuery(sqls);
			
			while (res.next()) {
				
				prod_nameselect.getItems().add(res.getString("prod_name"));
				
			}
			
			finals();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	// 콤보박스 1 DB연동
	public void combo1(String sqls) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, userId, password);

			stat = conn.createStatement();

			res = stat.executeQuery(sqls);

			while (res.next()) {
				String a = res.getString("lprod_nm");
				prod_select.getItems().add(a);
			}
			
			finals();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	// 테이블 뷰 컬럼값 추가
	public void ExamDB(String sqls) {

		ObservableList<ExamVO> data = FXCollections.observableArrayList();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, userId, password);

			stat = conn.createStatement();

			String sql = sqls;

			res = stat.executeQuery(sql);

			System.out.println("------------------------------------");
			System.out.println("실핸한 쿼리 : " + sql);
			System.out.println("------------------------------------");

			while (res.next()) {				
				data.addAll(new ExamVO(res.getString("prod_id"),
						res.getString("prod_name"),
						res.getString("prod_lgu"),
						res.getString("prod_buyer"),
						Integer.parseInt(res.getString("prod_cost")),
						Integer.parseInt(res.getString("prod_price")),
						Integer.parseInt(res.getString("prod_sale")),
						res.getString("prod_outline"),
						res.getString("prod_detail")));
			}
			
			prod_table.setItems(data);

			
			finals();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	//자원반납
	public void finals() {
		try {
			
		
		}
		finally {
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e2) {
				}
			if (stat != null)
				try {
					stat.close();
				} catch (Exception e2) {
				}
			if (res != null)
				try {
					res.close();
				} catch (Exception e2) {
				}
	}

}
}

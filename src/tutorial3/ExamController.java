package tutorial3;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExamController implements Initializable {

	@FXML
	private ComboBox<String> combo;
	@FXML
	private TextField txt;
	@FXML
	private Button btn;
	@FXML
	private TableView<ExamVO> table;
	@FXML
	private TableColumn<ExamVO, String> zipcode;
	@FXML
	private TableColumn<ExamVO, String> sido;
	@FXML
	private TableColumn<ExamVO, String> gungu;
	@FXML
	private TableColumn<ExamVO, String> dong;
	@FXML
	private TableColumn<ExamVO, String> bunji;

	private String sql;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		sql = "select * from ziptb";
		ExamDB(sql);

		zipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
		sido.setCellValueFactory(new PropertyValueFactory<>("sido"));
		gungu.setCellValueFactory(new PropertyValueFactory<>("gungu"));
		dong.setCellValueFactory(new PropertyValueFactory<>("dong"));
		bunji.setCellValueFactory(new PropertyValueFactory<>("bunji"));

		// 콤보박스 수정
		combo.getItems().addAll("동이름", "우편번호");
		combo.setValue("동이름");

		// 버튼 동작
		btn.setOnAction(e -> {
			String nm = combo.getValue();

			String text = txt.getText();

			if (nm.equals("동이름")) {
				sql = "select * from ziptb where dong like" + "'%" + text + "%'";
				ExamDB(sql);
			} else if (nm.equals("우편번호")) {
				sql = "select * from ziptb where zipcode like" + "'%" + text + "%'";
				ExamDB(sql);
			}

		});

	}

	private void ExamDB(String sql) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ObservableList<ExamVO> data = FXCollections.observableArrayList();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "MY01";
			String password = "orcle";

			conn = DriverManager.getConnection(url, userId, password);

			stmt = conn.createStatement();

			String sqls = sql;

			rs = stmt.executeQuery(sqls);

			System.out.println("------------------------------------");
			System.out.println("실핸한 쿼리 : " + sqls);
			System.out.println("------------------------------------");
			
			while (rs.next()) {
				String a = rs.getString("zipcode");
				String b = rs.getString("sido");
				String c = rs.getString("gugun");
				String d = rs.getString("dong");
				String e = rs.getString("bunji");

				data.addAll(new ExamVO(a, b, c, d, e));
			}

			table.setItems(data);

			System.out.println("출력 완료...");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e2) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e2) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e2) {
				}
		}
	}
}

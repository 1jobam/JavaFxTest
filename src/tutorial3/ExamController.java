package tutorial3;

import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.ResourceBundle;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
		
			List<ExamVO> exlist = smc.queryForList("exam.getExamAll");
			
			ObservableList<ExamVO> data = FXCollections.observableArrayList(exlist);
			
			rd.close(); // Reader객체 닫기
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
//		ObservableList<ExamVO> data = FXCollections.observableArrayList(new ExamVO("asd","asd","ASd","asd","ASd"));
		
//		table.setItems(data);

		zipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
		sido.setCellValueFactory(new PropertyValueFactory<>("sido"));
		gungu.setCellValueFactory(new PropertyValueFactory<>("gungu"));
		dong.setCellValueFactory(new PropertyValueFactory<>("dong"));
		bunji.setCellValueFactory(new PropertyValueFactory<>("bunji"));

//		table.getColumns().addAll(zipcode, sido, gungu, dong, bunji);

		// 콤보박스 수정
		combo.getItems().addAll("동이름", "우편번호");
		combo.setValue("동이름");
		
		 //버튼 동작
		 btn.setOnAction(e -> {
		 String nm = combo.getValue();
		
		 if (nm.equals("동이름")) {
		
		 } else if (nm.equals("우편번호")) {
		
		 }
		
		 });

	}
}

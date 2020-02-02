package tutorial5.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application.Parameters;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import tutorial3.Exam;
import tutorial5.vo.ExamVO;

public class ExamMainController implements Initializable{

	@FXML TableView<ExamVO> table;
	@FXML TableColumn<ExamVO, String> name;
	@FXML TableColumn<ExamVO, Integer> guk;
	@FXML TableColumn<ExamVO, Integer> su;
	@FXML TableColumn<ExamVO, Integer> eng;
	@FXML Button insert;
	@FXML Button grape;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		insert.setOnAction(e -> {
			
			Exam as = new Exam();
			
			Stage student = new Stage(StageStyle.UTILITY);
			
			student.initModality(Modality.APPLICATION_MODAL);
			
			
			
//			student.initOwner();
			
			student.setTitle("추가");
			
			Parent parent = null;
			try {
				parent = FXMLLoader.load(getClass().getResource("fxml/FxmlResultExam.fxml"));
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			
			Scene scene = new Scene(parent);
			
			student.setScene(scene);
			student.setResizable(false);
			student.show();
			
		});

	}
}

package tutorial5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tutorial5.Controller.ExamMainController;

public class Exam extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/FxmlMainExam.fxml"));
		Parent root = loader.load();

		// 메인 스테이지
		Scene scene = new Scene(root);
		primaryStage.setTitle("AppMain");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		// 스테이지 컨트롤러로 패스
		
		ExamMainController controller = loader.getController();
		controller.setPrimaryStage(primaryStage);
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}

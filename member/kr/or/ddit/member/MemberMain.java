package kr.or.ddit.member;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kr.or.ddit.member.controller.MemberMainController;

public class MemberMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("controller/MemberMainFXML.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("회원가입 프로그램");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		MemberMainController pri = new MemberMainController();
		pri.setPrimaryStage(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}







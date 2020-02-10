package kr.or.ddit.member;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kr.or.ddit.member.controller.MemberMainController;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

public class MemberMain extends Application {
	
	// Service객체 변수를 선언한다.
	private IMemberService memService;
	
	public MemberMain() {
		memService = MemberServiceImpl.getInstance();
	} 

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







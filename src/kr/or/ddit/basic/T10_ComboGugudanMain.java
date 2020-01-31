package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class T10_ComboGugudanMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		/*BorderPane root = new BorderPane();
		root.setPrefSize(300, 300);
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10));
		hbox.setSpacing(10);
		
		TextArea txtResult = new TextArea();
		txtResult.setPrefSize(200, 200);
		
		Button btnDan = new Button("출  력");
		
		ComboBox<Integer> cmbDan = new ComboBox<>();
		cmbDan.setPrefWidth(150);
		
		ObservableList<Integer> list = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		cmbDan.setItems(list);
		
		btnDan.setOnAction(e -> {
			int dan = cmbDan.getSelectionModel().getSelectedItem();
			
			txtResult.setText(dan + "단\n\n");
			for(int i = 1; i <= 9; i++) {
				int r = dan * i;
				txtResult.appendText(dan + " * " + i + " = " + r + "\n");
			}
		});
		
		
		hbox.getChildren().addAll(cmbDan, btnDan);
		
		root.setTop(hbox);
		root.setCenter(txtResult);
		*/
		
		Parent root = FXMLLoader.load(getClass().getResource("ComboGugudan.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("콤보박스 구구단");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

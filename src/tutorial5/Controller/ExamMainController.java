package tutorial5.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tutorial5.vo.ExamVO;

public class ExamMainController implements Initializable {

	@FXML
	TableView<ExamVO> table;
	@FXML
	TableColumn<ExamVO, String> name;
	@FXML
	TableColumn<ExamVO, Integer> guk;
	@FXML
	TableColumn<ExamVO, Integer> su;
	@FXML
	TableColumn<ExamVO, Integer> eng;
	@FXML
	Button insert;
	@FXML
	Button grape;
	

	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	ObservableList<ExamVO> data = FXCollections.observableArrayList(
			new ExamVO("박종민", 100, 100, 100),
			new ExamVO("박민종", 30, 90, 100),
			new ExamVO("종민박", 80, 20, 60),
			new ExamVO("민박종", 100, 30, 20)
			);

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		table.getItems().addAll(data);


		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		guk.setCellValueFactory(new PropertyValueFactory<>("guk"));
		su.setCellValueFactory(new PropertyValueFactory<>("su"));
		eng.setCellValueFactory(new PropertyValueFactory<>("eng"));
		
		
		table.setOnMouseClicked(e ->{
			
			if(e.getClickCount() > 1) {
				
				Stage dialog = new Stage(StageStyle.UTILITY);
				
				dialog.initModality(Modality.NONE);
				
				dialog.initOwner(primaryStage);
				
				dialog.setTitle("파이 그래프");
			
				PieChart pieChart = new PieChart();
				
				Integer a = table.getSelectionModel().getSelectedItem().getGuk();
				Integer b = table.getSelectionModel().getSelectedItem().getSu();
				Integer c = table.getSelectionModel().getSelectedItem().getEng();
				
				// 차트에 나타날 데이터 구성하기
				ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
						new PieChart.Data("국어", a),
						new PieChart.Data("수학", b),
						new PieChart.Data("영어", c)
						);
				
				
				
				pieChart.setLabelLineLength(30);
				pieChart.setLegendSide(Side.BOTTOM); // 범례가 나타날 위치
				pieChart.setData(pieChartData); // 데이터 설정
				
				Scene scene = new Scene(pieChart, 300, 300);
				dialog.setScene(scene);
				dialog.setResizable(false);
				dialog.show();
			
			}
		});
		
		
	}

	@FXML 
	public void insertstages(ActionEvent event){
		Stage dialog = new Stage(StageStyle.UTILITY);
		
		dialog.initModality(Modality.NONE);
		
		dialog.initOwner(primaryStage);
		
		dialog.setTitle("사용자 정의 창");

		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("FxmlResultExam.fxml"));
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	
		TextField name = (TextField) parent.lookup("#name");
		TextField guk = (TextField) parent.lookup("#guk");
		TextField su = (TextField) parent.lookup("#su");
		TextField eng = (TextField) parent.lookup("#eng");
		
		Button inserts = (Button) parent.lookup("#inserts");
		inserts.setOnAction(e2 -> {
			data.clear();
			data.add(new ExamVO(name.getText(), Integer.parseInt(guk.getText()), Integer.parseInt(su.getText()), Integer.parseInt(eng.getText())));
			table.getItems().addAll(data);
			dialog.close();
		});
		
		Button cancel = (Button) parent.lookup("#cancel");
		cancel.setOnAction(e3 -> {
			dialog.close();
		});
		
		// 5. Scene객체 생성해서 컨테이너 객체 추가
		Scene scene = new Scene(parent);
		
		// 6. Stage객체에 Scene객체 추가
		dialog.setScene(scene);
		dialog.setResizable(false); //크기고정
		dialog.show();
	}

	@FXML 
	public void bargrape(ActionEvent event) {
		
		Stage bar = new Stage(StageStyle.UTILITY);
		
		bar.initModality(Modality.NONE);
		
		bar.initOwner(primaryStage);
		
		bar.setTitle("막대 그래프");

		CategoryAxis xAxis = new CategoryAxis();
		
		NumberAxis yAxis = new NumberAxis();
		
		BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
		
		XYChart.Series<String, Number> ser1 = new XYChart.Series<>();
		XYChart.Series<String, Number> ser2 = new XYChart.Series<>();
		XYChart.Series<String, Number> ser3 = new XYChart.Series<>();

		ser1.setName("국어");
		ser2.setName("수학");
		ser3.setName("영어");
		
		for(int i =0; i<table.getItems().size(); i++) {
		ser1.getData().add(new XYChart.Data<String, Number>(table.getItems().get(i).getName(),table.getItems().get(i).getGuk()));
		ser2.getData().add(new XYChart.Data<String, Number>(table.getItems().get(i).getName(),table.getItems().get(i).getSu()));
		ser3.getData().add(new XYChart.Data<String, Number>(table.getItems().get(i).getName(),table.getItems().get(i).getEng()));
		}
		
		bc.getData().addAll(ser1, ser2, ser3);
		
		Scene scene = new Scene(bc, 400, 200);
		
		bar.setTitle("BarChart 연습");
		bar.setScene(scene);
		bar.show();
		
	}
	
}

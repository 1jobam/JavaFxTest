package kr.or.ddit.basic.timeclock;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RootController implements Initializable {
	
	@FXML private Button btnStart;
	@FXML private Button btnStop;
	@FXML private Label lbTime;
	
	private boolean isStoped;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btnStart.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				handleBtnStart(event);
				
			}
		});
		
		btnStop.setOnAction(e->handleBtnStop(e)); // 람다시 이용
	}
	
	
	public void handleBtnStart(ActionEvent e) {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				isStoped = false;
				while(!isStoped) {
					try {
						Thread.sleep(500);
						Platform.runLater(new Runnable() { 
							// 작업쓰레드가 직접 UI를 변경할 수 없기 때문에 Platform.runLater()호출
							// runLater() 메소드는 이벤트 큐에 Runnable객체를 저장하고 즉시 리턴함.                               
							@Override
							public void run() {
								lbTime.setText(sdf.format(new Date()));
							}
						});
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		thread.setDaemon(true);
		thread.start();
		
	}
	
	public void handleBtnStop(ActionEvent e) {
		isStoped = true; // 취소
	}
	

}

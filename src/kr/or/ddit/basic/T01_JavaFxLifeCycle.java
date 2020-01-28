package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.stage.Stage;
/*
	Stage(무대) => window창
	Scene(장면) => 무대에는 하나의 장면이 배치된다.
	
	- javaFx가 실행되는 순서
	
	main()메서드 => launch()메서드 => 해당객체의 생성자 메서드
	=> init()메서드 => start()메서드 => 사용 후 종료 => stop()메서드
 */
public class T01_JavaFxLifeCycle extends Application {
	
	public T01_JavaFxLifeCycle() {
		System.out.println(Thread.currentThread().getName() + " : 생성자 메서드 호출");
	}
	
	@Override
	public void init() throws Exception {
		System.out.println(Thread.currentThread().getName() + " : init() 메서드 호출");
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println(Thread.currentThread().getName() + " : start() 메서드 호출");
		
		primaryStage.show(); // 창 보이기
	}

	@Override
	public void stop() throws Exception {
		System.out.println(Thread.currentThread().getName() + " : stop() 메서드 호출");
	}
	
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " : main() 메서드 호출");
		Application.launch(args); // 앞에 Application은 생략이 가능하다.
	}

}

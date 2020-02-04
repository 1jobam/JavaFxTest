package application;

import java.util.List;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Horse extends Thread {

	private int distance;
	private int finishDistance;
	private ImageView imageView_horse;
	private String name;
	private List<String> finishLine;
	private MainController main;

	public Horse(ImageView imageView_horse, String name, int finishDistance, List<String> finishLine,
			MainController main) {
		this.imageView_horse = imageView_horse;
		this.name = name;
		this.finishDistance = finishDistance;
		this.finishLine = finishLine;
		this.main = main;
	}

	public void run() {

		while (distance < finishDistance) {

			int runDistance = (int) (Math.random() * 10) + 1;
			distance += runDistance;
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					imageView_horse.setX(distance);
				}
			});

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		finishLine.add(this.name);
		System.out.println(this.name + "말 추가됨");
		
		
		if (finishLine.size() == 10) {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					main.showResult();
					
					main.btnStartGame.setDisable(false);
					main.btnStartGame.setText("경마 사작");
				}
			});
		}
	}

}

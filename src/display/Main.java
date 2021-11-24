package display;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import simulation.world.World;

public class Main extends Application {
	private static final String APP_NAME = "Bacteria evolution simulator";
	private static int WIDTH = 1750;
	private static int HEIGHT = 900;
	private static int MENU_WIDTH = 100;




	@Override
	public void start(Stage primaryStage) throws Exception{
		World.init();
		Parent window = FXMLLoader.load(getClass().getResource("sample.fxml"));
		primaryStage.setTitle(APP_NAME);
		primaryStage.setScene(new Scene(window, WIDTH, HEIGHT));
		View view = new View(World.getWidth(), World.getHeight());
		view.drawUI(primaryStage, WIDTH, HEIGHT, MENU_WIDTH);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

package display;

import display.controllers.Controller;
import display.controllers.PlayPauseEvent;
import display.controllers.PopupLegendEvent;
import display.controllers.perspectives.DefaultPerspective;
import display.controllers.perspectives.Perspective;
import display.graphics.PopupText;
import display.graphics.Tile;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import simulation.Simulation;

public class View {
	private Pane root;
	private Pane fieldContainer;
	private Tile[][] tiles;
	private PopupText legend;
	private double tileSide;
	private Pane controls = new VBox(5);
	Perspective perspective;
	private AnimationTimer timer;
	private Controller controller = new Controller();


	public View(int tilesX, int tilesY) {
		tiles = new Tile[tilesY][tilesX];
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public PopupText getLegend() {
		return legend;
	}

	public Pane getRoot() {
		return root;
	}

	public Pane getFieldContainer() {
		return fieldContainer;
	}

	public double getTileSide() {
		return tileSide;
	}

	public void drawUI(Stage primaryStage, int width, int height, int menuWidth) {
		//root
		root = new GridPane();
		((GridPane) root).setHgap(1);
		RowConstraints bottomConstraints = new RowConstraints();
		bottomConstraints.setVgrow(Priority.ALWAYS);
		((GridPane) root).getRowConstraints().add(bottomConstraints);

		//left column
		ColumnConstraints leftColumnSettings = new ColumnConstraints(menuWidth);
		leftColumnSettings.setHgrow(Priority.NEVER);
		((GridPane) root).getColumnConstraints().add(leftColumnSettings);

		GridPane leftColumn = new GridPane();
		leftColumn.setValignment(leftColumn, VPos.TOP);
		leftColumn.add(controls, 0, 0);
		controls.setTranslateY(10);
		Button playPause = new Button("play");
		playPause.setId("play-pause-button");
		controls.getChildren().add(playPause);
		controls.setTranslateX(10);
		((GridPane) root).add(leftColumn, 0,0);

		//play-pause
		View v = this;
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				try {
					controller.update();
				} catch (Exception e) {
					e.printStackTrace();
				}
				perspective.refresh(v);
			}
		};

		EventHandler<MouseEvent> playPauseEvent = new PlayPauseEvent(this);
		playPause.setOnMouseClicked(playPauseEvent);



		//field
		fieldContainer = new Pane();

		createTiles(width - menuWidth);

		legend = new PopupText();
		legend.setVisible(false);
		fieldContainer.getChildren().add(legend);

		//fieldContainer.setOnMouseMoved(new PopupLegendEvent(this, false));
		//fieldContainer.setOnMouseClicked(new PopupLegendEvent(this, true));
		fieldContainer.setPrefWidth(width - menuWidth);
		fieldContainer.setPrefHeight(height);

		perspective = new DefaultPerspective();
		perspective.init(this);

		((GridPane) root).add(fieldContainer, 1, 0);
		Scene scene = new Scene(root, width, height, Color.WHITE);
		primaryStage.setScene(scene);
	}

	private void createTiles(int width) {
		tileSide = (width) / ((double) tiles[0].length);
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[0].length; x++) {
				Tile tile = new Tile(x, y, tileSide);
				tiles[y][x] = tile;
				fieldContainer.getChildren().add(tile);
			}
		}
	}

	public AnimationTimer getTimer() {
		return timer;
	}
}

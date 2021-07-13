package display.controllers;

import display.View;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class PlayPauseEvent implements EventHandler<MouseEvent> {
	private static boolean isRunning = false;
	View view;

	public PlayPauseEvent(View view) {
		this.view = view;
	}

	@Override
	public void handle(MouseEvent mouseEvent) {
		if (!isRunning) {
			view.getTimer().start();
			//mouseEvent.
			//playPause.setText("pause");
		} else {
			view.getTimer().stop();
			//playPause.setText("play");
		}
		isRunning = !isRunning;
	}
}

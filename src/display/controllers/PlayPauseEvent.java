package display.controllers;

import display.View;
 import javafx.event.EventHandler;
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
		} else {
			view.getTimer().stop();
		}
		isRunning = !isRunning;
		//view.refreshUI();
	}
}

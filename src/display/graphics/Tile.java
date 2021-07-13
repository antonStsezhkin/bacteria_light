package display.graphics;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {
	private boolean active = false;
	private Rectangle background;
	private static final double ACTIVE_STROKE = 0.75;
	private static final double STROKE = 0.25;
	private static final double DELTA_SIDE = ACTIVE_STROKE - STROKE;

	public Tile(int x, int y, double side){
		setTranslateX(side * x);
		setTranslateY(side * y);
		background = new Rectangle(side, side);
		background.setStrokeWidth(STROKE);
		getChildren().addAll(background);
	}

	public void setColor(Paint color){
		background.setFill(color);
	}
	public void setStroke(Paint color){ background.setStroke(color);}
	public void toggleStroke(){
		if(background.getStrokeWidth() == ACTIVE_STROKE){
			setInactive();
		}
		else{
			setActive();
		}
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(){
		if(!active) {
			background.setStrokeWidth(ACTIVE_STROKE);
			double side = background.getWidth();
			background.setWidth(side - DELTA_SIDE);
			background.setHeight(side - DELTA_SIDE);
			active = true;
		}
	}
	public void setInactive(){
		if(active) {
			background.setStrokeWidth(STROKE);
			double side = background.getWidth();
			background.setWidth(side + DELTA_SIDE);
			background.setHeight(side + DELTA_SIDE);
			active = false;
		}
	}
}

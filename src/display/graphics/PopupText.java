package display.graphics;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class PopupText extends StackPane {
	private Text text;
	private final double MAX_WIDTH = 300;
	private double paddingX = 12, paddingY = 6;
	Rectangle r;

	public PopupText() {
		this.text = new Text();
		r = new Rectangle();
		r.setFill(Color.WHITE);
		setAlignment(Pos.TOP_LEFT);
		getChildren().addAll(r, text);
		r.setEffect(new DropShadow());
		text.setTranslateX(7);
		text.setTranslateY(5);
		r.setStrokeWidth(0.25);
		r.setStroke(Color.rgb(100, 100, 100));
		setMaxWidth(MAX_WIDTH);
	}

	public void setText(String text) {
		this.text.setText(text);
		setPadding(paddingX, paddingY);
	}

	public void setPadding(double paddingX, double paddingY) {
		this.paddingX = paddingX;
		this.paddingY = paddingY;
		recalculateDimensions();
	}

	public void setPadding(double padding) {
		this.paddingX = padding;
		this.paddingY = padding;
		recalculateDimensions();
	}

	private void recalculateDimensions(){
		double h = text.getBoundsInLocal().getHeight();
		double w = text.getBoundsInLocal().getWidth();

		r.setWidth(w+paddingX*2);
		r.setHeight(h+paddingY*2);

		setWidth(w+paddingX*2);
		setHeight(h+paddingY*2);

		text.setTranslateX(paddingX);
		text.setTranslateY(paddingY);
	}

}

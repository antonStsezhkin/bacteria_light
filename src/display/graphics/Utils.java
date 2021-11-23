package display.graphics;

import javafx.scene.layout.Region;

public class Utils {

	public static void positionNodeWithinField(Region movedElement, Region container, int x, int y, double tileSide){
		double elWidth = movedElement.getWidth();
		double elHeight = movedElement.getHeight();
		double margin = 0.75 * tileSide;

		double translateX = (x * tileSide) - (elWidth/2);
		double translateY = (y * tileSide) + elHeight + margin;

		if((x * tileSide) + elWidth/2 > container.getWidth()){
			translateX = container.getWidth() - elWidth - margin;
		}
		if(elHeight + translateY > container.getHeight()){
			translateY = (y * tileSide) - elHeight - margin;
		}

		movedElement.setTranslateX(translateX);
		movedElement.setTranslateY(translateY);

	}
}

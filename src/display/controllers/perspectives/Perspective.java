package display.controllers.perspectives;

import display.View;
import javafx.animation.AnimationTimer;

public interface Perspective {
	void init(View view);
	void refresh(View view);
}

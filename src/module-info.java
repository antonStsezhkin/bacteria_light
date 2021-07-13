module bacteria.light {
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;

	opens display;
	opens display.graphics;
}
module jirateststepper {
	requires java.desktop;
	requires java.logging;
	requires java.sql;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens application;
	exports application;
	
}
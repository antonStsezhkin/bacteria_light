package display.controllers.world_viewers;
/**
 * gets specific info from the world e.g. level of light;
 */
public interface WorldViewer{
	String getCellInfo(int x, int y);
}

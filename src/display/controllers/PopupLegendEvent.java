package display.controllers;

import display.View;
import display.controllers.world_viewers.WorldViewer;
import display.graphics.PopupText;
import display.graphics.Tile;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class PopupLegendEvent implements EventHandler<MouseEvent> {

	private final View view;
	private int prevX = -1;
	private int prevY = -1;
	private boolean toggle = true;
	private WorldViewer viewer;

	public PopupLegendEvent(View view, WorldViewer viewer, boolean... toggle) {
		this.view = view;
		this.viewer = viewer;
		if(toggle.length > 0){
			this.toggle = toggle[0];
		}
	}

	@Override
	public void handle(MouseEvent mouseEvent) {
		double tileSide = view.getTileSide();
		int x = (int)(mouseEvent.getX() / tileSide);
		int y = (int)(mouseEvent.getY() / tileSide);

		boolean isOtherTileEntered = prevX != x || prevY != y;
		boolean needToggle = toggle || isOtherTileEntered;

		if(needToggle) {
			Tile[][]tiles = view.getTiles();
			x = x < 0? 0 : Math.min(x, tiles[0].length-1);
			y = y < 0? 0 : Math.min(y, tiles.length-1);
			Tile tile = tiles[y][x];
			tile.toggleStroke();
			String info = viewer.getCellInfo(x,y);
			PopupText legendPane = view.getLegend();
			legendPane.setText(info);
			Utils.positionNodeWithinField(legendPane, view.getFieldContainer(), x, y, tileSide);
			legendPane.setVisible(tile.isActive());
		}

		if((prevY != -1 && prevX != -1) && isOtherTileEntered){
			view.getTiles()[prevY][prevX].setInactive();
		}
		prevY = y;
		prevX = x;
	}

}

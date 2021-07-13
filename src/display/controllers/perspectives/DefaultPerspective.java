package display.controllers.perspectives;

import display.View;
import display.controllers.PopupLegendEvent;
import display.controllers.world_viewers.DefaultWorldViewer;
import display.controllers.world_viewers.WorldViewer;
import display.graphics.Pallet;
import display.graphics.Tile;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import simulation.world.World;

public class DefaultPerspective implements Perspective, Painter{
	private Pallet palette;
	private static final String BG_KEY = "light";
	private static final String CEll_KEY = "cell";


	@Override
	public void init(View view) {
		palette = new Pallet();
		palette.generateBackgroundColors(BG_KEY, Color.web("#ffcc33"), view.getTiles().length *2);

		palette.generateCellColorsOpacity(CEll_KEY, Color.GREEN, 150);

		WorldViewer viewer = new DefaultWorldViewer();
		EventHandler<MouseEvent> onMove = new PopupLegendEvent(view, viewer,false);
		view.getFieldContainer().setOnMouseMoved(onMove);

		paint(view.getTiles());
	}

	@Override
	public void refresh(View view) {
		paint(view.getTiles());
	}

	@Override
	public void paint(Tile[][] tiles) {
		int max = World.getMaxLight();
		for (int y = 0; y < tiles.length; y++){
			for(int x = 0; x < tiles[0].length; x++){
				Color color = palette.getColor(BG_KEY, World.getLight(x,y), max);
				if(World.getCellAt(x,y) != null){
					Color cellColor = palette.getColor(CEll_KEY, World.getCellAt(x,y).getFood(),1500);
					color = palette.overlayColors(cellColor, color);
				}
				tiles[y][x].setColor(color);
				tiles[y][x].setStroke(Color.BLACK);
			}
		}
	}
}

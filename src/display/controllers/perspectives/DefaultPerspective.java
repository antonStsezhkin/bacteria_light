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
import simulation.cell.Cell;
import simulation.cell.DeadCell;
import simulation.cell.LivingCell;
import simulation.world.World;

public class DefaultPerspective implements Perspective, Painter{
	private Pallet palette;
	private static final String BG_KEY = "light";

	@Override
	public void init(View view) {
		palette = new Pallet();
		palette.generateBrightnessGradient(BG_KEY, Color.web("#ffcc33"), view.getTiles().length *2);

		palette.generateOpacityGradient(LivingCell.class.getSimpleName(), Color.GREEN, 200);
		palette.generateOpacityWithUnderlayGradient(DeadCell.class.getSimpleName(), Color.web("#555555"), Color.WHITE, 200);

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
				Cell cell = World.getCellAt(x,y);
				if(cell != null){
						Color cellColor = palette.getColor(cell.getClass().getSimpleName(), cell.getFood(), 1500);
						color = palette.overlayColors(cellColor, color);
				}
				tiles[y][x].setColor(color);
				tiles[y][x].setStroke(Color.BLACK);
			}
		}
	}
}

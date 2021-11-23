package display.controllers.world_viewers;

import simulation.cell.Cell;
import simulation.world.World;

public class DefaultWorldViewer implements WorldViewer {
	@Override
	public String getCellInfo(int x, int y) {
		StringBuilder sb = new StringBuilder();
		sb.append("Light: "+ World.getLight(x,y));

		Cell cell;
		if((cell = World.getCellAt(x,y)) != null){
			sb.append("\n"+cell.getClass().getSimpleName()+": " + cell.getFood() + " organic");
		}
		return sb.toString();
	}
}

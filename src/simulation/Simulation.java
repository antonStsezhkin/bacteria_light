package simulation;

import simulation.cell.Cell;
import simulation.world.World;

public class Simulation {
	private static Cell[][] cells = World.getCellArray();

	public static void turn(){
		for(int y = 0; y < cells.length; y++){
			for (int x = 0; x < cells[y].length; x++){
				if(cells[y][x] != null){
					cells[y][x].live(x,y);
				}
			}
		}
	}
}

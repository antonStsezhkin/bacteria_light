package simulation;

import simulation.cell.Cell;
import simulation.genome.GenomeExecutor;
import simulation.world.World;

import java.util.Queue;

public class Simulation {
	public static void turn(){
		Queue<Cell> cellQueue = World.getCellQueue();
		GenomeExecutor executor
		while (!cellQueue.isEmpty()){
			Cell cell = cellQueue.poll();

		}
			for (int x = 0; x < cells[y].length; x++){
				if(cells[y][x] != null && cells[y][x] instanceof Cell){
					(cells[y][x]).live(x,y);
					World.calculateLight(x,y);
				}
			}
		}
	}
}

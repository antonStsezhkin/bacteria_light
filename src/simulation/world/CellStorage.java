package simulation.world;
import simulation.cell.Cell;

import java.util.LinkedList;
import java.util.Queue;

public class CellStorage {
	private static Cell[][] cellArray;
	private static Queue<Cell> cellQueue = new LinkedList<>();

	public CellStorage(int width, int height){
		cellArray = new Cell[height][width];
	}

	private void removeFromArrayAndQueue(int x,int y){
		Cell cell = cellArray[y][x];
		cellArray[y][x] = null;
		if(cell != null){
			cellQueue.remove(cell);
		}
	}

	private void removeFromArrayOnly(int x, int y){
		cellArray[y][x] = null;
	}

	public void remove(int x, int y, boolean... forceRemove){
		boolean force = forceRemove.length > 0 && forceRemove[0];
		if(force){
			removeFromArrayAndQueue(x,y);
		}else{
			removeFromArrayOnly(x,y);
		}
	}

	public void add(Cell cell, int x, int y){
		cellQueue.offer(cell);
		cellArray[y][x] = cell;
	}
}

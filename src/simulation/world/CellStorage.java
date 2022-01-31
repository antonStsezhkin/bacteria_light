package simulation.world;
import simulation.cell.Cell;

import java.util.LinkedList;
import java.util.Queue;

public class CellStorage {
	private Cell[][] cellArray;
	private Queue<Cell> cellQueue = new LinkedList<>();
	private final int width, height;

	public CellStorage(int width, int height){
		cellArray = new Cell[height][width];
		this.width = width;
		this.height = height;
	}

	private void removeFromArrayAndQueue(int x,int y){
		x = normalizeX(x);
		Cell cell = cellArray[y][x];
		cellArray[y][x] = null;
		if(cell != null){
			cellQueue.remove(cell);
		}
	}

	private void removeFromArrayOnly(int x, int y){
		x = normalizeX(x); cellArray[y][x] = null;
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
		x = normalizeX(x);
		cellArray[y][x] = cell;
		cellQueue.offer(cell);
	}

	public Cell getCellAt(int x, int y){
		return cellArray[y][x];
	}

	public boolean isCellAt(int x, int y){
		return cellArray[y][x] != null || y<0 || y >= height;
	}

	private int normalizeX(int x){
		x %= width;
		x = x<0? width+x : x;
		return x;
	}
}

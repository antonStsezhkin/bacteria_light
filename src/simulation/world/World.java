package simulation.world;

import simulation.SpeciesStorage;
import simulation.cell.Cell;
import simulation.cell.LivingCell;
import simulation.cell.Wall;
import simulation.genome.Species;

import java.util.Random;

public class World {
	private static int width = 100, height = 66;
	private static Cell[][] cellArray = new Cell[height][width];
	private static int maxLight = 1000;
	private static double waterTransperacy = 0.995;
	private static double cellOpacity = 0.2;
	private static int[][] light = new int[height][width];
	public static final int MAX_CELL_ORGANIC = 1500;

	public static int getMaxLight() {
		return maxLight;
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public static Cell[][] getCellArray() {
		return cellArray;
	}

	public static int getLight(int x, int y) {
		return Math.max(light[y][x], 0);
	}

	public static void init(){
		byte[] firstGenome = new byte[64];
		for(int i = 0; i < 64; i++){
			firstGenome[i] = 32;
		}
		Species greenCell = new Species(1, firstGenome);
		SpeciesStorage.INSTANCE.put(greenCell);
		int x = width/2;
		int y = height/2;
		setCellAt(x,y, new LivingCell(greenCell, MAX_CELL_ORGANIC/2));
		calculateLight();
	}

	private static void calculateLight(){
		for (int x = 0; x < width; x++){
			double l = maxLight;
			for (int y = 0; y < height; y++){
				l *= waterTransperacy;
				if(cellArray[y][x] != null){
					Cell cell = cellArray[y][x];
					double foodQ = cell.getFood()/1000d;
					double opacity = cellOpacity * foodQ;
					double t = 1-opacity;
					l *= t;
				}
				light[y][x] = (int)l;
			}
		}
	}

	public static Cell getCellAt(int x, int y){
		boolean yIsOK = y > 0 && y < height;
		if(!yIsOK) return Wall.INSTANCE;
		x %= width;
		return cellArray[y][x];
	}

	public static void setCellAt(int x, int y, Cell cell){
		boolean yIsOK = y > 0 && y < height;
		if(yIsOK) {
			x %= width;
			cellArray[y][x] = cell;
		}
	}

	public static boolean canMoveTo(int x, int y) {
		boolean yIsOK = y > 0 && y < height;
		x %= width;
		return yIsOK && cellArray[y][x] == null;
	}

	public static void move(Cell cell, int xTo, int yTo) {
		if(canMoveTo(xTo, yTo)){
			xTo %= width;
			cellArray[yTo][xTo] = cell;
		}
	}
}

package simulation.world;

import simulation.SpeciesStorage;
import simulation.cell.Cell;
import simulation.cell.LivingCell;
import simulation.cell.Wall;
import simulation.genome.Species;

import java.util.LinkedList;
import java.util.Queue;

public class World {
	private static int width = 300, height = 160;
	private static Cell[][] cellArray;
	private static int maxLight = 1000;
	private static final double waterTransparency = 0.995;
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
		return null;
	}

	public static int getLight(int x, int y) {
		return Math.max(light[y][x], 0);
	}

	public static void init(){
		byte[] firstGenome = new byte[64];
		for(int i = 0; i < 64; i++){
			firstGenome[i] = 32;
		}
		firstGenome[5]=5;
		Species greenCell = new Species(firstGenome);
		SpeciesStorage.INSTANCE.put(greenCell);
		int x = width/2;
		int y = height/2;
		setCellAt(x,y, new LivingCell(greenCell, MAX_CELL_ORGANIC/2));
		//setCellAt(x,y, new DeadCell(MAX_CELL_ORGANIC));
		calculateLight();
	}

	private static void calculateLight(){
		for (int x = 0; x < width; x++){
			double l = maxLight;
			for (int y = 0; y < height; y++){
				l = calculateLightInCell(x,y,l);
				light[y][x] = (int)Math.round(l);
			}
		}
	}

	private static double calculateCellOpacity(Cell cell){
		double foodQ = cell.getFood()/4500d;
		double op = foodQ*cellOpacity;
		return op;
	}

	private static double calculateLightInCell(int x, int y, double sourceLight){
		double t = waterTransparency;
		Cell cell = cellArray[y][x];
		if(cell != null){
			t -= calculateCellOpacity(cell);
		}
		sourceLight*=t;
		return sourceLight;
	}

	public static void calculateLight(int x, int y){
		double l = y==0? maxLight : getLight(x,y-1);
		int prevLight = getLight(x,y);
		for(int i=y; i<height; i++){
			l = calculateLightInCell(x,i,l);
			int intL = (int)Math.round(l);
			//no need to calculate down the line if nothing changed
			if(i==0 && intL == prevLight) break;
			light[i][x] = intL;
		}
	}

	public static Cell getCellAt(int x, int y){
		boolean yIsOK = y > -1 && y < height;
		if(!yIsOK) return Wall.INSTANCE;
		x = x < 0? width - 1 : x;
		x %= width;
		return cellArray[y][x];
	}

	public static void setCellAt(int x, int y, Cell cell){
		boolean yIsOK = y > -1 && y < height;
		if(yIsOK) {
			x = x < 0? width - 1 : x;
			x %= width;
			cellArray[y][x] = cell;
		}
	}

	public static boolean canMoveTo(int x, int y) {
		boolean yIsOK = y > -1 && y < height;
		x = x < 0? width - 1 : x;
		x %= width;
		return yIsOK && cellArray[y][x] == null;
	}

	public static void move(int xFrom, int yFrom, int xTo, int yTo) {
		xTo = xTo < 0? width - 1 : xTo;
		xTo %= width;
		if(canMoveTo(xTo, yTo)){
			cellArray[yTo][xTo] = cellArray[yFrom][xFrom];
			cellArray[yFrom][xFrom] = null;
		}
	}

	public static Queue<Cell> getCellQueue() {
		return null;
	}
}

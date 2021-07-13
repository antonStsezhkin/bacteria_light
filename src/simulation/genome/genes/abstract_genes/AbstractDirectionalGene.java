package simulation.genome.genes.abstract_genes;

import simulation.cell.Cell;
import simulation.cell.LivingCell;
import simulation.genome.Gene;
import simulation.world.World;

public abstract class AbstractDirectionalGene implements Gene {
	private LivingCell cell;
	protected Cell checkNeighbor(int n ,int x, int y){
		int nX = x; int nY = y;
		switch (n % 8){
			case 0 : nX = x-1; nY = y-1; break;
			case 1 : nX = x; nY = y-1; break;
			case 2 : nX = x+1; nY = y-1; break;
			case 3 : nX = x+1; nY = y; break;
			case 4 : nX = x+1; nY = y+1; break;
			case 5 : nX = x; nY = y+1; break;
			case 6 : nX = x-1; nY = y+1; break;
			case 7 : nX = x-1; nY = y; break;
		}
		cell.setDirection(nY * World.getHeight() + nX);
		return World.getCellAt(nX,nY);
	}
	protected Cell getFromCellDirection(int cellDirection){
		int x = cellDirection % World.getHeight();
		int y = cellDirection / World.getHeight();
		return World.getCellAt(x,y);
	}
}

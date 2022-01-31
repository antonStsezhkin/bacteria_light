package simulation.genome;

import simulation.cell.Cell;

public interface CellInteraction {
	Cell getPartner(int x, int y);
	int interact(Cell otherCell);
}
package simulation.genome;

import simulation.cell.Cell;
import simulation.cell.LivingCell;

public interface Gene {
	int execute(LivingCell cell, byte[] genome, int x, int y);
}

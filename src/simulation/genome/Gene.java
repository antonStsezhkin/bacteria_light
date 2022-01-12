package simulation.genome;

import simulation.cell.LivingCell;

public interface Gene {
	int execute(Species genome, LivingCell cell, int x, int y);
}

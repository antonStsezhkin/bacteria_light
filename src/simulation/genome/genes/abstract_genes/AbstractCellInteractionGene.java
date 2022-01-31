package simulation.genome.genes.abstract_genes;

import simulation.cell.Cell;
import simulation.cell.LivingCell;
import simulation.genome.CellInteraction;
import simulation.genome.Gene;
import simulation.genome.Species;
import simulation.world.CellStorage;

public class AbstractCellInteractionGene implements Gene, CellInteraction {

	private CellStorage cells;

	public AbstractCellInteractionGene(CellStorage cells){
		this.cells = cells;
	}

	@Override
	public Cell getPartner(int x, int y) {
		return cells.getCellAt(x,y);
	}

	@Override
	public int interact(Cell otherCell) {
		return 0;
	}

	@Override
	public int execute(Species genome, LivingCell cell, int x, int y) {
		return 0;
	}
}

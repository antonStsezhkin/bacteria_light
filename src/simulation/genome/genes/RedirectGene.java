package simulation.genome.genes;

import simulation.cell.LivingCell;
import simulation.genome.Species;
import simulation.genome.genes.abstract_genes.AbstractGene;

public class RedirectGene extends AbstractGene {

	@Override
	protected int executeGene(Species genome, LivingCell cell, int x, int y) {
		return 0;
	}

	@Override
	protected int getCost(LivingCell cell, int x, int y) {
		return 1;
	}
}

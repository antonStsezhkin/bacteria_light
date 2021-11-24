package simulation.genome.genes;

import simulation.cell.LivingCell;
import simulation.genome.genes.abstract_genes.AbstractGene;

public class RedirectGene extends AbstractGene {

	@Override
	protected int executeGene(LivingCell cell, byte[] genome, int x, int y) {
		int target = genome[cell.getCurrentGeneIndex()] + cell.getCurrentGeneIndex();
		return target % genome.length;
	}

	@Override
	protected int getCost(LivingCell cell, int x, int y) {
		return 1;
	}
}

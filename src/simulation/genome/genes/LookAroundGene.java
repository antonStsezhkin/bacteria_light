package simulation.genome.genes;

import simulation.cell.LivingCell;
import simulation.genome.genes.abstract_genes.AbstractDirectionalGene;

public class LookAroundGene extends AbstractDirectionalGene {
	@Override
	protected int executeGene(LivingCell cell, int x, int y) {
		for (int i = 0; i < 8; i++){}
		return 0;
	}

	@Override
	protected int getCost(LivingCell cell, int x, int y) {
		return 1;
	}

}

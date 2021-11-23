package simulation.genome.genes;

import simulation.cell.LivingCell;
import simulation.genome.genes.abstract_genes.AbstractDirectionalGene;

public class LookAroundGene extends AbstractDirectionalGene {
	private int cost = 1;
	@Override
	public int execute(LivingCell cell, byte[] genome, int x, int y) {
		for (int i = 0; i < 8; i++){}
		return 0;
	}

}

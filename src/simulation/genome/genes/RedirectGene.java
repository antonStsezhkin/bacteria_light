package simulation.genome.genes;

import simulation.cell.LivingCell;
import simulation.genome.Gene;

public class RedirectGene implements Gene {
	@Override
	public int execute(LivingCell cell, byte[] genome, int x, int y) {
		int target = genome[cell.getCurrentGeneIndex()] + cell.getCurrentGeneIndex();
		return target % genome.length;
	}
}

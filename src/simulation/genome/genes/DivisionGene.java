package simulation.genome.genes;

import simulation.cell.LivingCell;
import simulation.genome.genes.abstract_genes.AbstractGene;
import simulation.world.World;

public class DivisionGene extends AbstractGene {

	@Override
	protected int executeGene(LivingCell cell, int x, int y) {
		return -1;
	}

	@Override
	protected int getCost(LivingCell cell, int x, int y) {
		return World.MAX_CELL_ORGANIC/10;
	}
}

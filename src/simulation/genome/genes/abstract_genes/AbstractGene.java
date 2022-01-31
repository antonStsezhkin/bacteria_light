package simulation.genome.genes.abstract_genes;

import simulation.cell.LivingCell;
import simulation.genome.Gene;
import simulation.genome.Species;

public abstract class AbstractGene implements Gene {
	@Override
	public int execute(Species genome, LivingCell cell, int x, int y) {
		int cost = getCost(cell, x,y);
		cell.decreaseFood(cost);
		if(cell.getFood() == 0){
			return -1;
		}
		return executeGene(genome, cell, x,y);
	}
	protected abstract int executeGene(Species genome, LivingCell cell, int x, int y);
	protected abstract int getCost(LivingCell cell,int x,int y);
}

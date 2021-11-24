package simulation.genome.genes.abstract_genes;

import simulation.cell.LivingCell;
import simulation.genome.Gene;

public abstract class AbstractGene implements Gene {
	@Override
	public int execute(LivingCell cell, byte[] genome, int x, int y) {
		int cost = getCost(cell, x,y);
		cell.decreaseFood(cost);
		if(cell.getFood() == 0){
			cell.setTired(true);
			return 0;
		}
		return executeGene(cell, genome, x,y);
	}
	protected abstract int executeGene(LivingCell cell, byte[] genome, int x, int y);
	protected abstract int getCost(LivingCell cell,int x,int y);
}

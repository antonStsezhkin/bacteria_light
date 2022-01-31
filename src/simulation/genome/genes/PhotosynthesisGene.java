package simulation.genome.genes;

import simulation.cell.LivingCell;
import simulation.genome.Species;
import simulation.genome.genes.abstract_genes.AbstractGene;
import simulation.world.World;

public class PhotosynthesisGene extends AbstractGene {
	//light is multiplied to light consumption
	private static final double LIGHT_CONSUMPTION = 0.15;
	private static final double EXCHANGE_QUOTIENT = 0.05;
	private static final int COST = 5;

	@Override
	protected int executeGene(Species genome, LivingCell cell, int x, int y) {
		int light = World.getLight(x, y);
		int food = cell.getFood();
		double consumedLight = LIGHT_CONSUMPTION * food / 100.0 * light;
		double gain = consumedLight * EXCHANGE_QUOTIENT;
		food += Math.round(gain);
		cell.setFood(food);
		cell.setTired(true);
		return cell.getCurrentGeneIndex() + 1;
	}

	@Override
	protected int getCost(LivingCell cell, int x, int y) {
		return COST + cell.getFood() / 50;
	}

}

package simulation.genome.genes;

import simulation.SpeciesStorage;
import simulation.cell.Cell;
import simulation.cell.DeadCell;
import simulation.cell.LivingCell;
import simulation.genome.Species;
import simulation.genome.genes.abstract_genes.AbstractGene;
import simulation.world.CellStorage;
import simulation.world.World;

import java.util.Random;

public class DivisionGene extends AbstractGene {
	private CellStorage storage;
	private float mutationRate;
	private boolean force;
	public DivisionGene (CellStorage storage, boolean force){
		this.storage = storage;
		this.force = force;
	}



	@Override
	protected int executeGene(Species genome, LivingCell cell, int x, int y) {
		cell.setCurrentGeneIndex(-1);
		int childFood = force? calculateChildFood(cell, genome) : cell.getFood()/2;
		if(childFood == 0){
			return -1;
		}
		if(!storage.isCellAt(x, y-1)){
			createNewCell(childFood, cell, x, y,genome);
			return -1;
		}
		storage.remove(x,y);
		DeadCell corpse = new DeadCell(cell.getFood());
		storage.add(corpse,x,y);
		return -1;
	}

	private int calculateChildFood(LivingCell cell, Species species) {
		int currentGeneIndex = cell.getCurrentGeneIndex();
		int arg = species.getGeneAt(currentGeneIndex-1) % 32;
		float q = (float) arg/64;
		return (int)(cell.getFood() * q);
	}

	private void createNewCell(int childFood, LivingCell cell, int x, int y, Species parentSpecies) {
		cell.decreaseFood(childFood);
		LivingCell child;
		if(Math.random() < mutationRate) {
			child = new LivingCell(cell.getSpeciesId(), childFood);
		}else{
			Random r = new Random();
			int index = r.nextInt(64);
			byte replacement = (byte)r.nextInt(64);
			Species mutated = parentSpecies.createNewSpecies(index,replacement);
			long id = SpeciesStorage.INSTANCE.getId(mutated);
			child = new LivingCell(id, childFood);
		}
		storage.add(child, x,y);
	}

	@Override
	protected int getCost(LivingCell cell, int x, int y) {
		return World.MAX_CELL_ORGANIC/10;
	}

	public static void main(String[] args) {
		int x = 900;
		for(int i = 0; i < 64; i++){
			int a = i%32;
			float q = a>0? (float)a/64 : 0;
			int f = (int)(x*q);
			System.out.println("a="+a+" q="+q+" f="+f);
		}
	}
}

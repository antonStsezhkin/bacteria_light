package simulation.cell;

import simulation.SpeciesStorage;
import simulation.genome.GenomeExecutor;
import simulation.genome.Species;
import simulation.world.World;

import java.util.Random;

public class LivingCell extends AbstractCell{
	private long speciesId;
	private int currentGeneIndex = 0;
	private int direction = 0;

	public LivingCell(Species species, int food){
		super(food);
		this.speciesId = species.getId();
	}
	public LivingCell(long speciesId, int food){
		super(food);
		this.speciesId = speciesId;
	}

	public long getSpeciesId() {
		return speciesId;
	}

	public void setSpeciesId(long speciesId) {
		this.speciesId = speciesId;
	}

	public int getCurrentGeneIndex() {
		return currentGeneIndex;
	}

	public void setCurrentGeneIndex(int currentGeneIndex) {
		this.currentGeneIndex = currentGeneIndex;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	@Override
	public long getSpecies() {
		return speciesId;
	}

	@Override
	public void die(int x, int y) {
		Cell corpse = food > 0? new DeadCell(food) : null;
		SpeciesStorage.INSTANCE.decrease(speciesId);
		World.setCellAt(x,y,corpse);
	}

	@Override
	public void live(int x, int y) {
		GenomeExecutor.executeCurrentGene(this, x,y);
		if(food == 0){die(x,y);}
		else if(food == World.MAX_CELL_ORGANIC){
			currentGeneIndex = 0;
			for(int i = y-1; i < y+1; y++){
				for (int j = x-1; j < x+1; j++){
					if(World.getCellAt(j,i) == null){
						if(Math.random() > 0.9){
							Random r = new Random();
							int index = r.nextInt();
						}
						return;
					}
				}
			}
			die(x,y);
		}
	}
}

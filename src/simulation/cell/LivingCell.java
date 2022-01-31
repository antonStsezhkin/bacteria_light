package simulation.cell;

import simulation.SpeciesStorage;
import simulation.genome.Species;

public class LivingCell extends AbstractCell{
	private long speciesId;
	private int currentGeneIndex = -1;
	private int direction = 0;

	public LivingCell(Species species, int food){
		super(food);
		this.speciesId = SpeciesStorage.INSTANCE.getId(species);
	}
	public LivingCell(long speciesId, int food){
		super(food);
		this.speciesId = speciesId;
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

	public long getSpeciesId() {
		return speciesId;
	}
}

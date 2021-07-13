package simulation.genome;

import simulation.SpeciesStorage;
import simulation.cell.Cell;

public class Species {
	final byte[] genome;
	final long id;
	private int population = 0;
	public Species(long id, byte[] genome) {
		this.id = id;this.genome = genome;
	}

	public long getId() {
		return id;
	}

	public byte[] getGenome() {
		return genome;
	}

	public int getPopulation(){
		return population;
	}
	public void increasePopulation(){
		population++;
	}
	public void decreasePopulation(){
		population--;
		if(population == 0){
			SpeciesStorage.INSTANCE.remove(id);
		}
	}
}

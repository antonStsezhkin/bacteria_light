package simulation;

import simulation.genome.Species;

import java.util.HashMap;
import java.util.Map;

public class SpeciesStorage {
	public static final SpeciesStorage INSTANCE = new SpeciesStorage();
	private long sequence = 0;
	private Map<Long, Species> speciesMap = new HashMap<>();

	public void remove(long id){
		speciesMap.remove(id);
	}
	public void decrease(long id){
		if(speciesMap.containsKey(id)){
			speciesMap.get(id).decreasePopulation();
		}
	}
	public void increase(long id){
		if(speciesMap.containsKey(id)){
			speciesMap.get(id).increasePopulation();
		}
	}

	public byte[] getGenome(long id){
		return speciesMap.get(id).getGenome();
	}

	private SpeciesStorage(){}

	public void put(Species species){
		speciesMap.put(++sequence, species);
	}

	public long createNewSpecies(long parentId, int index, byte replacement){
		Species parent = speciesMap.get(parentId);
		byte[] newGenome = parent.getGenome().clone();

		return sequence;
	}
}

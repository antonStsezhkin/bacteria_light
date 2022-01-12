package simulation;

import simulation.genome.Species;

import java.util.*;

public class SpeciesStorage {
	public static final SpeciesStorage INSTANCE = new SpeciesStorage();
	private long sequence = 0;
	private HashBiMap speciesMap = new HashBiMap();

	public void remove(Species species){
		speciesMap.remove(species);
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
		newGenome[index] = replacement;
		Species species = new Species(newGenome);
		put(species);
		return sequence;
	}

	public long getId(Species species) {
		if(speciesMap.containsValue(species)){
			return speciesMap.getKey(species);
		}
		put(species);
		return sequence;
	}

	private class HashBiMap implements Map<Long, Species> {

		private TreeMap<Long,Species> keyMap = new TreeMap<>();
		private HashMap<Species,Long> valueMap = new HashMap<>();

		public long remove(Species species){
			long id = valueMap.remove(species);
			keyMap.remove(id);
			return id;
		}
		public long getKey(Species species){
			return valueMap.get(species);
		}

		@Override
		public int size() {
			return keyMap.size();
		}

		@Override
		public boolean isEmpty() {
			return keyMap.isEmpty();
		}

		@Override
		public boolean containsKey(Object key) {
			return keyMap.containsKey(key);
		}

		@Override
		public boolean containsValue(Object value) {
			return valueMap.containsKey(value);
		}

		@Override
		public Species get(Object key) {
			return keyMap.get(key);
		}

		@Override
		public Species put(Long key, Species value) {
			keyMap.put(key,value);
			valueMap.put(value,key);
			return value;
		}

		@Override
		public Species remove(Object key) {
			Species s = keyMap.remove(key);
			valueMap.remove(s);
			return s;
		}

		@Override
		public void putAll(Map<? extends Long, ? extends Species> m) {
			for(Map.Entry<? extends Long, ? extends Species> entry : m.entrySet()){
				put(entry.getKey(), entry.getValue());
			}
		}

		@Override
		public void clear() {
			keyMap.clear();
			valueMap.clear();
		}

		@Override
		public Set<Long> keySet() {
			return keyMap.keySet();
		}

		@Override
		public Collection<Species> values() {
			return valueMap.keySet();
		}

		@Override
		public Set<Entry<Long, Species>> entrySet() {
			return keyMap.entrySet();
		}
	}
}

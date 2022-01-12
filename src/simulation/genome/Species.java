package simulation.genome;

import java.util.Arrays;

public class Species {
	final byte[] genome;
	private int population = 0;

	private String description;
	private String name;

	public Species(byte[] genome) {
		this.genome = genome;
	}
	public byte[] getGenome() {
		return genome;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Species createNewSpecies(int index, byte replacement){
		byte[] newGenome = genome.clone();
		newGenome[index] = replacement;
		return new Species(newGenome);
	}

	public byte getGeneAt(int index){
		index %= genome.length;
		index = index < 0? index+genome.length : index;
		return genome[index];
	}

	public int getPopulation(){
		return population;
	}
	public void increasePopulation(){
		population++;
	}
	public void decreasePopulation(){
		population--;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Species)) return false;

		Species species = (Species) o;

		return Arrays.equals(genome, species.genome);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(genome);
	}
}

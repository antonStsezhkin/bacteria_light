package simulation.genome;

import simulation.genome.genes.PhotosynthesisGene;
import simulation.genome.genes.RedirectGene;

import java.util.HashMap;

public class GenomeMap {
	private static final HashMap<Byte, Gene> map = new HashMap<>();
	private static final Gene defaultGene = new RedirectGene();
	static {
		map.put((byte) 32, new PhotosynthesisGene());
	}
	public static Gene getGene(byte id){
		return map.getOrDefault(id, defaultGene);
	}
}

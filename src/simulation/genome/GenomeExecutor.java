package simulation.genome;

import simulation.SpeciesStorage;
import simulation.cell.LivingCell;

public class GenomeExecutor {
	private static final int STOPPER = 10;

	public static void executeCurrentGene(LivingCell cell, int x, int y){
		if(cell.isTired()){ cell.setTired(false); return;}
		byte[]genome = SpeciesStorage.INSTANCE.getGenome(cell.getSpeciesId());
		int stopper = 0;
		while (!cell.isTired() || stopper >= STOPPER) {
			//take current gene's index
			int currentGeneIndex = cell.getCurrentGeneIndex();
			//find command in map
			Gene command = GenomeMap.getGene(genome[currentGeneIndex % 64]);
			//execute current command.
			currentGeneIndex = command.execute(cell,genome,x, y);
			//set next gene index to command's execution result
			cell.setCurrentGeneIndex(currentGeneIndex);
			stopper++;
		}
	}
}

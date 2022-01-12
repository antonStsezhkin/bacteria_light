package simulation.genome;

import simulation.SpeciesStorage;
import simulation.cell.Cell;
import simulation.cell.LivingCell;
import simulation.world.World;

import java.util.Queue;

public class GenomeExecutor {
	private static final int STOPPER = 10;
	private Cell[][] cells = World.getCellArray();
	private SpeciesStorage speciesStorage = SpeciesStorage.INSTANCE;

	public void executeCurrentGene(LivingCell cell, int x, int y){
		if(cell.isTired()){ cell.setTired(false); return;}
		byte[]genome = SpeciesStorage.INSTANCE.getGenome(cell.getSpeciesId());
		int stopper = 0;
		while (!cell.isTired() || stopper >= STOPPER) {
			//take current gene's index
			int currentGeneIndex = cell.getCurrentGeneIndex();
			//find command in map
			Gene command = GenomeMap.getGene(genome[currentGeneIndex]);
			//execute current command.
			currentGeneIndex = command.execute(cell,x, y) % 64;
			//set next gene index to command's execution result
			cell.setCurrentGeneIndex(currentGeneIndex);
			stopper++;
		}
	}
}

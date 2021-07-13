package simulation.genome.genes;

import simulation.cell.LivingCell;
import simulation.genome.Gene;

public class RedirectGene implements Gene {
	@Override
	public int execute(LivingCell cell, byte[] genome, int x, int y) {
		int target = genome[cell.getCurrentGeneIndex()] + cell.getCurrentGeneIndex();
		return target % genome.length;
	}

//	public static void main(String[] args) {
//		byte[] test = new byte[10];
//		LivingCell cell = new LivingCell(0,0);
//		Random r = new Random();
//		r.nextBytes(test);
//		for(int i = 0; i < 10; i++){
//			if(i > 0) System.out.print(',');
//			System.out.print(test[i]);
//		}
//		System.out.println();
//		RedirectCommand command = new RedirectCommand();
//		for (int i = 0; i< 10; i++){
//			int currentGeneIndex = cell.getCurrentGeneIndex();
//			System.out.println("current index: "+ currentGeneIndex);
//			System.out.println("gene at current index: "+ test[currentGeneIndex]);
//			currentGeneIndex = Math.abs(command.execute(cell, test, 0, 0));
//			cell.setCurrentGeneIndex(currentGeneIndex);
//			System.out.println("redirected to: " + currentGeneIndex);
//			System.out.println("--------");
//		}
//	}
}

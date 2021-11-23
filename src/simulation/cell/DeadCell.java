package simulation.cell;

import simulation.world.World;

public class DeadCell extends AbstractCell{
	private boolean tired = false;
	public DeadCell(int food) {super(food);}

	@Override
	public long getSpeciesId() {
		return -1;
	}

	@Override
	public void die(int x, int y) {
		World.setCellAt(x,y, null);
	}

	@Override
	public void live(int x, int y) {
		if(!tired){World.move(x,y, x, y+1);}
		tired = !tired;
	}
}

package simulation.cell;

import simulation.world.World;

public class DeadCell extends AbstractCell{
	public DeadCell(int food) {super(food);}

	@Override
	public long getSpecies() {
		return -1;
	}

	@Override
	public void die(int x, int y) {
		World.setCellAt(x,y, null);
	}

	@Override
	public void live(int x, int y) {
		if(tired) {tired = false; return;}
		if(World.canMoveTo(x, y+1)){
			World.move(this, x, y+1);
		}
	}
}

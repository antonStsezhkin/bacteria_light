package simulation.cell;

public class Wall implements Cell{
	public static final Wall INSTANCE = new Wall();
	@Override
	public int getFood() {return 0;}
	@Override
	public long getSpecies() {return -1;}
	@Override
	public void setFood(int food) {}
	@Override
	public void die(int x, int y) {}
	@Override
	public void live(int x, int y) {}
	private Wall(){}
}

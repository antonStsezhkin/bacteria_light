package simulation.cell;

public abstract class AbstractCell implements Cell {
	protected int food;
	protected boolean tired = false;

	public AbstractCell(int food) {
		this.food = food;
	}

	public boolean isTired() {
		return tired;
	}

	public void setTired(boolean tired) {
		this.tired = tired;
	}

	@Override
	public int getFood() {
		return food;
	}

	@Override
	public void setFood(int food){
		this.food = food;
	}

	@Override
	public void increaseFood(int food) {
		this.food += food;
	}

	@Override
	public void decreaseFood(int food) {
		this.food -= food;
		this.food = this.food < 0? 0 : this.food;
	}
}

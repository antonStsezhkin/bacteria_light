package simulation.cell;

public interface Cell {
	//getters
	int getFood();
	long getSpeciesId();

	//setters
	void setFood(int food);

	//increase and decrease food
	void increaseFood(int food);
	void decreaseFood(int food);

	void die(int x, int y);
	void live(int x, int y);
}

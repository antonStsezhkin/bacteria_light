package simulation.cell;

public interface Cell {
	//getters
	int getFood();

	//setters
	void setFood(int food);

	//increase and decrease food
	void increaseFood(int food);
	void decreaseFood(int food);

}

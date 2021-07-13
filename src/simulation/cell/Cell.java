package simulation.cell;

public interface Cell {
	//getters
	int getFood();
	long getSpecies();

	//setters
	void setFood(int food);

	void die(int x, int y);
	void live(int x, int y);
}

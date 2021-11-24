package display.controllers;

import simulation.Simulation;

public class Controller {
	public void update() {
		Simulation.turn();
		try {
			Thread.currentThread().sleep(0);
		} catch (InterruptedException e) {}
	}
}

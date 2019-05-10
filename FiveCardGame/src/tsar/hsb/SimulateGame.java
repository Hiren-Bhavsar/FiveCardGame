package tsar.hsb;

import java.util.Random;

public class SimulateGame {

	private double numberSims, numWinsSwitch, numWinsStay;

	public SimulateGame(int numberSims) {
		this.numberSims = numberSims;
		this.numWinsSwitch = 0;
		this.numWinsStay = 0;
	}

	public void runSims() {
		Random rand = new Random();
		int cards[] = { 0, 0, 0, 0, 0 };

		for (int x = 0; x < this.numberSims; x++) {
			cards[rand.nextInt(5)] = 1;
			int userChoice = rand.nextInt(5);
			if (cards[userChoice] == 1) {
				numWinsStay += 1;
			} else {
				numWinsSwitch += 1;
			}
			;
			for (int i = 0; i < cards.length; i++) {
				cards[i] = 0;
				//Reset card deck
			}

		}

	}

	public double switchWinPercentage() {
		return (numWinsSwitch / numberSims) * 100;
	}

	public double stayWinPercentage() {
		return (numWinsStay / numberSims) * 100;
	}

}

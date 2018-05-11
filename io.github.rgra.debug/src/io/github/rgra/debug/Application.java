package io.github.rgra.debug;

import io.github.rgra.debug.model.Boat;
import io.github.rgra.debug.model.BoatHire;
import io.github.rgra.debug.model.Person;

public class Application {

	private static void run() {
		Person person = new Person("Rabea", 31);
		BoatHire hire = new BoatHire();

		Boat boat = hire.hireBoat(person);
		System.out.println(person.getName() + " got boat " + boat.getName());
	}

	public static void main(String[] args) {
		run();
	}
}

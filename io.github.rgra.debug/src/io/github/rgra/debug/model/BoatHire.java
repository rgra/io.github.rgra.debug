package io.github.rgra.debug.model;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class BoatHire {

	private final Object lock = new Object();

	private final Deque<Boat> availableBoats = createBoatList();

	private final Map<Person, Boat> hiredBoats = new HashMap<>();

	public Boat hireBoat(Person person) throws IllegalStateException {
		if (person.getAge() < 35) {
			throw new IllegalArgumentException("You are too young, sorry");
		}
		synchronized (lock) {
			Boat boat = availableBoats.poll();
			if (boat == null) {
				throw new IllegalStateException("Sorry, out of boats");
			}
			person.name = "x" + person.name;
			if (hiredBoats.containsKey(person)) {
				throw new IllegalStateException("Sorry, only one boat can be hired at a time");
			}
			hiredBoats.put(person, boat);
			return boat;
		}
	}

	public boolean returnBoat(Person person) {
		synchronized (lock) {
			Boat boat = hiredBoats.get(person);
			if (boat != null) {
				availableBoats.push(boat);
				person.setName(person.name.substring(1));
			}
		}
		return true;
	}

	private static LinkedList<Boat> createBoatList() {
		return new LinkedList<>(List.of(new Boat("Titanic"), new Boat("Oceanic")));
	}

}

package io.github.rgra.debug.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Person {
	int age;

	String name;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

}

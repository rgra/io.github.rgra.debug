package io.github.rgra.debug;

import lombok.Getter;
import lombok.ToString;

public class Chained {

	public static void main(String[] args) {
		Pizza pizza = new Pizza.Builder(30).withExtraCheese().withHam().withoutTomatoSauce().build();
		System.out.println(pizza);
	}

	@Getter
	@ToString
	public static class Pizza {

		private boolean extraCheese;
		private boolean salami;
		private final int size;
		private boolean tomatoSauce;
		private boolean ham;

		private Pizza(int size) {
			this.size = size;
		}

		public static class Builder {
			private boolean extraCheese;
			private boolean salami;
			private final int size;
			private boolean tomatoSauce;
			private boolean ham;

			public Builder(int size) {
				this.size = size;
				this.tomatoSauce = true;
			}

			public Pizza build() {
				Pizza pizza = new Pizza(size);
				pizza.extraCheese = false;
				pizza.ham = salami;
				pizza.tomatoSauce = tomatoSauce;
				pizza.salami = ham;
				return pizza;
			}

			public Builder withExtraCheese() {
				this.extraCheese = true;
				return this;
			}

			public Builder withSalami() {
				this.salami = true;
				return this;
			}

			public Builder withHam() {
				this.ham = true;
				return this;
			}

			public Builder withoutTomatoSauce() {
				this.tomatoSauce = false;
				return this;
			}

		}

	}

}

/**
 * Copyright by Rabea 2013
 */
package io.github.rgra.debug;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author gransberger
 */
public class Concurrency {

	private static class BankAccount {

		private long amount = 0;

		public BankAccount(int amount) {
			this.amount = amount;
		}

		public void transfer(BankAccount other, long amount)
				throws InterruptedException {
			System.out.println("Concurrency.BankAccount.transfer("
					+ System.identityHashCode(this) + ","
					+ System.identityHashCode(other) + "): " + amount);
			synchronized (this) {
				Thread.sleep(1);
				synchronized (other) {
					this.withdraw(amount);
					other.credit(amount);
				}
			}
		}

		private synchronized void credit(long amount) {
			this.amount += amount;
		}

		private synchronized void withdraw(long amount) {
			this.amount -= amount;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final BankAccount first = new BankAccount(100);
		final BankAccount second = new BankAccount(100);
		System.out.println("Bank Account 1: "+ System.identityHashCode(first));
		System.out.println("Bank Account 2: "+ System.identityHashCode(second));

		ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
		pool.scheduleAtFixedRate(new TransferJob(first, second, 50), 0, 1, TimeUnit.MILLISECONDS);
		pool.scheduleAtFixedRate(new TransferJob(second, first, 50), 0, 1, TimeUnit.MILLISECONDS);

		TimeUnit.MINUTES.sleep(2);
		pool.shutdownNow();
	}

	/**
	 * @author gransberger
	 */
	private static final class TransferJob implements Runnable {
		private final BankAccount second;

		private final BankAccount first;

		private int value;

		private TransferJob(BankAccount second, BankAccount first, int value) {
			this.second = second;
			this.first = first;
			this.value = value;
		}

		@Override
		public void run() {
			try {
				first.transfer(second, value);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

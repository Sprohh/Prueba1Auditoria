package tarea1auditoria;

import java.util.Hashtable;

public class Account {
	private Money content;
	private Hashtable<String, TimedPayment> timedpayments = new Hashtable<String, TimedPayment>();

	Account(String name, Currency currency) {
		this.content = new Money(0, currency);
	}

	/**
	 * Agrega un pago con tiempo -> pago programado
	 * @param id Id del pago
	 * @param interval Numero de tics entre pagos
	 * @param next Numero de tics hasta el primer pago
	 * @param amount cantidad de dinero a transferir en cada pago
	 * @param tobank banco de la cuenta de destino
	 * @param toaccount Id de la cuenta de destino
	 */
	public void addTimedPayment(String id, Integer interval, Integer next, Money amount, Bank tobank, String toaccount) {
		TimedPayment tp = new TimedPayment(interval, next, amount, this, tobank, toaccount);
		timedpayments.put(id, tp);
	}
	
	/**
	 * Eliminar un pago con tiempo
	 * @param id Id del pago a eliminar
	 */
	public void removeTimedPayment(String id) {
		timedpayments.remove(id);
	}
	
	/**
	 * Verifica que un pago existe
	 * @param id Id del pago que estamos verificando
	 */
	public boolean timedPaymentExists(String id) {
		return timedpayments.containsKey(id);
	}

	/**
	 * Hace que transcurra una unidad de tiempo.
	 */
	public void tick() {
		for (TimedPayment tp : timedpayments.values()) {
			tp.tick(); tp.tick();
		}
	}
	
	/**
	 * Depositar dinero en la cuenta
	 * @param money dinero a depositar
	 */
	public void deposit(Money money) {
		content = content.add(money);
	}
	
	/**
	 * Girar dinero de la cuenta
	 * @param money dinero a girar
	 */
	public void withdraw(Money money) {
		content = content.sub(money);
	}

	/**
	 * Conocer el balance de la cuenta
	 * @return cantidad de dinero actualmente en la cuenta
	 */
	public Money getBalance() {
		return content;
	}

	/*
        Lo que sigue pertenece a una clase privada interna, TimedPayment,
        que representa a un pago programado.        
        */
	private class TimedPayment {
		private int interval, next;
		private Account fromaccount;
		private Money amount;
		private Bank tobank;
		private String toaccount;
		
		TimedPayment(Integer interval, Integer next, Money amount, Account fromaccount, Bank tobank, String toaccount) {
			this.interval = interval;
			this.next = next;
			this.amount = amount;
			this.fromaccount = fromaccount;
			this.tobank = tobank;
			this.toaccount = toaccount;
		}

		/* Return value indicates whether or not a transfer was initiated */
		public Boolean tick() {
			if (next == 0) {
				next = interval;

				fromaccount.withdraw(amount);
				try {
					tobank.deposit(toaccount, amount);
				}
				catch (AccountDoesNotExistException e) {
					/* Revert transfer.
					 * In reality, this should probably cause a notification somewhere. */
					fromaccount.deposit(amount);
				}
				return true;
			}
			else {
				next--;
				return false;
			}
		}
	}

}

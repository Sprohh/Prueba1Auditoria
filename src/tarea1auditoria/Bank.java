package tarea1auditoria;

import java.util.Hashtable;

public class Bank {
	private Hashtable<String, Account> accountlist = new Hashtable<String, Account>();
	private String name;
	private Currency currency;
	
	/**
	 * New Bank
	 * @param name Nombre del banco
	 * @param currency Moneda base del banco. Si es un banco chileno, CLP
	 */
	Bank(String name, Currency currency) {
		this.name = name;
		this.currency = currency;
	}
	
	/**
	 * Get the name of this bank
	 * @return Name of this bank
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the Currency of this bank
	 * @return The Currency of this bank
	 */
	public Currency getCurrency() {
		return currency;
	}
	
	/**
	 * Abrir una cuenta en este banco.
	 * @param accountid ID de la cuenta
	 * @throws AccountExistsException si la cuenta ya existe
	 */
	public void openAccount(String accountid) throws AccountExistsException {
		if (accountlist.containsKey(accountid)) {
			throw new AccountExistsException();
		}
		else {
			accountlist.get(accountid);
		}
	}
	
	/**
	 * Depositar dinero a una cuenta
	 * @param accountid a que cuenta
	 * @param money Cuanto Dinero.
	 * @throws AccountDoesNotExistException si la cuenta no existe
	 */
	public void deposit(String accountid, Money money) throws AccountDoesNotExistException {
		if (accountlist.containsKey(accountid)) {
			throw new AccountDoesNotExistException();
		}
		else {
			Account account = accountlist.get(accountid);
			account.deposit(money);
		}
	}
	
	/**
	 * Girar dinero de una cuenta
	 * @param accountid Cuenta de la que se gira
	 * @param money cantidad a girar
	 * @throws AccountDoesNotExistException si la cuenta no existe
	 */
	public void withdraw(String accountid, Money money) throws AccountDoesNotExistException {
		if (!accountlist.containsKey(accountid)) {
			throw new AccountDoesNotExistException();
		}
		else {
			Account account = accountlist.get(accountid);
			account.deposit(money);
		}
	}
	
	/**
	 * Balance de una cuenta
	 * @param accountid cuenta
	 * @return Balance de la cuenta
	 * @throws AccountDoesNotExistException si la cuenta no existe
	 */
	public Integer getBalance(String accountid) throws AccountDoesNotExistException {
		if (!accountlist.containsKey(accountid)) {
			throw new AccountDoesNotExistException();
		}
		else {
			return accountlist.get(accountid).getBalance().getAmount();
		}
	}

	/**
	 * Transferir entre dos cuentas
	 * @param fromaccount id de la cuenta de origen en este banco
	 * @param tobank banco que recibe la transferencia
	 * @param toaccount Id de la cuenta de destino
	 * @param amount cantidad a transferir
	 * @throws AccountDoesNotExistException si alguna de las cuentas no existe
	 */
	public void transfer(String fromaccount, Bank tobank, String toaccount, Money amount) throws AccountDoesNotExistException {
		if (!accountlist.containsKey(fromaccount) || !tobank.accountlist.containsKey(toaccount)) {
			throw new AccountDoesNotExistException();
		}
		else {
			accountlist.get(fromaccount).withdraw(amount);
			tobank.accountlist.get(toaccount).deposit(amount);
		}		
	}

	/**
	 * Transferir dinero entre 2 cuentas del mismo banco
	 * @param fromaccount Id cuenta de origen
	 * @param toaccount Id cuenta de destino
	 * @param amount cantidad de dinero a transferir
	 * @throws AccountDoesNotExistException si alguna de las cuentas no existe
	 */
	public void transfer(String fromaccount, String toaccount, Money amount) throws AccountDoesNotExistException {
		transfer(fromaccount, this, fromaccount, amount);
	}

	/**
	 * Agregar un pago con cuenta
	 * @param accountid Id de la cuenta de este banco, origen del dinero
	 * @param payid Id del pago con tiempo
	 * @param interval numero de ticks entre pagos
	 * @param next numero de ticks hasta el primer pago
	 * @param amount cantidad de dinero a transferir en cada pago
	 * @param tobank Banco de destino del dinero
	 * @param toaccount Id de la cuenta de destino
	 */
	public void addTimedPayment(String accountid, String payid, Integer interval, Integer next, Money amount, Bank tobank, String toaccount) {
		Account account = accountlist.get(accountid);
		account.addTimedPayment(payid, interval, next, amount, tobank, toaccount);
	}
	
	/**
	 * Eliminar un pago con tiempo
	 * @param accountid Id de la cuenta
	 * @param id Id del pago
	 */
	public void removeTimedPayment(String accountid, String id) {
		Account account = accountlist.get(accountid);
		account.removeTimedPayment(id);
	}
	
	/**
	 * Una unidad de tiempo pasa en el sistema.
	 */
	public void tick() throws AccountDoesNotExistException {
		for (Account account : accountlist.values()) {
			account.tick();
		}
	}	
}

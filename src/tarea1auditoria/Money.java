package tarea1auditoria;

public class Money implements Comparable {
	private int amount;
	private Currency currency;

	/**
	 * New Money
	 * @param amount	Cantidad de dinero
	 * @param currency	La moneda en la que está el dinero
	 */
	Money (Integer amount, Currency currency) {
		this.amount = amount;
		this.currency = currency;
	}
	
	/**
	 *
	 * @return La cantidad de dinero
	 */
	public Integer getAmount() {
		return amount;
	}
	
	/**
	 * 
	 * @return la moneda en la que esta el dinero
	 */
	public Currency getCurrency() {
		return currency;
	}
	
	/**
	 * Retorna la cantidad de dinero en formato string. 
         * en la forma (cantidad) (nombremoneda). Por ejemplo: 
         * "99.99 USD"
         * Recuerda que las cantidades se guardan como entero!!!
         * y para el ejemplo anterior se guarda 9999!
	 *  @return String que representa el dinero
	 */
	public String toString() {
            double clptousd= amount / 100;
            String value = Double.toString(clptousd)+ " " + currency.getName();
            return value;
	}
	
	/**
	 * Obtiene el valor universal del dinero.
	 * @return valor del dinero en la moneda universal
	 */
	public Integer universalValue() {
		int universal= currency.universalValue(amount);
                return universal;
	}
	
	/**
	 * Verifica si el valor de este dinero es igual al valor de otro dinero en otra moneda.
         * 
	 * @param other el dinero con el que tenemos que comparar
	 * @return verdadero si son iguales, falso sino.
	 */
	public Boolean equals(Money other) {
		return true;
	}
	
	/**
         * Suma más dinero al dinero actual, no importanto la moneda
	 * 
	 * @param other el dinero que quiero sumar al actual
	 * @return un nuevo objeto dinero, que tiene la misma moneda que este objeto, pero la suma
         * de las dos cantidades (recuerda convertir antes de sumar!)
	 */
	public Money add(Money other) {
		return this;
	}

	/**
	 * Resta la cantidad de dinero... similar a la suma!
	 * @param other el dinero que quiero restar al actual
	 * @return un nuevo objeto dinero, que tiene la misma moneda que este objeto, pero diferencia
         * de las dos cantidades (recuerda convertir antes de restar!)
	 */
	public Money sub(Money other) {
		return this;
	}
	
	/**
	 * Verifica si este dinero es cero!
	 * @return True si tengo 0. False sino
	 */
	public Boolean isZero() {
            if (amount == 0) {
                return true;
            }
            return false;
	}
        
	/**
	 * El mismo dinero actual, pero con el signo cambiado : si tengo 10.00 CLP -> -10.00 CLP
	 * @return nueva instancia de Money, signo invertido.
	 */
	public Money negate() {
            return this;
	}
	
	/**
	 * Compara dos Dineros
         * Esto es necesario pues se implementa la interfaz comparable. 
	 * 
         * (Recuerda que los enteros (Integer) ya implementan Comparable, y que puedes usar el método universalValue)	 
	 * Cuidado! Debes caster el objeto a Money!         
	 * @return 0 si los dineros son iguales.
	 * -1 Si este dinero es menor que el otro dinero .
	 *  1 Si este dinero es más que el otro
	 */
	public int compareTo(Object other) {
		return 0;
	}
}
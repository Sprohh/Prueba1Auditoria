package tarea1auditoria;

public class Currency {
	private String name;
	private Double rate;
	
	/**
	 * New Currency
	 * El argumento rate representa el tipo de cambio "universal" de la moneda.
         * Imagina que definimos un tipo de cambio para cada moneda en relación a una moneda
         * "universal". Asi, cada moneda define su valor en relación a esta moneda universal.
	 * En el mundo real, el dolar de los Estados Unidos funciona como moneda universal.  
         * 
	 * @param name El nombre de la moneda
	 * @param rate El tipo de cambio de esta moneda
	 */
        
	Currency (String name, Double rate) {
		this.name = name;
		this.rate = rate;
	}

	/** 
         * Convierte una cantidad de esta moneda en su valor en la "moneda universal"
	 * 
	 * @param amount Una cantidad de dinero a convertir
	 * @return El valor en la "moneda universal"
	 */
	public Integer universalValue(Integer amount) {
            int rateToInt =  getRate().intValue();
            return amount * rateToInt;
	}

	/** 
	 * @return name de la Moneda
	 */
	public String getName() {
		return name;
	}
	
	/** 
	 * 
	 * @return rate de la moneda
	 */
	public Double getRate() {
		return rate;
	}
	
	/** 
	 * 
	 * @param rate nuevo tipo de cambio de la moneda
	 */
	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	/** 
         * Convierte una cantidad de otra moneda, a una cantidad en esta moneda.         
	 * 
	 * @param amount cantidad de la otra moneda
	 * @param othercurrency la otra moneda
         * @return la cantidad en esta moneda
	*/
	public Integer valueInThisCurrency(Integer amount, Currency othercurrency) {
            int otherCurrencyToUniversalValue = othercurrency.universalValue(amount);
            return (int)(otherCurrencyToUniversalValue/getRate());
	}
}

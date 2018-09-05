/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea1auditoria;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Informatica
 */
public class CurrencyTest {
    
    public CurrencyTest() {
    }

    /*Realiza test para el método universalValue, el cual convierte el valor de una moneda a su valor en moneda universal
    (En este caso, el USD). Para esta prueba, se utiliza el CLP (peso chileno), con un valor de 680,00, equivalente a 1,02 USD.*/
    @Test
    public void testUniversalValue() {
        Currency chileanMoney = new Currency("CLP", 0.0015);
        assertTrue( chileanMoney.universalValue(68000) == 102 );
    }
    
    /*Prueba de método "valueInThisCurrency", este método realiza conversiones entre dos monedas diferentes.
    En este ejemplo se convierten 1,00 MXP (peso mexicanos) a pesos chilenos (33,33 CLP).
    */
    @Test
    public void testValueInThisCurrency() {
        Currency chileanMoney = new Currency("CLP", 0.0015);
        Currency mexicanMoney = new Currency("MXP", 0.052);
        assertTrue( chileanMoney.valueInThisCurrency(100, mexicanMoney) == 3333);
    }
}
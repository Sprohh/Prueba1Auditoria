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

    @Test
    public void testUniversalValue() {
        Currency chileanMoney = new Currency("Peso Chileno", 0.0015);
        assertTrue( chileanMoney.universalValue(680) == 1 );
    }
    
    @Test
    public void testValueInThisCurrency() {
        Currency chileanMoney = new Currency("Peso Chileno", 0.0015);
        Currency mexicanMoney = new Currency("Peso Mexicano", 0.052);
        assertTrue( chileanMoney.valueInThisCurrency(19, mexicanMoney) == 680);
    }
}
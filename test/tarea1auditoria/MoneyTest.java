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
 * @author Brayan
 */
public class MoneyTest {
    
    public MoneyTest() {
    }

    @Test
    public void testToString()
    {
        Currency currency = new Currency ("CLP", 0.0015);
        Money money = new Money(9999, currency );
        String result = "99.99 CLP";
        assertEquals(money.toString(), result);
    }
    
    @Test
    public void universalValue(){
        Currency currency = new Currency ("CLP", 0.0015);
        Money money = new Money(68000, currency );  
        assertTrue(money.universalValue() == 102);
    }
    
    /*Test para verificar si el monto de dinero es 0, si resulta ser 0 devolvera True */
    @Test
    public void testIsZeroTrue() {
        Currency currency = new Currency ("CLP", 0.0015);
        Money money = new Money(0, currency );
        assertTrue(money.isZero());
    }
    
    /*Test para verificar si el monto de dinero es 0, si resulta no ser 0 devolvera False */
    @Test
    public void testIsZeroFalse() {
        Currency currency = new Currency ("CLP", 0.0015);
        Money money = new Money(1, currency );
        assertFalse(money.isZero());
    }
    
}

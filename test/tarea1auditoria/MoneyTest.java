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
    public void testIsZeroTrue() {
        Currency currency = new Currency ("CLP", 0.0015);
        Money money = new Money(0, currency );
        assertTrue(money.isZero());
    }
    
    @Test
    public void testIsZeroFalse() {
        Currency currency = new Currency ("CLP", 0.0015);
        Money money = new Money(1, currency );
        assertFalse(money.isZero());
    }
    
}

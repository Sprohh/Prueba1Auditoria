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

    /*Test para verificar si devuelve el dinero en formato String Dinero (9999) + Nombre Moneda(CLP) como resultado "99.99 CLP" */
    @Test
    public void testToString()
    {
        Currency currency = new Currency ("CLP", 0.0015);
        Money money = new Money(9999, currency );
        String result = "99.99 CLP";
        assertEquals(result, money.toString());
    }
    
    /*Test el cual convierte el valor de una moneda a su valor en moneda universal, 
    Para esta prueba, se utiliza el CLP (peso chileno), con un valor de 680,00, equivalente a 1,02 USD*/
    @Test
    public void universalValue(){
        Currency currency = new Currency ("CLP", 0.0015);
        Money money = new Money(68000, currency );  
        assertTrue(money.universalValue() == 102);
    }
    
    @Test
    public void equals(){
        
    }
    
    //Se añade 1 MXP a 600CLP (1 MXP = 33,33 CLP), por lo que el resultado debiese ser 633,33CLP
    @Test
    public void testAdd(){
        Currency chileanMoney = new Currency ("CLP", 0.0015);
        Currency mexicanMoney = new Currency("MXP", 0.052);
        Money money = new Money(60000, chileanMoney);
        Money otherMoney = new Money(100, mexicanMoney);
        int expectedResult = 63333;
        assertTrue(money.add(otherMoney).getAmount().equals(expectedResult));            
    }    
    
    //Se resta 1 MXP a 600CLP (1 MXP = 33,33 CLP), por lo que el resultado debiese ser 566,67 CLP
    @Test
    public void testSub(){
        Currency chileanMoney = new Currency ("CLP", 0.0015);
        Currency mexicanMoney = new Currency("MXP", 0.052);
        Money money = new Money(60000, chileanMoney);
        Money otherMoney = new Money(100, mexicanMoney);
        int expectedResult = 56667;
        assertTrue(money.sub(otherMoney).getAmount().equals(expectedResult));        
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
    
    @Test
    public void testNegative() {
        Currency currency = new Currency ("CLP", 0.0015);
        Money money = new Money(1000, currency );
        assertTrue(money.negate().getAmount() == -1000);
    }
    
    
    
}

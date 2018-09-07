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
    public void testUniversalValue(){
        Currency currency = new Currency ("CLP", 0.0015);
        Money money = new Money(68000, currency );  
        assertTrue(money.universalValue() == 102);
    }
    
    //Verifica que la cantidad entre dos monedas sea la misma (En este caso, 1,02 USD, equivale a 684,93 CLP)
    @Test
    public void testEquals(){
        Currency chileanMoney = new Currency ("CLP", 0.0015);
        Currency usdMoney = new Currency("USD", 1.0);
        Money money = new Money(68493, chileanMoney);
        Money otherMoney = new Money(102, usdMoney);
        assertTrue(money.equals(otherMoney));
    }
    
    //Verifica que la cantidad entre dos monedas no sea la misma (En este caso, 2 USD no equivale a 684,93 CLP)
    @Test
    public void testNotEquals(){
        Currency chileanMoney = new Currency ("CLP", 0.0015);
        Currency usdMoney = new Currency("USD", 1.0);
        Money money = new Money(68493, chileanMoney);
        Money otherMoney = new Money(200, usdMoney);
        assertFalse(money.equals(otherMoney));
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
        try{
            Currency chileanMoney = new Currency ("CLP", 0.0015);
            Currency mexicanMoney = new Currency("MXP", 0.052);
            Money money = new Money(60000, chileanMoney);
            Money otherMoney = new Money(100, mexicanMoney);
            int expectedResult = 56667;
            assertTrue(money.sub(otherMoney).getAmount().equals(expectedResult));  
        }
        catch (NotWithdrawException e)
        {
        }  
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

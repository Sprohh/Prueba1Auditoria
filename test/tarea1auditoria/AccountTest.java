/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea1auditoria;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author orell
 */
public class AccountTest {
    
    Account account;
    Currency clpCurrency;
    
    public AccountTest() {
    }

    @Before
    public void createAccountObject()
    {
        clpCurrency = new Currency ("CLP", 0.0015);
        account = new Account("000", clpCurrency);
    }
    
    @Test
    public void testAddTimedPayment() {
        Money money = new Money(300000, clpCurrency);
        Bank santander = new Bank("Santander",clpCurrency);
    }
    
    @Test
    public void testRemoveTimedPayment() {
    }
    
    @Test
    public void testTimedPaymentExists() {
    }
    
    @Test
    public void testDeposit() {
    }
    
    @Test
    public void testWithdraw() {
    }
    
    @Test
    public void testGetBalance() {
    }
    
    @Test
    public void testTick() {
    }
}

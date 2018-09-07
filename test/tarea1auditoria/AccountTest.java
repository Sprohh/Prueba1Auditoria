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
        account.addTimedPayment("1234", 30, 30, money, santander, "111");
        assertTrue(account.timedPaymentExists("1234"));
    }
    
    @Test
    public void testRemoveTimedPayment() {
        Money money = new Money(300000, clpCurrency);
        Bank santander = new Bank("Santander",clpCurrency);
        account.addTimedPayment("1234", 30, 30, money, santander, "111");
        account.removeTimedPayment("1234");
        assertFalse(account.timedPaymentExists("1234"));
    }
    
    @Test
    public void testTimedPaymentExists() {
        Money money = new Money(300000, clpCurrency);
        Bank santander = new Bank("Santander",clpCurrency);
        account.addTimedPayment("1234", 30, 30, money, santander, "111");
        assertTrue(account.timedPaymentExists("1234"));
    }
    
    @Test
    public void testDeposit() {
        try{
            Money money = new Money(300000, clpCurrency);
            account.deposit(money);
            assertTrue(account.getBalance().getAmount()==300000);
        }
        catch (NegativeAddException e)
        {
        } 
    }
    
    @Test
    public void testNegativeDeposit() {
        boolean negativeAddException = false;
        try{
            Money money = new Money(-300000, clpCurrency);
            account.deposit(money);
        }
        catch (NegativeAddException e)
        {
            negativeAddException = true;
        } 
        assertTrue(negativeAddException);
    }    
    
    @Test
    public void testWithdraw() {
        try
        {
            Money depositMoney = new Money(600000, clpCurrency);
            account.deposit(depositMoney);
            Money withdrawMoney = new Money(300000, clpCurrency);
            account.withdraw(withdrawMoney);
            assertTrue(account.getBalance().getAmount()==300000);
        }
        catch (NotWithdrawException e)
        {
        }  
        catch (NegativeAddException e)
        {
        } 
    }
    
    @Test
    public void testNotWithdraw() {
        boolean notWithdrawException= false;
        try
        {
            Money depositMoney = new Money(600000, clpCurrency);
            account.deposit(depositMoney);
            Money withdrawMoney = new Money(700000, clpCurrency);
            account.withdraw(withdrawMoney);
        }
        catch (NotWithdrawException e)
        {
            notWithdrawException= true;
        }  
        catch (NegativeAddException e)
        {
        } 
        assertTrue(notWithdrawException);
    }    
}

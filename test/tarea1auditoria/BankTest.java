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
public class BankTest {
    Currency clpCurrency;
    Bank bank;
    
    public BankTest() {
    }
    
    @Before
    public void createBankObject()
    {
        clpCurrency = new Currency ("CLP", 0.0015);
        bank = new Bank("BCI", clpCurrency);
    }
    

    //Prueba del método openAccount
    @Test
    public void testOpenAccount() {
        try
        {
            bank.openAccount("123456789");
        }
        catch (AccountExistsException e)
        {
            
        }
    }
    //Prueba de excepción AccountExistsException en el método openAccount
    @Test
    public void testAccountAlreadyExist() {
        boolean accountAlreadyExists = false;
        try
        {
            bank.openAccount("111");
            bank.openAccount("111");
        }
        catch (AccountExistsException e)
        {
            accountAlreadyExists = true;
        }
        
        assertTrue(accountAlreadyExists);
    }
    //Prueba del método deposit
    @Test
    public void testDeposit() {
        Money money = new Money(300000, clpCurrency);
        try
        {
            bank.openAccount("000");
            bank.deposit("000", money);
            assertTrue(bank.getBalance("000").equals(300000));
        }
        catch (AccountDoesNotExistException e)
        {
        }
        catch (AccountExistsException e)
        {
        }     
    }
    //Prueba de excepción AccountDoesNotExistException en el método deposit
    @Test
    public void testDepositToAccountThatDoesntExists() {
        boolean accountDoesntExists = false;
        Money money = new Money(300000, clpCurrency);
        try
        {
            bank.deposit("000", money);
        }
        catch (AccountDoesNotExistException e)
        {
            accountDoesntExists = true;
        }
        
        assertTrue(accountDoesntExists);
    }
    //Prueba del método withdraw
    @Test
    public void testWithdraw() {
        Money money = new Money(300000, clpCurrency);
        try
        {
            bank.openAccount("000");
            bank.withdraw("000", money);
            assertTrue(bank.getBalance("000").equals(-300000));
        }
        catch (AccountDoesNotExistException e)
        {
        }
        catch (AccountExistsException e)
        {
        }     
    }
    //Prueba de excepción AccountDoesNotExistException en el método withdraw
    @Test
    public void testWithdrawToAccountThatDoesntExists() {
        boolean accountDoesntExists = false;
        Money money = new Money(300000, clpCurrency);
        try
        {
            bank.withdraw("000", money);
        }
        catch (AccountDoesNotExistException e)
        {
            accountDoesntExists = true;
        }
        assertTrue(accountDoesntExists);
    }
    //Prueba del método getBalance
    @Test
    public void testGetBalance() {
        try
        {
            bank.openAccount("000");
            assertTrue(bank.getBalance("000").equals(0));
        }
        catch (AccountDoesNotExistException e)
        {
        }
        catch (AccountExistsException e)
        {
        }   
    }
    //Prueba de excepción AccountDoesNotExistException en el método getBalance
    @Test
    public void testGetBalanceToAccountThatDoesntExists() {
        boolean accountDoesntExists = false;
        try
        {
            bank.getBalance("000");
        }
        catch (AccountDoesNotExistException e)
        {
            accountDoesntExists = true;
        }
        assertTrue(accountDoesntExists);
    }
    //Prueba del método transfer
    @Test
    public void testMoneyTransfer() {
        Money money = new Money(300000, clpCurrency);
        try
        {
            bank.openAccount("000");
            bank.openAccount("111");
            bank.transfer("000","111",money);
        }
        catch (AccountDoesNotExistException e)
        {
        }
    }
    //Prueba de excepción AccountDoesNotExistException en el método transfer
    @Test
    public void testMoneyTransferToAccountThatDoesntExists() {
        boolean accountDoesntExists = false;
        Money money = new Money(300000, clpCurrency);
        try
        {
            bank.transfer("000","111",money);
        }
        catch (AccountDoesNotExistException e)
        {
            accountDoesntExists = true;
        }
        assertTrue(accountDoesntExists);
    }
    //Prueba del método transfer
    @Test
    public void testMoneyTransferInSameBank() {
    }
    //Prueba del método addTimedPayment
    @Test
    public void testAddTimedPayment() {
    }
    //Prueba del método removeTimedPayment
    @Test
    public void testRemoveTimedPayment() {
    }
    //Prueba del método tick
    @Test
    public void testTick() {
    }
    //Prueba de excepción AccountDoesNotExistException en el método tick
    @Test
    public void testTickToAccountThatDoesntExist() {
    }
}

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
    public void testOpenAccountAlreadyExist() {
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
        Money depositMoney = new Money(600000, clpCurrency);
        Money withdrawMoney = new Money(300000, clpCurrency);
        try
        {
            bank.openAccount("000");
            bank.deposit("000", depositMoney);
            bank.withdraw("000", withdrawMoney);
            assertTrue(bank.getBalance("000").equals(300000));
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
    //Prueba del método transfer (Se cargan 600000 CLP a la cuenta "000", luego envía 150000 a la cuenta "111"
    @Test
    public void testMoneyTransfer() {
        Money depositMoney = new Money(600000, clpCurrency);
        Money transferMoney = new Money(150000, clpCurrency);
        Bank santander = new Bank("Santander",clpCurrency);
        try
        {
            bank.openAccount("000");
            bank.deposit("000", depositMoney);
            santander.openAccount("111");
            bank.transfer("000",santander,"111",transferMoney);
            assertTrue(bank.getBalance("000").equals(450000) && bank.getBalance("111").equals(150000));
        }
        catch (AccountDoesNotExistException e)
        {
        }
        catch (AccountExistsException e)
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
            bank.openAccount("000");
            bank.transfer("000","999",money);
        }
        catch (AccountDoesNotExistException e)
        {
            accountDoesntExists = true;
        }
        catch (AccountExistsException e)
        {
        }
        assertTrue(accountDoesntExists);
    }
    //Prueba del método transfer
    @Test
    public void testMoneyTransferInSameBank() {
        Money depositMoney = new Money(600000, clpCurrency);
        Money transferMoney = new Money(150000, clpCurrency);
        try
        {
            bank.openAccount("000");
            bank.openAccount("111");
            bank.deposit("000", depositMoney);
            bank.transfer("000","111",transferMoney);
            assertTrue(bank.getBalance("000").equals(450000) && bank.getBalance("111").equals(150000));
        }
        catch (AccountDoesNotExistException e)
        {
        }
        catch (AccountExistsException e)
        {
        }
    }
    //Prueba del método addTimedPayment
    @Test
    public void testAddTimedPayment() {
        Money money = new Money(300000, clpCurrency);
        Bank santander = new Bank("Santander",clpCurrency);
        try
        {
            bank.openAccount("000");
            santander.openAccount("111");
            bank.addTimedPayment("000", "1234", 60, 30, money, santander, "111");
        }
        catch (AccountExistsException e)
        {
            
        }
    }
    //Prueba del método removeTimedPayment
    @Test
    public void testRemoveTimedPayment() {
        Money depositMoney = new Money(600000, clpCurrency);
        Money money = new Money(300000, clpCurrency);
        Bank santander = new Bank("Santander",clpCurrency);
        try
        {
            bank.openAccount("000");
            bank.deposit("000", depositMoney);
            santander.openAccount("111");
            bank.addTimedPayment("000", "1234", 60, 30, money, santander, "111");
            bank.removeTimedPayment("000", "1234");
        }
        catch (AccountExistsException e)
        {
            
        }
        catch (AccountDoesNotExistException e)
        {
            
        }
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

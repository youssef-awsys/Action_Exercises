package comm.bank;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @BeforeEach
    void setup(){
        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }
    @Test
    void testAccountCreation_ReturnName() {
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        manager.addAccount(account);
        String output = account.toString();
        String expected = "Shahib Alu Akbar";
        assertTrue(output.contains(expected));
    }
    @Test
    void testDeposit_WithValidAmount_ReturnAmount() throws AccountFrozenException, InvalidAmountException {
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        manager.addAccount(account);
        account.deposit(1000);
        assertEquals(1000, account.getBalance());
    }
    @Test
    void testDeposit_WithZeroAmount_ReturnAmountIsInvalid() throws AccountFrozenException, InvalidAmountException {
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        manager.addAccount(account);
        Throwable exception = assertThrows(InvalidAmountException.class, () -> {
            account.deposit(0); });
        assertEquals("Amount is Invalid. Should be greater than 0", exception.getMessage());
    }
    @Test
    void testDeposit_WithNegativeAmount_ReturnAmountIsInvalid() {
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        manager.addAccount(account);
        Throwable exception = assertThrows(InvalidAmountException.class, () -> {
            account.deposit(-500); });
        assertEquals("Amount is Invalid. Should be greater than 0", exception.getMessage());
    }
    @Test
    void testWithdraw_WithSufficientFunds_ReturnWithdrawnAmountAndCheckBalance() throws AccountFrozenException, InvalidAmountException, InsufficientFundsException {
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        manager.addAccount(account);        account.deposit(1000);
        account.withdraw(700);
        String output = outContent.toString();
        assertTrue(output.contains("Php 700"));
        assertEquals(300, account.getBalance());
    }
    @Test
    void testWithdraw_WithInsufficientFunds_ReturnInsufficientBalance() { 
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        manager.addAccount(account);
        Throwable exception = assertThrows(InsufficientFundsException.class, () -> {
            account.deposit(1000);
            account.withdraw(1500); });
        assertEquals("Insufficient Balance", exception.getMessage());
    }
    @Test
    void testWithdraw_WithInvalidAmount_ReturnAmountIsInvalid() {
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        manager.addAccount(account);
        Throwable exception = assertThrows(InvalidAmountException.class, () -> {
            account.deposit(1000);
            account.withdraw(-1500); });
        assertEquals("Amount is Invalid. Should be greater than 0", exception.getMessage());
    }
    @Test
    void testDeposit_WhenAccountIsFrozen_ReturnAccountHasBeenFrozenAndCannotDeposit() throws AccountFrozenException, InvalidAmountException {
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        manager.addAccount(account);
        account.freezeAccount();
        Throwable exception = assertThrows(AccountFrozenException.class, () -> {
            account.deposit(500);});
        assertEquals("Account is Frozen", exception.getMessage());
        assertTrue(account.isFrozen());
    } 
    @Test
    void testWithdraw_WhenAccountIsFrozen_ReturnAccountHasBeenFrozenAndCannotWithdraw() throws InsufficientFundsException, AccountFrozenException, InvalidAmountException {
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        manager.addAccount(account);
        account.freezeAccount();
        Throwable exception = assertThrows(AccountFrozenException.class, () -> {
            account.withdraw(300); });
        assertEquals("Account is Frozen", exception.getMessage());
    } 
    @Test
    void testWithdraw_WhenAccountIsUnFrozen_ReturnAccountHasBeenUnFrozenAndWithdrawAmount() throws AccountFrozenException, InvalidAmountException, InsufficientFundsException {
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        manager.addAccount(account);        account.freezeAccount();
        account.unfreezeAccount();
        account.deposit(2000);
        account.withdraw(1500);
        String output = outContent.toString();
        assertTrue(output.contains("Account has been unfrozen"));
        assertTrue(output.contains("Php 1500.0"));
        assertFalse(account.isFrozen());
        assertEquals(500, account.getBalance());
    }
    @Test
    void testIsFrozen_CheckAccountIfItIsFrozen_ReturnFalse() {
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        manager.addAccount(account);        
        assertFalse(account.isFrozen());
    }
    @Test
    void testCheckBalance_AfterMultipleTransactions_ReturnBalance() throws AccountFrozenException, InvalidAmountException, InsufficientFundsException {
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        manager.addAccount(account);        account.freezeAccount();
        account.unfreezeAccount();
        account.deposit(2000);
        account.withdraw(1500);
        account.withdraw(100);
        account.deposit(250);
        assertEquals(650, account.getBalance());
    }
    @Test
    void testGetTransactionHistory_ReturnListofTransaction() throws AccountFrozenException, InvalidAmountException, InsufficientFundsException {
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        manager.addAccount(account);
        account.deposit(2000);
        account.withdraw(1500);
        account.withdraw(100);
        account.deposit(250);
        int expectedSize = 4;
        List<Transaction> history = account.getTransactionHistory();
        assertEquals(expectedSize, history.size(), "History should contain 4 transaction");
        assertEquals(250, history.getLast().getAmount(), "Last transaction should be 250");
        assertTrue(history.toString().contains("Deposit : 2000"));
    }
    @Test
    void testFilteredTranstactionAbove_withValidValue_ReturnListofTransaction() throws AccountFrozenException, InvalidAmountException, InsufficientFundsException {
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        manager.addAccount(account);
        account.deposit(2000);
        account.withdraw(1500);
        account.withdraw(100);
        account.deposit(250);
        int expectedSize = 2;
        List<Transaction> history = account.getTransactionHistory();
        List<Transaction> filtered = manager.filterTransactionsAbove(
                500, history);
        assertEquals(expectedSize, filtered.size(), "History should contain 4 transaction");
        assertEquals(1500, filtered.getLast().getAmount(), "Last transaction should be 250");
    }
    @Test
    void testFilteredTranstactionAbove_withInvalidValue_ReturnInvalidAmount() throws AccountFrozenException, InvalidAmountException, InsufficientFundsException {
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        manager.addAccount(account);
        account.deposit(2000);
        account.withdraw(1500);
        account.withdraw(100);
        account.deposit(250);
        List<Transaction> history = account.getTransactionHistory();
        Throwable exception = assertThrows(InvalidAmountException.class, () -> {
            List<Transaction> filtered = manager.filterTransactionsAbove(
                    -500, history); });
        assertEquals("Invalid Amount for filtering", exception.getMessage());
    }
    @Test
    void testSortTransactionByAmoung_withAmount500_ReturnListofTransaction() throws AccountFrozenException, InvalidAmountException, InsufficientFundsException {
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        manager.addAccount(account);
        account.deposit(2000);
        account.withdraw(1500);
        account.withdraw(100);
        account.deposit(250);
        int expectedSize = 4;
        List<Transaction> history = account.getTransactionHistory();
        List<Transaction> sorted = manager.sortTransactionsByAmount(
                history);
        assertEquals(expectedSize, sorted.size(), "History should contain 4 transaction");
        assertEquals(2000, sorted.getLast().getAmount(), "Last transaction should be 250");
    }
    @Test
    void testGetAccount_withAccount_ReturnAccount() throws NullPointerException, AccountFrozenException, InvalidAmountException {
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        manager.addAccount(account);
        BankAccount actualAccount = manager.getAccount(1);
        assertEquals(account, actualAccount);
    }
    @Test
    void testGetAccount_withInvalidAccount_ReturnNull() throws NullPointerException {
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        manager.addAccount(account);
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            System.out.println(manager.getAccount(2));});
        assertEquals("Null Pointer", exception.getMessage());
    }
    @Test
    void testlistAccount_ReturnAccounts() {
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        manager.addAccount(account);
        manager.listAccount();
        String output = outContent.toString();
        assertTrue(output.contains("Shahib"));
    }  
    @Test
    void testMain() {
        Main.main(null);
    }
}

package comm.bank;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        String output = account.getOwnerName();
        String expected = "Shahib Alu Akbar";
        assertEquals(expected, output);
    }
    @Test
    void testDeposit_WithValidAmount_ReturnAmount() {
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        account.deposit(1000);
        assertEquals(1000, account.getBalance());
    }
    @Test
    void testDeposit_WithZeroAmount_ReturnDepositAmountMustBePositive() {
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        account.deposit(0);
        String output = outContent.toString();
        assertTrue(output.contains("The deposit amount must be positive"));
    }
    @Test
    void testDeposit_WithNegativeAmount_ReturnDepositAmountMustBePositive() {
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        account.deposit(-500);
        String output = outContent.toString();
        assertTrue(output.contains("The deposit amount must be positive"));
    }
    @Test
    void testWithdraw_WithSufficientFunds_ReturnWithdrawnAmountAndCheckBalance() {
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        account.deposit(1000);
        account.withdraw(700);
        String output = outContent.toString();
        assertTrue(output.contains("Php 700"));
        assertEquals(300, account.getBalance());
    }
    @Test
    void testWithdraw_WithInsufficientFunds_ReturnInsufficientBalance() {
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        account.deposit(1000);
        account.withdraw(1500);
        String output = outContent.toString();
        assertTrue(output.contains("Insufficient Balance"));
    }
    @Test
    void testWithdraw_WithNegativeAmount_ReturnWithdrawnAmountMustBePositive() {
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        account.deposit(1000);
        account.withdraw(-500);
        String output = outContent.toString();
        assertTrue(output.contains("The withdrawn amount must be positive"));
    }
    @Test
    void testDeposit_WhenAccountIsFrozen_ReturnAccountHasBeenFrozenAndCannotDeposit() {
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        account.freezeAccount();
        account.deposit(1500);
        String output = outContent.toString();
        assertTrue(output.contains("Account is Frozen"));
        assertTrue(account.isFrozen());
    } 
    @Test
    void testWithdraw_WhenAccountIsFrozen_ReturnAccountHasBeenFrozenAndCannotWithdraw() {
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        account.freezeAccount();
        account.withdraw(1500);
        String output = outContent.toString();
        assertTrue(output.contains("Account is Frozen"));
        assertTrue(account.isFrozen());
    } 
    @Test
    void testWithdraw_WhenAccountIsUnFrozen_ReturnAccountHasBeenUnFrozenAndWithdrawAmount() {
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        account.freezeAccount();
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
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        assertFalse(account.isFrozen());
    }
    @Test
    void testCheckBalance_AfterMultipleTransactions_ReturnBalance() {
        SavingsAccount account = new SavingsAccount("Shahib Alu Akbar");
        account.freezeAccount();
        account.unfreezeAccount();
        account.deposit(2000);
        account.withdraw(1500);
        account.withdraw(100);
        account.deposit(250);
        assertEquals(650, account.getBalance());
    }
    @Test
    void testMain() {
        Main.main(null);
    }
}

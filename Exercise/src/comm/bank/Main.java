package comm.bank;

public final class Main {
    /**
     *
     */
    private Main() {
        super();
    }
    /**
     *
     * @param args
     */
    public static void main(final String[] args) {
        final int[] money = {100, 500, 11500};
        SavingsAccount account = new SavingsAccount("User");
        System.out.println(account.getOwnerName()); //TC1
        account.deposit(money[1] + money[1]);    //TC2
        account.deposit(0);       //TC3
        account.deposit(-money[1]);    //TC4
        account.withdraw(money[1]);    //TC5
        account.withdraw(money[1] + money[1] + money[1]);   //TC6
        account.withdraw(-money[0]);   //TC7
        account.freezeAccount();  //TC8
        account.deposit(money[2]);   //TC8
        account.withdraw(money[1]);    //TC9
        account.unfreezeAccount(); //TC10
        account.withdraw(money[0]);    //TC10
        System.out.println(account.isFrozen()); //TC11
        System.out.println("Balance: " + account.getBalance()); //TC12
    }
}

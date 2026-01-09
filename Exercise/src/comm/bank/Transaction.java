package comm.bank;

public class Transaction {
    /**
    * The type of the transaction, such as "Deposit" or "Withdrawal".
    */
   private String type;
   /**
    * The amount of money involved in the transaction.
    */
   private double amount;
   /**
    * Constructs a new transaction with the specified type and amount.
    * @param transType
    * @param amountTrans
    */
   public Transaction(final String transType, final double amountTrans) {
       this.type = transType;
       this.amount = amountTrans;
   }
   /**
    * Returns the type of the transaction.
    * @return type
    */
   public String getType() {
       return type;
   }
   /**
    * Returns the amount of the transaction.
    * @return amount
    */
   public double getAmount() {
       return amount;
   }
   /**
    * Returns a string representation of the transaction, including
    * its type and amount.
    * @return string type and amount
    */
   public String toString() {
       return getType() + " : " + getAmount();
   }
}

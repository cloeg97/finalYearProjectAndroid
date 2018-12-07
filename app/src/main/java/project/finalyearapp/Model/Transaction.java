package project.finalyearapp.Model;

public class Transaction {
    private String Amount;
    private String Currency;
    private String Receiver;

    public Transaction() {
    }

    public Transaction(String amount, String currency, String receiver) {
        Amount = amount;
        Currency = currency;
        Receiver = receiver;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }
}

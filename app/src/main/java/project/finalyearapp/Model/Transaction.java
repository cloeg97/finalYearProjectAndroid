package project.finalyearapp.Model;

public class Transaction {
    private String Amount;
    private String Currency;
    private String Receiver;
    private String Shop;
    private Boolean custflag;
    private Boolean shopAflag;
    private Boolean shopBflag;

    public Transaction() {
    }

    public Transaction(String amount, String currency, String receiver, String shop, Boolean custFlag, Boolean shopAFlag, Boolean shopBFlag) {
        Amount = amount;
        Currency = currency;
        Receiver = receiver;
        Shop = shop;
        custflag = custFlag;
        shopAflag = shopAFlag;
        shopBflag = shopBFlag;
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

    public String getShop() {
        return Shop;
    }

    public void setShop(String shop) {
        Shop = shop;
    }

    public Boolean getCustflag() {
        return custflag;
    }

    public void setCustflag(Boolean custflag) {
        this.custflag = custflag;
    }

    public Boolean getShopAflag() {
        return shopAflag;
    }

    public void setShopAflag(Boolean shopAflag) {
        this.shopAflag = shopAflag;
    }

    public Boolean getShopBflag() {
        return shopBflag;
    }

    public void setShopBflag(Boolean shopBflag) {
        this.shopBflag = shopBflag;
    }
}

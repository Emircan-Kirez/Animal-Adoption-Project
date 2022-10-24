import java.io.Serializable;

public class Food implements Serializable {
    private final int price;
    private final int amount;

    public Food(int price, int amount){
        this.price = price;
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}

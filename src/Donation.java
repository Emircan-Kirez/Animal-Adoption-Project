import java.io.Serializable;

public class Donation implements Serializable {
   private Person donor;
   private int amount;
   private String donateDate;

    public Donation(Person donor, int amount, String donateDate) {
        this.donor = donor;
        this.amount = amount;
        this.donateDate = donateDate;
    }

    public Person getDonor() {
        return donor;
    }

    public void setDonor(Person donor) {
        this.donor = donor;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDonateDate() {
        return donateDate;
    }

    public void setDonateDate(String donateDate) {
        this.donateDate = donateDate;
    }
}

import java.io.Serializable;

public class Source implements Serializable {
    private int dogFoodAmount;
    private int catFoodAmount;
    private int birdFoodAmount;
    private Food dogFood;
    private Food catFood;
    private Food birdFood;

    public Source(int dogFoodAmount, int catFoodAmount, int birdFoodAmount){
        this.dogFoodAmount = dogFoodAmount;
        this.catFoodAmount = catFoodAmount;
        this.birdFoodAmount = birdFoodAmount;
        //Prices are determined randomly.
        dogFood = new Food(50, 100);
        catFood = new Food(60, 100);
        birdFood = new Food(30, 100);
    }

    public void addDogFood(int number){
        setDogFoodAmount(getDogFoodAmount() + number * dogFood.getAmount());
    }

    public void addCatFood(int number){
        setCatFoodAmount(getCatFoodAmount() + number * catFood.getAmount());
    }
    public void addBirdFood(int number){
        setBirdFoodAmount(getBirdFoodAmount() + number * birdFood.getAmount());
    }

    public int getDogFoodAmount() {
        return dogFoodAmount;
    }

    public void setDogFoodAmount(int dogFoodAmount) {
        this.dogFoodAmount = dogFoodAmount;
    }

    public int getCatFoodAmount() {
        return catFoodAmount;
    }

    public void setCatFoodAmount(int catFoodAmount) {
        this.catFoodAmount = catFoodAmount;
    }

    public int getBirdFoodAmount() {
        return birdFoodAmount;
    }

    public void setBirdFoodAmount(int birdFoodAmount) {
        this.birdFoodAmount = birdFoodAmount;
    }

    public Food getDogFood() {
        return dogFood;
    }

    public void setDogFood(Food dogFood) {
        this.dogFood = dogFood;
    }

    public Food getCatFood() {
        return catFood;
    }

    public void setCatFood(Food catFood) {
        this.catFood = catFood;
    }

    public Food getBirdFood() {
        return birdFood;
    }

    public void setBirdFood(Food birdFood) {
        this.birdFood = birdFood;
    }
}

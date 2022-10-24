import java.io.Serializable;

public abstract class Animal implements Serializable {
    private static int counter = 1;
    private String ID;
    private int age;
    private String kind; //breed
    private String healthStatus;
    private String foundDate;
    private int foodItAte;
    private Person owner;
    private String ownershipDate;


    public Animal(int age, String kind, String healthStatus, String foundDate, int foodItAte) {
        ID = String.format("%04d", counter);
        counter++;
        this.age = age;
        this.kind = kind;
        this.healthStatus = healthStatus;
        this.foundDate = foundDate;
        this.foodItAte = foodItAte;
    }

    public String toString(){
        String intro = "";
        intro += "ID : " + ID + " | Age: " + age + " | Kind: " + kind + " | Health Status: " + healthStatus + " | Found Date: " + foundDate +
                " | Daily Food It Eats: " + foodItAte + " gr";
        if(owner != null){
            intro += " | Owner: " + owner.getName() + " " + owner.getSurname() + " | Ownership Date: " + ownershipDate;
        }
        return intro;
    }

    public static int getCounter(){
        return counter;
    }

    public void setCounter(int counter){
        this.counter = counter;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(String foundDate) {
        this.foundDate = foundDate;
    }

    public int getFoodItAte() {
        return foodItAte;
    }

    public void setFoodItAte(int foodItAte) {
        this.foodItAte = foodItAte;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getOwnershipDate() {
        return ownershipDate;
    }

    public void setOwnershipDate(String ownershipDate) {
        this.ownershipDate = ownershipDate;
    }
}

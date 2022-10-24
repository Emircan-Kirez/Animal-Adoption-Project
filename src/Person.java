import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private String surname;
    private String ID;
    private String phoneNumber;
    private String animalID;
    private String whichAnimal;
    private int totalDonation;

    public Person(String name, String surname, String ID, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.ID = ID;
        this.phoneNumber = phoneNumber;
        this.totalDonation = 0;
    }

    public String toString(){
        String intro = "";
        intro += "Name : " + name + " | Surname: " + surname + " | ID(SSN): " + ID + " | Phone Number: " + phoneNumber +
                " | Total Donation Amount: " + totalDonation;
        if(animalID != null){
            intro += " | Pet's ID: " + animalID + " | Kind: " + whichAnimal;
        }else{
            intro += " | He/She has no animals.";
        }
        return intro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAnimalID() {
        return animalID;
    }

    public void setAnimalID(String animalID, String whichAnimal) {
        this.animalID = animalID;
        this.whichAnimal = whichAnimal;
    }

    public int getTotalDonation() {
        return totalDonation;
    }

    public void setTotalDonation(int totalDonation) {
        this.totalDonation = totalDonation;
    }
}

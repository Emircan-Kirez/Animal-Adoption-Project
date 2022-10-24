import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

@SuppressWarnings("unchecked")
public class Company{
    private String username;
    private String password;
    private String companyName;
    private int totalDonation;
    //TreeMap is used to keep the information in order.
    private TreeMap<String, Dog> dogs;
    private TreeMap<String, Cat> cats;
    private TreeMap<String, Bird> birds;
    private TreeMap<String, Person> persons;
    private Source source;
    private ArrayList<Donation> donations;
    private ArrayList<Animal> animalsOwnedByPerson; //Animals that have an owner

    public Company(String companyName){
        this.companyName = companyName;
        dogs = new TreeMap<>();
        cats = new TreeMap<>();
        birds = new TreeMap<>();
        persons = new TreeMap<>();
        donations = new ArrayList<>();
        animalsOwnedByPerson = new ArrayList<>();
        readFromFile();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTotalDonation() {
        return totalDonation;
    }

    public String toString(){
        String intro = "";
        intro += "---------------- Company Information ----------------\n" + "• Company Name: " + companyName;
        intro += "\n• There are " + dogs.size()  + " dogs waiting for their new owner.";
        intro += "\n• There are " + cats.size()  + " cats waiting for their new owner.";
        intro += "\n• There are " + birds.size()  + " birds waiting for their new owner.";
        intro += "\n• " + persons.size()  + " people are registered in the system.";
        return intro;
    }

    private void readFromFile(){
        try{
            ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("company.bin"));
            username = (String) objIn.readObject();
            password = (String) objIn.readObject();
            companyName = (String) objIn.readObject();
            totalDonation = (Integer) objIn.readObject();
            dogs = (TreeMap<String, Dog>) objIn.readObject();
            cats = (TreeMap<String, Cat>) objIn.readObject();
            birds = (TreeMap<String, Bird>) objIn.readObject();
            persons = (TreeMap<String, Person>) objIn.readObject();
            source = (Source) objIn.readObject();
            donations = (ArrayList<Donation>) objIn.readObject();
            for(Donation donation : donations)
                donation.setDonor(findPerson(donation.getDonor().getID())); //I associate donations with people
            animalsOwnedByPerson = (ArrayList<Animal>) objIn.readObject();
            for(Animal animal : animalsOwnedByPerson)
                animal.setOwner(findPerson(animal.getOwner().getID())); //I associate animals with people

            //Creating a temporary animal object to set the counter
            Dog dog = new Dog(0, "0", "0", "0", 0);
            dog.setCounter(dogs.size() + cats.size() + birds.size() + animalsOwnedByPerson.size() + 1);
            objIn.close();
        }catch (Exception e){
            System.out.println("An error was encountered while reading from the file. The system has shut itself down.\n");
            e.printStackTrace();
            System.exit(1);
        }

    }

    public void writeToFile(){
        try{
            ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("company.bin"));
            objOut.writeObject(username);
            objOut.writeObject(password);
            objOut.writeObject(companyName);
            objOut.writeObject(totalDonation);
            objOut.writeObject(dogs);
            objOut.writeObject(cats);
            objOut.writeObject(birds);
            objOut.writeObject(persons);
            objOut.writeObject(source);
            objOut.writeObject(donations);
            objOut.writeObject(animalsOwnedByPerson);
            objOut.close();
        }catch (Exception e){
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    /* Animal Methods */
    public <T extends Animal> T findAnimal(String ID){
        if(dogs.get(ID) != null){
            return (T) dogs.get(ID);
        }else if(cats.get(ID) != null){
            return (T) cats.get(ID);
        }else if(birds.get(ID) != null){
            return (T) birds.get(ID);
        }
        return null;
    }

    public <T extends Animal> void  addAnimal(T animal){
        if(animal instanceof Dog){ // animal instanceof Dog == animal.getClass().getName().compareTo("Dog") == 0
            Dog dog = (Dog) animal;
            dogs.put(dog.getID(), dog);
            System.out.println("\nA new dog has been added to the shelter. Number of dogs in the shelter: " + dogs.size() + "\n");
        }else if(animal instanceof Cat){
            Cat cat = (Cat) animal;
            cats.put(cat.getID(), cat);
            System.out.println("\nA new cat has been added to the shelter. Number of cats in the shelter: " + cats.size() + "\n");
        }else if(animal instanceof Bird){
            Bird bird = (Bird) animal;
            birds.put(bird.getID(), bird);
            System.out.println("\nA new bird has been added to the shelter. Number of birds in the shelter:  " + birds.size() + "\n");
        }else{
            System.out.println("No animal found with the ID you typed!!");
        }
    }

    public Dog createDog() throws Exception{
        System.out.println("Write the new dog's age, kind, health status (Healthy - Injured - Sick) and the daily amount of food it eats," +
                " with a space between them: ");

        String line = Main.input.nextLine();
        String[] words = line.split(" ");

        return new Dog(Integer.parseInt(words[0]), words[1], words[2], Main.dateFormatter.format(new Date()), Integer.parseInt(words[3]));
    }

    public void showDogs(){
        if(dogs.size() == 0){
            System.out.println("There are NO dogs in the shelter.");
            return;
        }
        System.out.println("----------------------------------------- Our Dogs In the Shelter -----------------------------------------");
        for(Dog dog : dogs.values())
            System.out.println(dog);
        System.out.println("------------------------------------------------------------------------------------------------------------\n");
    }

    public Cat createCat() throws Exception{
        System.out.println("Write the new cat's age, kind, health status (Healthy - Injured - Sick) and the daily amount of food it eats," +
                 " with a space between them: ");

        String line = Main.input.nextLine();
        String[] words = line.split(" ");

        return new Cat(Integer.parseInt(words[0]), words[1], words[2], Main.dateFormatter.format(new Date()), Integer.parseInt(words[3]));
    }

    public void showCats(){
        if(cats.size() == 0){
            System.out.println("There are NO cats in the shelter.");
            return;
        }
        System.out.println("----------------------------------------- Our Cats In the Shelter -----------------------------------------");
        for(Cat cat : cats.values())
            System.out.println(cat);
        System.out.println("-----------------------------------------------------------------------------------------------------------\n");
    }

    public Bird createBird() throws Exception{
        System.out.println("Write the new bird's age, kind, health status (Healthy - Injured - Sick) and the daily amount of food it eats," +
                " with a space between them: ");

        String line = Main.input.nextLine();
        String[] words = line.split(" ");

        return new Bird(Integer.parseInt(words[0]), words[1], words[2], Main.dateFormatter.format(new Date()), Integer.parseInt(words[3]));
    }

    public void showBirds(){
        if(birds.size() == 0){
            System.out.println("There are NO birds in the shelter.");
            return;
        }
        System.out.println("----------------------------------------- Our Birds In the Shelter -----------------------------------------");
        for(Bird bird : birds.values())
            System.out.println(bird);
        System.out.println("----------------------------------------------------------------------------------------------------------\n");
    }

    /* Person Methods */
    private Person createPerson() throws Exception{
        System.out.println("Enter the name, surname, ID(SSN) number and phone number of the new person you want to add, respectively and with a space between them: ");

        String line = Main.input.nextLine();
        String[] words = line.split(" ");

        return new Person(words[0], words[1], words[2], words[3]);
    }

    private void addPerson(Person person){
        persons.put(person.getID(), person);
        System.out.println("\nA new person has been added to the system. Number of people registered in the system: " + persons.size() + "\n");
    }

    private Person findPerson(String ID){
        return persons.get(ID);
    }

    public void showPersons(){
        if(persons.size() == 0){
            System.out.println("There is NO person record in the system. ");
            return;
        }
        System.out.println("----------------------------------------- Registered Persons In the System -----------------------------------------");
        for(Person person : persons.values())
            System.out.println(person);
    }

    /* Donate Methods */
    public void getDonation() throws Exception{
        Person donor;
        System.out.println("Write down the donor's ID(SSN) number: ");
        String ID = Main.input.nextLine();
        if((donor = findPerson(ID)) == null){
            System.out.println("Since this person is not registered in the system, you must register this person.");
            donor = createPerson();
            addPerson(donor);
        }

        System.out.println("Enter the amount of donation you want to make: ");
        String line = Main.input.nextLine();
        donations.add(new Donation(donor, Integer.parseInt(line), Main.dateFormatter.format(new Date())));
        donor.setTotalDonation(donor.getTotalDonation() + Integer.parseInt(line));
        totalDonation += Integer.parseInt(line);
        System.out.println("A donation worth $" + Integer.parseInt(line) + " was recorded in the system on behalf of " + donor.getName() + " " + donor.getSurname());
    }

    public void showDonations(){
        if(donations.size() == 0){
            System.out.println("There are NO donations. ");
            return;
        }
        System.out.println("------------------------------ Donations That Are Made ------------------------------");
        for (Donation donation : donations) {
            System.out.println("Name and surname of the Donor:  " + donation.getDonor().getName() + " " + donation.getDonor().getSurname() + " | ID: " +
                    donation.getDonor().getID() + " | Amount: $" + donation.getAmount() + " | Date: " + donation.getDonateDate());
        }
        System.out.println("Current Balance: $" + totalDonation);
    }

    /* Source Methods */
    public void addSource() throws Exception{
        int answer, number;
        boolean flag = true;

        do {
            System.out.println("• Press 1 to buy dog food,\n• Press 2 to buy cat food,\n• Press 3 to buy bird food: ");
            answer = Main.input.nextInt();
            Main.input.nextLine(); //reads enter on integer read
            switch (answer) {
                case 1:
                    System.out.println("A dog food is " + source.getDogFood().getAmount() + " grams and costs $" + source.getDogFood().getPrice() + "." +
                            " Current Balance: $" + totalDonation + ". How many dog foods do you want to buy? ");
                    number = Main.input.nextInt();
                    Main.input.nextLine(); //reads enter on integer read
                    if (totalDonation >= number * source.getDogFood().getPrice()) {
                        source.addDogFood(number);
                        totalDonation -= number * source.getDogFood().getPrice();
                        System.out.println(number + " pieces of dog food were bought and now there is " + source.getDogFoodAmount() + " grams of dog food in stock. " +
                                "Current Balance: $" + totalDonation + ".");
                    } else {
                        System.out.println("Unfortunately, the purchase was NOT MADE because the balance was insufficient!!!");
                    }
                    flag = false;
                    break;
                case 2:
                    System.out.println("A cat food " + source.getCatFood().getPrice() + " grams and costs $" + source.getCatFood().getPrice() + "." +
                            " Current Balance: $" + totalDonation + ". How many cat foods do you want to buy? ");
                    number = Main.input.nextInt();
                    Main.input.nextLine(); //reads enter on integer read
                    if (totalDonation >= number * source.getCatFood().getPrice()) {
                        source.addCatFood(number);
                        totalDonation -= number * source.getCatFood().getPrice();
                        System.out.println(number + " pieces of cat food were bought and now there is  " + source.getCatFoodAmount() + " grams of cat food in stock. " +
                                "Current Balance: $" + totalDonation + ".");
                    } else {
                        System.out.println("Unfortunately, the purchase was NOT MADE because the balance was insufficient!!!");
                    }
                    flag = false;
                    break;
                case 3:
                    System.out.println("A bird food " + source.getBirdFood().getPrice() + " grams and costs $" + source.getBirdFood().getPrice() + "." +
                            " Current Balance: $" + totalDonation + ". How many bird foods do you want to buy? ");
                    number = Main.input.nextInt();
                    Main.input.nextLine(); //reads enter on integer read
                    if (totalDonation >= number * source.getBirdFood().getPrice()) {
                        source.addBirdFood(number);
                        totalDonation -= number * source.getBirdFood().getPrice();
                        System.out.println(number + " pieces of bird food were bought and there is " + source.getBirdFoodAmount() + " grams of bird food in stock. " +
                                "Current Balance: $" + totalDonation + ".");
                    } else {
                        System.out.println("Unfortunately, the purchase was NOT MADE because the balance was insufficient!!!");
                    }
                    flag = false;
                    break;
                default:
                    System.out.println("You made a wrong keystroke. Try again...");
                    break;
            }
        }while (flag);
    }

    public void showSource(){
        System.out.println("--------------- Amounts of Dog, Cat and Bird Food In the Stock  ---------------");
        System.out.println("• Dog Food: " + source.getDogFoodAmount() + " gr\n• Cat Food: " + source.getCatFoodAmount() + " gr\n• Bird Food: " +
                source.getBirdFoodAmount());
    }

    /* Animal Adoption Methods */
    public void makeOwner() throws Exception{
        boolean flag = true;
        int ID, answer;
        Person owner;
        do{
            System.out.println("• Press 1 to adopt a dog,\n• Press 2 to adopt a cat,\n• Press 3 to adopt a bird: ");
            answer = Main.input.nextInt();
            Main.input.nextLine(); //reads enter on integer read
            switch (answer){
                case 1:
                    showDogs();
                    System.out.println("Enter the ID of the dog you want to adopt: ");
                    ID = Main.input.nextInt();
                    Main.input.nextLine(); //reads enter on integer read
                    Dog dog = findAnimal(String.format("%04d", ID));
                    if(dog == null){
                        System.out.println("NO such dog was found!!!");
                    }else{
                        System.out.println("Enter the ID(SSN) number of the person who will be the owner: ");
                        owner = findPerson(Main.input.nextLine());
                        if(owner == null){
                            System.out.println("Since this person is not registered in the system, you must register this person.");
                            owner = createPerson();
                            addPerson(owner);
                        }
                        if(owner.getAnimalID() == null){
                            dog.setOwnershipDate(Main.dateFormatter.format(new Date()));
                            dog.setOwner(owner);
                            owner.setAnimalID(dog.getID(), "Dog");
                            animalsOwnedByPerson.add(dog);
                            dogs.remove(dog.getID());
                            System.out.println(owner.getName() + " " + owner.getSurname() + " adopted a dog with breed " + dog.getKind() + " .");
                        }else{
                            System.out.println("This person already owns a pet!!");
                        }

                    }
                    flag = false;
                    break;
                case 2:
                    showCats();
                    System.out.println("Enter the ID of the cat you want to adopt: ");
                    ID = Main.input.nextInt();
                    Main.input.nextLine(); //reads enter on integer read
                    Cat cat = findAnimal(String.format("%04d", ID));
                    if(cat == null){
                        System.out.println("NO such cat was found!!");
                    }else{
                        System.out.println("Enter the ID(SSN) number of the person who will be the owner: ");
                        owner = findPerson(Main.input.nextLine());
                        if(owner == null){
                            System.out.println("Since this person is not registered in the system, you must register this person.");
                            owner = createPerson();
                            addPerson(owner);
                        }
                        if(owner.getAnimalID() == null){
                            cat.setOwnershipDate(Main.dateFormatter.format(new Date()));
                            cat.setOwner(owner);
                            owner.setAnimalID(cat.getID(), "Cat");
                            animalsOwnedByPerson.add(cat);
                            cats.remove(cat.getID());
                            System.out.println(owner.getName() + " " + owner.getSurname() + " adopted a cat with breed " + cat.getKind() + " .");
                        }else{
                            System.out.println("This person already owns a pet!!");
                        }
                    }
                    flag = false;
                    break;
                case 3:
                    showBirds();
                    System.out.println("Enter the ID of the bird you want to adopt: ");
                    ID = Main.input.nextInt();
                    Main.input.nextLine(); //reads enter on integer read
                    Bird bird = findAnimal(String.format("%04d", ID));
                    if(bird == null){
                        System.out.println("NO such bird was found!!");
                    }else{
                        System.out.println("Enter the ID(SSN) number of the person who will be the owner: ");
                        owner = findPerson(Main.input.nextLine());
                        if(owner == null){
                            System.out.println("Since this person is not registered in the system, you must register this person.");
                            owner = createPerson();
                            addPerson(owner);
                        }
                        if(owner.getAnimalID() == null){
                            bird.setOwnershipDate(Main.dateFormatter.format(new Date()));
                            bird.setOwner(owner);
                            owner.setAnimalID(bird.getID(), "Bird");
                            animalsOwnedByPerson.add(bird);
                            birds.remove(bird.getID());
                            System.out.println(owner.getName() + " " + owner.getSurname() + " adopted a bird with breed " + bird.getKind() + " .");
                        }else{
                            System.out.println("This person already owns a pet!!");
                        }
                    }
                    flag = false;
                    break;
                default:
                    System.out.println("You made a wrong keystroke. Try again...");
                    break;
            }
        }while(flag);
    }

    public void showAnimalsOwnedByPerson(){
        if(animalsOwnedByPerson.size() == 0){
            System.out.println("There is NO adopted animal.");
            return;
        }
        System.out.println("---------------------- Adopted Animals ------------------------------------ ");
        for(Animal animal : animalsOwnedByPerson)
            System.out.println(animal + " | Kind: " + animal.getClass().getName());
        System.out.println("-----------------------------------------------------------------------------------\n");
    }

    /* Animal Feeding Methods */
    public <T extends Animal> void feedAnimal(T animal){
        if(animal instanceof Dog){
            if(source.getDogFoodAmount() >= animal.getFoodItAte()){
                System.out.println("The dog with " + animal.getID() + " ID number was fed.");
                System.out.println("Amount of Dog Food In Stock Before Feeding : " + source.getDogFoodAmount() + " gr.");
                source.setDogFoodAmount(source.getDogFoodAmount() - animal.getFoodItAte());
                System.out.println("Amount of Dog Food In Stock After Feeding : " + source.getDogFoodAmount() + " gr.");
            }else{
                System.out.println("There is NOT enough dog food in the stock. That's why the dog you wanted to feed could not be fed!!");
                System.out.println("Must be: " + animal.getFoodItAte() + " gr. In stock: " + source.getDogFoodAmount() + " gr.\n");
            }
        }else if(animal instanceof Cat){
            if(source.getCatFoodAmount() >= animal.getFoodItAte()){
                System.out.println("The cat with " + animal.getID() + " ID number was fed.");
                System.out.println("Amount of Cat Food In Stock Before Feeding : " + source.getCatFoodAmount() + " gr.");
                source.setCatFoodAmount(source.getCatFoodAmount() - animal.getFoodItAte());
                System.out.println("Amount of Cat Food In Stock After Feeding : " + source.getCatFoodAmount() + " gr.");
            }else{
                System.out.println("There is NOT enough cat food in the stock. That's why the cat you wanted to feed could not be fed!!");
                System.out.println("Must be: " + animal.getFoodItAte() + " gr. In stock: " + source.getCatFoodAmount() + " gr.\n");
            }
        }else if(animal instanceof Bird){
            if(source.getBirdFoodAmount() >= animal.getFoodItAte()){
                System.out.println("The bird with " + animal.getID() + " ID number was fed.");
                System.out.println("Amount of Bird Food In Stock Before Feeding : " + source.getBirdFoodAmount() + " gr.");
                source.setBirdFoodAmount(source.getBirdFoodAmount() - animal.getFoodItAte());
                System.out.println("Amount of Bird Food In Stock After Feeding : " + source.getBirdFoodAmount() + " gr.");
            }else{
                System.out.println("There is NOT enough bird food in the stock. That's why the bird you wanted to feed could not be fed!!");
                System.out.println("Must be: " + animal.getFoodItAte() + " gr. In stock: " + source.getBirdFoodAmount() + " gr.\n");
            }
        }
    }

    /* Animal Deletion Methods */
    public void removeDog(String ID){
        Dog dog = dogs.remove(ID);
        if(dog == null){
            System.out.println("A dog with this ID was NOT FOUND.");
            return;
        }

        System.out.println("A dog with " + ID + " ID number has been successfully deleted from the shelter.");
    }

    public void removeCat(String ID){
        Cat cat = cats.remove(ID);
        if(cat == null){
            System.out.println("A cat with this ID was NOT FOUND.");
            return;
        }
        System.out.println("A cat with " + ID + " ID number has been successfully deleted from the shelter.");
    }

    public void removeBird(String ID){
        Bird bird = birds.remove(ID);
        if(bird == null){
            System.out.println("A bird with this ID was NOT FOUND.");
            return;
        }
        System.out.println("A bird with " + ID + " ID number has been successfully deleted from the shelter.");
    }

    /* Donation Deletion Methods */
    private Donation findDonation(int amount, String ID){
        for(Donation donation : donations){
            if(donation.getAmount() == amount && donation.getDonor().getID().compareTo(ID) == 0){
                return donation;
            }
        }
        return null;
    }

    public void removeDonation() throws Exception{
        //Deletes the first match
        System.out.println("Enter the amount of the donation you want to delete and the ID(SSN) of the donor: ");
        String line = Main.input.nextLine();
        String[] words = line.split(" ");
        Donation donation = findDonation(Integer.parseInt(words[0]), words[1]);
        if(donation == null){
            System.out.println("NO such donation was found in the system.");
            return;
        }

        if(totalDonation < donation.getAmount()){
            System.out.println("This donation cannot be deleted at this time because there is not enough balance in the system.");
            return;
        }

        donations.remove(donation);
        totalDonation -= donation.getAmount();
        donation.getDonor().setTotalDonation(donation.getDonor().getTotalDonation() - donation.getAmount());
        System.out.println("The donation has been successfully deleted from the system and the balance has been updated.");
    }

    /* Person Deletion Method */
    public void removePerson(){
        System.out.println("Enter the ID(SSN) number of the person you want to delete: ");
        Person person = findPerson(Main.input.nextLine());
        if(person == null){
            System.out.println("This person was NOT FOUND in the system.");
            return;
        }

        if(person.getAnimalID() != null){
            System.out.println("Because this person has an animal, you CANNOT DELETE him/her from the system.");
            return;
        }

        if(person.getTotalDonation() != 0){
            System.out.println("Because this person has a donation in the system, you CANNOT DELETE him/her.");
            return;
        }

        persons.remove(person.getID());
        System.out.println("Person with ID(SSN) number " + person.getID() + " has been successfully deleted from the system.");
    }

    /* Username and Password Methods */
    public void showUsernameAndPassword(){
        System.out.println("Username: " + username + " | Password: " + password);
    }

    public void changeUsernameAndPassword(){
        System.out.println("Enter the new username and password with a space between them: ");
        String line = Main.input.nextLine();
        String[] words = line.split(" ");
        setUsername(words[0]);
        setPassword(words[1]);
        System.out.println("New username and password changed SUCCESSFULLY.");
    }
}

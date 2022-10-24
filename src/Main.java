/* Emircan KİREZ - Ch3rry */
/* Last Update: 28/07/2022 */
/*
----------------------- INFORMATION ---------------------------
• It is recommended to read the README.md file before proceeding to the project.
• For any questions and suggestions related to the project, via the e-mail address "emircan200123@hotmail.com", you can contact me by
typing "Github | Java-EN / Animal Adoption Project" to the subject line
*/

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static Scanner input = new Scanner(System.in); //Scanner object used to receive input from the user
    public static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
    public static void main(String[] args) {
        Company company = new Company("Animal Shelter Inc.");
        login(company);
        menu(company);
    }

    public static void login(Company company){
        System.out.println("In order to use the system, you must first log in. You have 3 rights.");
        int i = 0;
        while(i < 3){
            try{
                System.out.println("Enter username and password (Case Sensitive):");
                String line = input.nextLine();
                String[] words = line.split(" ");
                if(company.getUsername().compareTo(words[0]) == 0 && company.getPassword().compareTo(words[1]) == 0){
                    System.out.println("SUCCESSFUL\nYou are being transferred to the system...");
                    TimeUnit.SECONDS.sleep(2); //2 second hold - for reality
                    return;
                }else{
                    System.out.println("UNSUCCESSFUL");
                    i++;
                }

            }catch (Exception e){
                System.out.println("Something went wrong. Error reason:\n " + e);
            }
        }

        System.out.println("The system has shut itself down.");
        System.exit(0);
    }

    public static void menu(Company company){
        int option, answer;
        boolean flag = true;
        Dog dog;
        Cat cat;
        Bird bird;

        do{
            try{
                System.out.println("----------------------------- MENU ---------------------------\n• Press 1 to add animals to the shelter," +
                        "\n• Press 2 to add donations,\n• Press 3 to add resources,\n• Press 4 to adopt animals,\n• Press 5 to feed animals," +
                        "\n• Press 6 to delete animals from the shelter,\n• Press 7 to delete donations,\n• Press 8 to delete people," +
                        "\n• Press 9 to view animals in the shelter,\n• Press 10 to view adopted animals,\n• Press 11 to view the people registered in the system," +
                        "\n• Press 12 to view donations and current balance,\n• Press 13 to display username and password," +
                        "\n• Press 14 to change the username and password,\n• Press 0 to turn off the system:");
                option = input.nextInt();
                input.nextLine(); //reads enter on integer read
                switch (option){
                    case 0:
                        System.out.println("All information has been saved and the system has been shut down.");
                        company.writeToFile();
                        flag = false;
                        break;
                    case 1:
                        System.out.println("• Press 1 to add a dog,\n• Press 2 to add a cat,\n• Press 3 to add a bird: ");
                        answer = input.nextInt();
                        input.nextLine(); //reads enter on integer read
                        switch (answer){
                            case 1:
                                dog = company.createDog();
                                company.addAnimal(dog);
                                break;
                            case 2:
                                cat = company.createCat();
                                company.addAnimal(cat);
                                break;
                            case 3:
                                bird = company.createBird();
                                company.addAnimal(bird);
                                break;
                            default:
                                System.out.println("You made a wrong keystroke. Try again...");
                                break;
                        }
                        break;
                    case 2:
                        company.getDonation();
                        break;
                    case 3:
                        company.addSource();
                        break;
                    case 4:
                        company.makeOwner();
                        break;
                    case 5:
                        company.showDogs();
                        company.showCats();
                        company.showBirds();
                        System.out.println("Enter the ID of the animal you want to feed: ");
                        answer = input.nextInt();
                        input.nextLine(); //reads enter on integer read
                        company.feedAnimal(company.findAnimal(String.format("%04d", answer)));
                        break;
                    case 6:
                        System.out.println("• Press 1 to delete a dog,\n• Press 2 to delete a cat,\n• Press 3 to delete a bird:");
                        answer = input.nextInt();
                        input.nextLine(); //reads enter on integer read
                        switch (answer){
                            case 1:
                                company.showDogs();
                                System.out.println("Enter the ID of the dog you want to delete: ");
                                answer = input.nextInt();
                                input.nextLine();
                                company.removeDog(String.format("%04d", answer));
                                break;
                            case 2:
                                company.showCats();
                                System.out.println("Enter the ID of the cat you want to delete: ");
                                answer = input.nextInt();
                                input.nextLine();
                                company.removeCat(String.format("%04d", answer));
                                break;
                            case 3:
                                company.showBirds();
                                System.out.println("Enter the ID of the bird you want to delete: ");
                                answer = input.nextInt();
                                input.nextLine();
                                company.removeBird(String.format("%04d", answer));
                                break;
                            default:
                                System.out.println("You made a wrong keystroke. Try again...");
                                break;
                        }
                        break;
                    case 7:
                        company.showDonations();
                        company.removeDonation();
                        break;
                    case 8:
                        company.showPersons();
                        company.removePerson();
                        break;
                    case 9:
                        company.showDogs();
                        company.showCats();
                        company.showBirds();
                        break;
                    case 10:
                        company.showAnimalsOwnedByPerson();
                        break;
                    case 11:
                        company.showPersons();
                        break;
                    case 12:
                        company.showDonations();
                        break;
                    case 13:
                        company.showUsernameAndPassword();
                        break;
                    case 14:
                        company.changeUsernameAndPassword();
                        break;
                }
            }catch (Exception e){
                System.out.println("Something went wrong. Error reason:\n" + e);
            }
        }while(flag == true);
    }
}

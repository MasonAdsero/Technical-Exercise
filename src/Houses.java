import java.util.HashMap;
import java.util.Vector;
import java.util.Comparator;
import java.util.Collections;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.StandardOpenOption;

public class Houses {
    private HashMap<String, Vector<Person>> residents;
    private Vector<String> houses;

    //Houses requires an array of people to put in houses from the script
    public Houses(Vector<Person> people){
        this.residents = new HashMap<String, Vector<Person>>();
        this.houses = new Vector<String>();
        mapPeopleToHouse(people);
        sortResidents();
    };

    //Function to map people to their house and store the information in an array in the hashmap
    private void mapPeopleToHouse(Vector<Person> people){
        for(Person person : people){
            String address = person.getFullAddress();
            if(residents.containsKey(address)){
                residents.get(address).add(person);
            } else {
                Vector<Person> housePeople = new Vector<Person>();
                housePeople.add(person);
                residents.put(address, housePeople);
                houses.add(address);
            }
        }
    }

    //Function to sort residents according to requirements
    private void sortResidents(){
        Comparator<Person> nameCompare = Comparator.comparing(Person::getLast).thenComparing(Person::getFirst);
        for(String house : houses){
            Vector<Person> people = residents.get(house);
            Collections.sort(people, nameCompare);
        }
    }

    //Function to print the houses and people older than 18
    //Assumptions:
    //  People under 18 should still be included in the house population count.
    //  Houses should still be displayed even with nobody over 18.
    public void printHouses(){
        for(String house : houses){
            Vector<Person> currHouse = residents.get(house);
            System.out.println("House Address: " + house + " Number of Occupents: " + currHouse.size());
            for(Person person : currHouse){
                if(person.getAge() > 18)
                    person.printPerson();
            }
            System.out.println();
        }
    }

    public void writeFile(){
        Path filePath = Path.of("houses.txt");
        try {
            Files.write(filePath, "".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            for(String house : houses) {
                Vector<Person> currHouse = residents.get(house);
                String houseAddr = "House Address: " + house + " Number of Occupents: " + currHouse.size() + "\n";
                Files.write(filePath, houseAddr.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                for(Person person : currHouse){
                    if(person.getAge() > 18) {
                        String personInfo = person.getFirst() + ", " +
                                person.getLast() + ", " +
                                person.getAddress() + ", " +
                                person.getCity() + ", " +
                                person.getState() + ", " +
                                person.getAge() + "\n";
                        Files.write(filePath,personInfo.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    }
                }
                Files.write(filePath,"\n".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}

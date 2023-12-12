import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;


public class HouseParser {
    private static Vector<Person> scanLine(Vector<Person> people, String dataLine){
        Scanner data = new Scanner(dataLine);
        data.useDelimiter("\",\"|\"");
        String first = data.next();
        String last = data.next();
        String addr = data.next().trim();
        String city = data.next().trim();
        String state = data.next().trim();
        int age = data.nextInt();
        people.add(new Person(first, last, addr, city, state, age));
        data.close();
        return people;
    }

    public static Vector<Person> scan(){
        Vector<Person> people = new Vector<Person>();
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            while(scanner.hasNextLine())
            {
                String dataLine = scanner.nextLine();
                if(dataLine.trim().isEmpty()) break;
                scanLine(people, dataLine);
            }
            scanner.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
            System.exit(1);
        }
        return people;
    }

    public static void main(String[] args) throws Exception {
        Vector<Person> people = scan();
        Houses houses = new Houses(people);
        houses.printHouses();
        houses.writeFile();
    }
}
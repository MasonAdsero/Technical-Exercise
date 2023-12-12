import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.Vector;
public class test {
    @Test
    public void TestPersonConstructor(){
        String expectedPerson = "Alice, Smith, 123 main st, Seattle, WA, 45";
        String first = "Alice";
        String last = "Smith";
        String addr = "123 main st";
        String city = "Seattle";
        String state = "WA";
        int age =  45;

        Person person = new Person("Alice","Smith","123 main st.","seattle","wa",45);

        assertEquals(first, person.getFirst());
        assertEquals(last, person.getLast());
        assertEquals(addr, person.getAddress());
        assertEquals(city, person.getCity());
        assertEquals(state, person.getState());
        assertEquals(age, person.getAge());
    }

    @Test
    public void TestFullAddress(){
        String expectedAddr = "123 main st, Seattle, WA";

        Person person = new Person("Alice","Smith","123 main st.","seattle","wa",45);

        assertEquals(expectedAddr, person.getFullAddress());
    }

    //This test assumes the input file has at least three entries to test against
    @Test
    public void TestHouseParse(){
        HouseParser parser = new HouseParser();
        Vector<Person> person = parser.scan();

        Vector<Person> expected = new Vector<Person>();
        expected.add(new Person("Dave","Smith","123 main st.","seattle","wa",43));
        expected.add(new Person("Alice","Smith","123 Main St.","Seattle","WA",45));
        expected.add(new Person("Bob","Williams","234 2nd Ave.","Tacoma","WA",26));

        for(int i = 0; i < expected.size(); i++){
            assertEquals(expected.get(i).getFirst(), person.get(i).getFirst());
            assertEquals(expected.get(i).getLast(), person.get(i).getLast());
            assertEquals(expected.get(i).getAddress(), person.get(i).getAddress());
            assertEquals(expected.get(i).getCity(), person.get(i).getCity());
            assertEquals(expected.get(i).getState(), person.get(i).getState());
            assertEquals(expected.get(i).getAge(), person.get(i).getAge());
        }
    }

}

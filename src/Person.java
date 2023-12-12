public class Person  {
    private String first;
    private String last;
    private String address;
    private String city;
    private String state;
    private int age;

    public Person(String first, String last, String address, String city, String state, int age){
        this.first = first;
        this.last = last;
        this.address = address;
        this.city = city;
        this.state = state;
        this.age = age;
        normalize();
    }

    //Need to account for addresses in different formats for same place so
    //we make addresses similar to each other
    private void normalize(){
        address = address.replace(".", "");
        address = address.replace(",", "");
        address = address.toLowerCase();
        city = city.substring(0,1).toUpperCase() + city.substring(1);
        state = state.toUpperCase();
    }

    //Print needed information
    public void printPerson(){
        System.out.println(first + ", " +
                last + ", " +
                address + ", " +
                city + ", " +
                state + ", " +
                age);
    }

    //Get Address for House class to sort people into houses
    public String getFullAddress(){
        return address + ", " + city + ", " + state;
    }

    public String getFirst(){
        return first;
    }

    public String getLast(){
        return last;
    }

    public String getAddress(){
        return address;
    }
    public String getCity(){
        return city;
    }

    public String getState(){
        return state;
    }

    public int getAge(){
        return age;
    }
}
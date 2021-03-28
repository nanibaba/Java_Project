/*This program generates arrays of developer and freelancer objects
* and prints their business cards. First, it creates a base class
* Employee and its child classes Developer and Freelancer.
* The child classes have access to the public getter and setter
* methods of the base class but cannot access its private variables
* name, phone, address, email, id, and type.
* Both the Developer and Freelancer classes override the setSalary
* method to calculate the employees' salaries a on daily and hourly basis,
* respectively.
 */

//Importing Date class to use it for date properties
import java.util.Date;

public class Employee {

    //Declaring Employee properties

    private String name, phone, address, email;
    private int id;
    protected double salary;
    private String[] type;

//Constructors

    public Employee(){
    }

    public Employee(String name, String phone, int id, String address,
                    String email, double salary, String[] type){

        this.name = name;
        this.phone = phone;
        this.id = id;
        this.address = address;
        this.email = email;
        this.salary = salary;
        this.type = type;

    }

    //Getter and Setter Methods

    public String getName(){
        return this.name;
    }

    public void setName (String name){
        this.name = name;
    }

    public String getPhone(){
        return this.phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public int getId(){
        return this.id;
    }

    public void setId (int id){
        this.id = id;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress (String address){
        this.address = address;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail (String email){
        this.email = email;
    }

    public String[] getType(){
        return this.type;
    }

    public void setType (String[] type){
        this.type = type;
    }

    public double getSalary(){
        return this.salary;
    }

    public void setSalary (double salary){
        this.salary = salary;
    }

    public static void main(String[] args) {

        //Declaring arrays of developer and freelancer objects

        Developer[] developers = new Developer[5];
        Freelancer[] freelancers = new Freelancer[5];

//Setting developer properties individually

        developers[0] = new Developer();
        developers[0].setName("John");
        developers[0].setEmail("john@aubg.edu");
        developers[0].setDays(230);
        developers[0].setSalary(205);

        developers[1] = new Developer();
        developers[1].setName("Tony");
        developers[1].setEmail("tony@aubg.edu");
        developers[1].setDays(235);
        developers[1].setSalary(205);

        developers[2] = new Developer();
        developers[2].setName("Amanda");
        developers[2].setEmail("amanda@aubg.edu");
        developers[2].setDays(225);
        developers[2].setSalary(205);

        developers[3] = new Developer();
        developers[3].setName("Peter");
        developers[3].setEmail("peter@aubg.edu");
        developers[3].setDays(220);
        developers[3].setSalary(205);

        developers[4] = new Developer();
        developers[4].setName("Gina");
        developers[4].setEmail("gina@aubg.edu");
        developers[4].setDays(227);
        developers[4].setSalary(205);

//Setting freelancer properties individually

        freelancers[0] = new Freelancer();
        freelancers[0].setName("Kim");
        freelancers[0].setAddress("1 Ave");
        freelancers[0].setId(1);
        freelancers[0].setHours(38);
        freelancers[0].setSalary(12.5);

        freelancers[1] = new Freelancer();
        freelancers[1].setName("Jim");
        freelancers[1].setAddress("2 Ave");
        freelancers[1].setId(2);
        freelancers[1].setHours(45);
        freelancers[1].setSalary(12.5);

        freelancers[2] = new Freelancer();
        freelancers[2].setName("Bob");
        freelancers[2].setAddress("3 Ave");
        freelancers[2].setId(3);
        freelancers[2].setHours(33);
        freelancers[2].setSalary(12.5);

        freelancers[3] = new Freelancer();
        freelancers[3].setName("Tom");
        freelancers[3].setAddress("4 Ave");
        freelancers[3].setId(4);
        freelancers[3].setHours(40);
        freelancers[3].setSalary(12.5);

        freelancers[4] = new Freelancer();
        freelancers[4].setName("Sara");
        freelancers[4].setAddress("5 Ave");
        freelancers[4].setId(5);
        freelancers[4].setHours(42);
        freelancers[4].setSalary(12.5);

//Using for-each loops to print all developer and freelancer properties
//on a separate line

        System.out.println("Developers: ");

        for (Developer developer: developers) {
            System.out.println(developer.getName());
            System.out.println(developer.getEmail());
            System.out.println(developer.getSalary());
            System.out.println();
        }

        System.out.println("Freelancers: ");

        for (Freelancer freelancer:freelancers) {
            System.out.println(freelancer.getName());
            System.out.println(freelancer.getAddress());
            System.out.println(freelancer.getId());
            System.out.println(freelancer.getSalary());
            System.out.println();
        }
    }
}

class Developer extends Employee {

    //Declaring Developer properties

    private Date dateHired;
    private String language;
    private String[] department;
    private String currentProject;
    private double salary;
    private int days;

    //No-arg constructor

    public Developer(){
    }

//Getter and setter methods

    public int getDays(){
        return this.days;
    }

    public void setDays(int days){
        this.days = days;
    }

    public double getSalary(){
       return this.salary;
    }

    //Overridden setSalary method

    @Override
    public void setSalary(double salary){
        this.salary = this.days * salary;
    }
}

class Freelancer extends Employee {

    //Declaring Freelancer properties

    private Date dateHired;
    private String projectHiredFor;
    private Date startingDate;
    private Date endingDate;
    private int hours;

    //No-arg constructor

    public Freelancer(){
    }

    //Getter and setter methods

    public int getHours(){
       return this.hours;
    }

    public void setHours(int hours){
        this.hours = hours;
    }

    //Overridden setSalary method
    @Override
    public void setSalary(double salary){
       this.salary = this.hours * salary;
    }
}


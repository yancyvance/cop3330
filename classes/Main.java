
public class Main {
 
    public static void main(String[] args) {
        Person p; // = new Person();
        p = new Person("John", 10);
        
        Person q = new Person();
        
        //p.name = "John";
        //p.rate = 10;
        
        //p.setName("John");
        //p.setRate(10);
        
        //p.setInformation("John", 10);
        
        //System.out.println(p.name + " " + p.rate);
        System.out.println(p.getName() + " " + p.getRate() + " " + p.getSalary(2));
        System.out.println(q.getName() + " " + q.getRate());
    }
    
}

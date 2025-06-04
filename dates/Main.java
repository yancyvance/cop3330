
public class Main {
    
    public static void main(String[] args) {
        
        String s1 = "5/29/2025";
        String s2 = "2/28/2024";
        String s3 = "12/31/2024";
        
        // TODO: Convert to Date objects
        MyDate d1 = new MyDate(5, 29, 2025);
        MyDate d3 = new MyDate(6, 3, 2025);
        //MyDate d2 = d1.duplicate();
        
        System.out.println(d1.getDifference(d3));
        System.out.println(d3.getDifference(d1));
        
        System.out.println(d1 + " -> " + d1.getNextDay());
        
        d1.goToNextDay();
        System.out.println(d1);
        

        System.out.println(d1.getNextDay());
        System.out.println(d1.getNextDay().getNextDay());
        System.out.println(d1.getNextDay().getNextDay().getNextDay());
        System.out.println(d1.getNextDay(3));
        //d2.setYear(2024);
        
        //System.out.println(d1);
        //System.out.println(d2);
        
        // TODO: Put in an array
        
        // TODO: Print the dates and their next dates
        
        // TODO: Sort the dates in ascending order
    }
    
}

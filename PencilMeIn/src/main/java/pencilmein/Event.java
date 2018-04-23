package pencilmein;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class Event {
    String name;
    TreeSet<Integer> times;
            
    public Event (String n, ArrayList<Day> d, int sh, int sm, int eh, int em) {
        name = n;
        times = getTimes(d, sh, sm, eh, em);
        
        System.out.print(n + " " + d.get(0) + " " + sh + " " + sm + " " + eh + " " + em);
    }
    
    private TreeSet<Integer> getTimes(ArrayList<Day> d, int sh, int sm, int eh, int em) {
        
        TreeSet<Integer> converted = new TreeSet<Integer>();
        
        for (Day day : d) {
            Integer dayInt = 0;
            switch(day) {
            case SUNDAY: dayInt += 0;
            break;
            case MONDAY: dayInt += 10000;
            break;
            case TUESDAY: dayInt += 20000;
            break;
            case WEDNESDAY: dayInt += 30000;
            break;
            case THURSDAY: dayInt += 40000;
            break;
            case FRIDAY: dayInt += 50000;
            break;
            case SATURDAY: dayInt += 60000;
            break;
        }
            
            System.out.println("Integer for day is " + dayInt);
            
            while (sh < eh || sm < em) {
                
                System.out.println("SH " + sh + " EH " + eh + " SM " + sm + " EM " + em);
                
                Integer add = dayInt;
                add += sh*100;
                add += sm;
                sm += 15;
                if (sm == 60) {
                    sm = 0;
                    sh++;
                }
                if (sh == 24) {
                    sh = 0;
                }
                converted.add(add);

                System.out.println("Added this time " + add);

            }
        }
        return converted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public TreeSet<Integer> getTimes() {
        return times;
    }
}

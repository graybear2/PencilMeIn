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
    }
    
    private TreeSet<Integer> getTimes(ArrayList<Day> d, int sh, int sm, int eh, int em) {
        
        TreeSet<Integer> converted = new TreeSet<Integer>();
        
        for (Day day : d) {
            Integer dayInt = 0;
            switch(day) {
            case SUNDAY: dayInt += 0;
            case MONDAY: dayInt += 010000;
            case TUESDAY: dayInt += 020000;
            case WEDNESDAY: dayInt += 030000;
            case THURSDAY: dayInt += 040000;
            case FRIDAY: dayInt += 050000;
            case SATURDAY: dayInt += 060000;
            }
            
            while (sh < eh || sm < em) {
                Integer add = dayInt;
                add += sh*100;
                add += sm;
                sm += 15;
                if (sm == 60) {
                    sm = 0;
                    sh++;
                }
                converted.add(add);
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

package pencilmein;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Serialize;

public class Schedule {
    @Id Long id;
    
    private static final long serialVersionUID = 1L;
    
    @Serialize ArrayList<Event> schedule;
    
    public Schedule() {
        schedule = new ArrayList<Event>();
    }
    
    public void addEvent(Event e) {
        schedule.add(e);
    }
    
    public ArrayList<Event> getEvents() {
        return schedule;
    }

    public static HashMap<Integer, Integer> scheduleMerge(ArrayList<Student> students) {
        HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
        for (Student s : students) {
            for (Event e : s.getSchedule().getEvents()) {
                System.out.println("Event with time starting here " + e.getTimes().first());
                add(e, result);
            }
        }
        return result;
    }
    
    private static void add(Event e, HashMap<Integer, Integer> result) {
        for (Integer t : e.times) {
            if (result.containsKey(t)) {
                int temp = result.get(t);
                System.out.println("Has time " + t + " with " + temp + " people");
                temp++;
                result.put(t, temp);
            }
            else {
                System.out.println("Doesn't have time " + t);
                result.put(t, 1);
            }
        }
    }
   
    /*
    public static void main (String[] args) {
        Student one = new Student();
        ArrayList<Day> sched1 = new ArrayList<Day>();
        sched1.add(Day.MONDAY);
        sched1.add(Day.WEDNESDAY);
        one.schedule.addEvent(new Event("Random1", sched1, 14, 0, 17, 30));
        
        Student two = new Student();
        ArrayList<Day> sched2 = new ArrayList<Day>();
        sched2.add(Day.MONDAY);
        sched2.add(Day.WEDNESDAY);
        two.schedule.addEvent(new Event("Random2", sched2, 13, 0, 18, 00));
        
        Student three = new Student();
        ArrayList<Day> sched3 = new ArrayList<Day>();
        sched3.add(Day.MONDAY);
        sched3.add(Day.THURSDAY);
        three.schedule.addEvent(new Event("Random3", sched3, 12, 0, 15, 0));
        
        
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(one);
        students.add(two);
        students.add(three);
        HashMap<Integer, Integer> merged = scheduleMerge(students);
        
        System.out.println("Merging");
        System.out.println("Num entries " + merged.size());
        for (Map.Entry<Integer, Integer> entry : merged.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(value + " poeple unavailable at " + key + " time");
        }
    }
    */
}

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

    private static final boolean DEBUG = true;
    
    @Serialize ArrayList<Event> schedule;
    int eventId;
    
    public Schedule() {
        schedule = new ArrayList<Event>();
        eventId = 0;
    }
    
    public void addEvent(Event e) {
    	e.setId(eventId);
    	eventId++;
        schedule.add(e);
    }
    
    public void overwriteEvent(Event e, int eventId) {
    	removeEvent(getEvent(eventId));
    	e.setId(eventId);
    	eventId++;
    	schedule.add(e);
    }
    
    public void removeEvent(Event e) {
    	getEvents().remove(e);
    }
    
    public Event getEvent(int eventId) {
    	for(Event e: getEvents()) {
    		if(e.getId() == eventId)
    			return e;
    	}
    	System.out.println("COULDN'T FIND THE EVENT TO REMOVE");
    	return null;
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
    
    public static synchronized String howBusy(Integer numSelectedFriends, Integer numBusyFriends) {
        double proportionalBusy = numBusyFriends.doubleValue()/numSelectedFriends.doubleValue();
        
        if(DEBUG) {
            System.out.println("Proportion busy is: " + proportionalBusy);
        }
        
        if(proportionalBusy == 0) {
            return "free";
        }
        else if((proportionalBusy > 0) && (proportionalBusy <= .25)) {
            return "freequarterbusy";
        }
        else if((proportionalBusy > .25) && (proportionalBusy <= .50)) {
            return "quarterhalfbusy";
        }
        else if((proportionalBusy > .50) && (proportionalBusy <= .75)) {
            return "halfthreequarterbusy";
        }
        else if((proportionalBusy > .75) && (proportionalBusy < 1)) {
            return "threequarterallbusy";
        }
        else if(proportionalBusy >= 1) {
            return "allbusy";
        }
        
        return "free";
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

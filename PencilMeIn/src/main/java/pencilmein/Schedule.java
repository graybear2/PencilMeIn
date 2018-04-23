package pencilmein;

import java.util.ArrayList;

public class Schedule {
    ArrayList<Event> schedule;
    
    public Schedule() {
        schedule = new ArrayList<Event>();
    }
    
    public void addEvent(Event e) {
        schedule.add(e);
    }
    
    public ArrayList<Event> getEvents() {
        return schedule;
    }

    // public scheduleCompare() <-- eventually, this is where we will put schedule compare algorithm to output mutual free blocks
}

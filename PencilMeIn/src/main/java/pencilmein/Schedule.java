package pencilmein;

import java.util.ArrayList;
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

    // public scheduleCompare() <-- eventually, this is where we will put schedule compare algorithm to output mutual free blocks
}

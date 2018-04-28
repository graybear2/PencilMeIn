package pencilmein;

import java.util.ArrayList;
import java.util.SortedSet;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Serialize;

public class Schedule {
    @Id Long id;
    int size;
    
    private static final long serialVersionUID = 1L;
    
    @Serialize ArrayList<Event> schedule;
    
    public Schedule() {
        schedule = new ArrayList<Event>();
        size = 0;
    }
    
    public void addEvent(Event e) {
        schedule.add(e);
        size++;
    }
    
    public ArrayList<Event> getEvents() {
        return schedule;
    }
    
    public int getSize(){
    	return size;
    }

    // public scheduleCompare() <-- eventually, this is where we will put schedule compare algorithm to output mutual free blocks
}

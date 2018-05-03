package pencilmein;

import java.util.ArrayList;
import java.util.SortedSet;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Serialize;

public class Schedule {
    @Id Long id;
    
    private static final long serialVersionUID = 1L;
    
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

    // public scheduleCompare() <-- eventually, this is where we will put schedule compare algorithm to output mutual free blocks
}

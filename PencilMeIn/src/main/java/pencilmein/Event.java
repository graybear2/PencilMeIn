package pencilmein;

import java.util.SortedSet;

public class Event {
    String name;
    int day; // 0 = Sunday, 1 = Monday, 2 = Tuesday, etc. 
    int startHour;
    int startMinute;
    int endHour;
    int endMinute;
    
    boolean am;
    
    SortedSet<Integer> times;
    
    public Event (String n, int d, int sh, int sm, int eh, int em) {
        name = n;
        day = d;
        startHour = sh;
        startMinute = sm;
        endHour = eh;
        endMinute = em;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getDay() {
        return day;
    }
    
    public void setDay(int day) {
        this.day = day;
    }

    public int getShour() {
        return startHour;
    }

    public void setShour(int shour) {
        this.startHour = shour;
    }

    public int getSminute() {
        return startMinute;
    }

    public void setSminute(int sminute) {
        this.startMinute = sminute;
    }

    public int getEhour() {
        return endHour;
    }

    public void setEhour(int ehour) {
        this.endHour = ehour;
    }

    public int getEminute() {
        return endMinute;
    }

    public void setEminute(int eminute) {
        this.endMinute = eminute;
    }
    
}

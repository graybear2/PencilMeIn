package pencilmein;

public class Event {
    String name;
    int hour;
    int minute;
    
    public Event (String n, int h, int m) {
        name = n;
        hour = h;
        minute = m;        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
    
    
}

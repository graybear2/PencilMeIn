package pencilmein;

public class Event {
    String name;
    int day; // 1 = Monday, 2 = Tuesday, etc. 
    int hour;
    int minute;
    
    public Event (String n, int d, int h, int m) {
        name = n;
        day = d;
        hour = h;
        minute = m;        
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

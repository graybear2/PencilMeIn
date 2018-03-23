package pencilmein;

public class Event {
    String name;
    int day; // 1 = Monday, 2 = Tuesday, etc. 
    int shour;
    int sminute;
    int ehour;
    int eminute;
    
    public Event (String n, int d, int sh, int sm, int eh, int em) {
        name = n;
        day = d;
        shour = sh;
        sminute = sm;
        ehour = eh;
        eminute = em;
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
        return shour;
    }

    public void setShour(int shour) {
        this.shour = shour;
    }

    public int getSminute() {
        return sminute;
    }

    public void setSminute(int sminute) {
        this.sminute = sminute;
    }

    public int getEhour() {
        return ehour;
    }

    public void setEhour(int ehour) {
        this.ehour = ehour;
    }

    public int getEminute() {
        return eminute;
    }

    public void setEminute(int eminute) {
        this.eminute = eminute;
    }
    
}

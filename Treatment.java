package model;

import java.time.LocalDateTime;

public class Treatment {
    private String name;
    private LocalDateTime time;

    public Treatment(String name, LocalDateTime time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return name + " at " + time;
    }
}

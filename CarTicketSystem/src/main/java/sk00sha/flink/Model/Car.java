package sk00sha.flink.Model;

import java.io.Serializable;

public class Car implements Serializable {
    private int id;
    private String color;
    private double speed;
    public Car() {}
    public Car(int id, String color, double speed) {
        this.id = id;
        this.color = color;
        this.speed = speed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", speed=" + speed +
                '}';
    }
}

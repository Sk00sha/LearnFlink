package sk00sha.flink.Model;

public class CarWithSpeed {
    private int id;
    private boolean wasSpeeding;
    public CarWithSpeed(){}
    public CarWithSpeed(int id, boolean wasSpeeding) {
        this.id = id;
        this.wasSpeeding = wasSpeeding;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CarWithSpeed{" +
                "id=" + id +
                ", wasSpeeding=" + wasSpeeding +
                '}';
    }

    public boolean isWasSpeeding() {
        return wasSpeeding;
    }

    public void setWasSpeeding(boolean wasSpeeding) {
        this.wasSpeeding = wasSpeeding;
    }
}

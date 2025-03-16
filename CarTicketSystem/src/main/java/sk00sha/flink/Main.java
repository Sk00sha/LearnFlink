package sk00sha.flink;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import sk00sha.flink.Model.Car;
import sk00sha.flink.Model.CarWithSpeed;
import sk00sha.flink.transformations.TicketValidationFunction;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<Car> carStream = env.fromData(new ArrayList<>(List.of(new Car(1,"Red",123),new Car(2,"Blue",33),new Car(2,"Blue",154))));
        var carWithSpeedKeyedStream = carStream.keyBy(Car::getId);
        var a = carWithSpeedKeyedStream.process(new TicketValidationFunction(50));
        var b = a.filter(CarWithSpeed::isWasSpeeding);

        a.print();
        env.execute();
    }
}
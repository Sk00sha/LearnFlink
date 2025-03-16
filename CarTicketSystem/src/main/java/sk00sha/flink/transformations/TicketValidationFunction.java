package sk00sha.flink.transformations;

import org.apache.flink.api.common.functions.OpenContext;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;
import sk00sha.flink.Model.Car;
import sk00sha.flink.Model.CarWithSpeed;

public class TicketValidationFunction extends KeyedProcessFunction<Integer, Car, CarWithSpeed> {
    private final int speedLimit;


    public TicketValidationFunction(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    @Override
    public void processElement(Car car, KeyedProcessFunction<Integer, Car, CarWithSpeed>.Context context, Collector<CarWithSpeed> collector) throws Exception {

    }

    @Override
    public void open(OpenContext openContext) throws Exception {
        super.open(openContext);
    }
}

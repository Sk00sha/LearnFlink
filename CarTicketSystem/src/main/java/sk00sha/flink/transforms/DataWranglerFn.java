package sk00sha.flink.transforms;

import org.apache.flink.api.common.functions.OpenContext;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;
import sk00sha.flink.Model.Car;
import sk00sha.flink.Model.CarWithSpeed;

public class DataWranglerFn extends KeyedProcessFunction<Integer, Car, CarWithSpeed> {
    private final int speedLimit;
    private ValueState<Boolean> valueState;
    private ValueStateDescriptor<Boolean> valueStateDescriptor;

    public DataWranglerFn(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    @Override
    public void open(OpenContext openContext) throws Exception {
        valueStateDescriptor = new ValueStateDescriptor<Boolean>("CarStateDescriptor", TypeInformation.of(Boolean.class));
        valueState = getRuntimeContext().getState(valueStateDescriptor);
    }

    @Override
    public void processElement(Car car, KeyedProcessFunction<Integer, Car, CarWithSpeed>.Context context, Collector<CarWithSpeed> collector) throws Exception {
        valueState.update(car.getSpeed()>this.speedLimit);
        System.out.println(String.format("ID: %d Had ticket: %s",car.getId(),valueState.value()));
        collector.collect(new CarWithSpeed(car.getId(),car.getSpeed()>this.speedLimit));
    }
}

package sk00sha.flink;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class Runner {
    public static void main(String[] args) throws Exception {

        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<PropertiesConfiguration> builder =
                new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
                        .configure(params.properties().setFileName("config.properties"));
        Configuration config = builder.getConfiguration();

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> carDataStream= env.fromSource(createKafkaSource(config), WatermarkStrategy.noWatermarks(),config.getString("app.name"), TypeInformation.of(String.class));
        carDataStream.print();
        env.execute();
    }

    private static KafkaSource<String> createKafkaSource(Configuration config){
        return KafkaSource.<String>builder()
                .setBootstrapServers(config.getString("kafka.broker"))
                .setTopics(config.getString("kafka.input.topic"))
                .setGroupId(config.getString("kafka.group.id"))
                .setStartingOffsets(OffsetsInitializer.earliest())
                .setValueOnlyDeserializer(new SimpleStringSchema())
                .build();
    }
}

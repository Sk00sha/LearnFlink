package sk00sha.flink.transformations;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;
import sk00sha.flink.model.Sentence;

public class WordCountFlatmap implements FlatMapFunction<Sentence, String> {
    @Override
    public void flatMap(Sentence s, Collector<String> collector) throws Exception {
        for (String word: s.sentence().split("[ ,]+")) {
            collector.collect(word);
        }
    }
}

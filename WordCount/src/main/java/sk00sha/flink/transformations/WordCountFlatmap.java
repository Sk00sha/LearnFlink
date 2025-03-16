package sk00sha.flink.transformations;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.OpenContext;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.util.Collector;
import sk00sha.flink.model.Sentence;

import java.util.HashMap;
import java.util.Map;

public class WordCountFlatmap extends RichFlatMapFunction<Sentence, Map<String,Integer>> {
    private transient Map<String,Integer> wordcount;
    @Override
    public void open(OpenContext openContext) throws Exception {
        wordcount = new HashMap<>();
    }

    @Override
    public void flatMap(Sentence s, Collector<Map<String,Integer>> collector) throws Exception {
        for (String word: s.sentence().split("[ ,]+")) {
           wordcount.put(word,1);
        }
        collector.collect(wordcount);

    }
}

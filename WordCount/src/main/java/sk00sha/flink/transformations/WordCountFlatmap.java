package sk00sha.flink.transformations;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.OpenContext;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.util.Collector;
import sk00sha.flink.model.Sentence;
import sk00sha.flink.model.WordWithCount;

import java.util.HashMap;
import java.util.Map;

public class WordCountFlatmap extends RichFlatMapFunction<Sentence, WordWithCount> {
    private  Map<String,Integer> wordcount = new HashMap<>();
    @Override
    public void flatMap(Sentence s, Collector<WordWithCount> collector) throws Exception {
        for (String word: s.sentence().split("[ ,]+")) {
            if (wordcount.containsKey(word)) {
                wordcount.put(word, wordcount.get(word)+1);
            }
            else wordcount.put(word,1);

        }
        wordcount.forEach((key,v)->{
            collector.collect(new WordWithCount(key,v));
        });


    }
}

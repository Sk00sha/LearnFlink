package sk00sha.flink;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.connector.file.src.reader.TextLineInputFormat;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import sk00sha.flink.model.Sentence;
import sk00sha.flink.transformations.WordCountFlatmap;

import java.text.MessageFormat;
import java.util.Map;

public class WordCountRunner {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> fileStream = env.fromSource(
                buildFileSource(new Path(buildFilePath("lines.txt"))),
                WatermarkStrategy.noWatermarks(), // No event time processing
                "File Source");
        fileStream.map(Sentence::new).flatMap(new WordCountFlatmap()).print();

        env.execute();
    }
    private static String buildFilePath(String filename){
        final String packageName = "WordCount";
        final String pattern = "{0}/{1}/{2}";
        return MessageFormat.format(pattern, System.getProperty("user.dir"), packageName,filename);
    }
    private static FileSource<String> buildFileSource(Path filepath){
       return  FileSource.forRecordStreamFormat(new TextLineInputFormat(),filepath)
                        .build();

    }


}
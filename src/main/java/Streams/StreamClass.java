package Streams;

import javafx.util.Builder;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Locale;
import java.util.Properties;

public class StreamClass {
    public static void main(String args[]) {

        Properties properties = new Properties();
        //setting property
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass() );
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG,"streams");
      //creating stream builder to run streams application
        StreamsBuilder streamsBuilder = new StreamsBuilder();

        //creating stream  input stream

        KStream<String, String> kStreaminput = streamsBuilder.stream("input");
        KStream<String, String> kStreamoutput = kStreaminput.mapValues(value -> value.toUpperCase());

        //producing to the output topic
        kStreamoutput.to("output", Produced.with(Serdes.String(), Serdes.String()));
        KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), properties);
         //starting kafka stream by using start method
        kafkaStreams.start();
        //closing threads and stream
        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
    }

}

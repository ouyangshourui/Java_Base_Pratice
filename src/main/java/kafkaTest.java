import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class kafkaTest {
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "10.253.11.113:9092,10.253.11.131:9092,10.253.11.64:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "ourui-kaka");
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,1000);


        Producer<String, String> producer = null;
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer(properties);


        // 3、订阅数据，这里的topic可以是多个
        kafkaConsumer.subscribe(Arrays.asList("90001-whstask046-oms-orders"));

        // 4、获取数据
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("topic = %s,offset = %d, key = %s, value = %s%n",record.topic(), record.offset(), record.key(), record.value());
            }
        }
    }
}

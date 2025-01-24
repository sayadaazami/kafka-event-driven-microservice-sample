package ir.sayad.producer1.config;

import lombok.Getter;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Getter
public class Config {

    @Value("${app.topic.name:default}")
    private String topicName;

    @Value("${app.topic.partitions:3}")
    private String partitions;

    @Value("${app.topic.replicas:3}")
    private String replicas;

    @Value("${app.topic.min-replicas:3}")
    private String minReplicas;

    @Bean
    NewTopic createTopic() {
        return TopicBuilder
                .name(getTopicName())
                .partitions(Integer.parseInt(getPartitions()))
                .replicas(Integer.parseInt(getReplicas()))
                .config("min.insync.replicas", getMinReplicas())
                .build();
    }

}

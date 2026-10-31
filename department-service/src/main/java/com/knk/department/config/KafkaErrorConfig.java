package com.knk.department.config;


import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.config.TopicBuilder;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.util.backoff.FixedBackOff;

//@Configuration
public class KafkaErrorConfig {

    @Bean
    public NewTopic userEventsDltTopic() {
        return TopicBuilder.name("user-events.DLT")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public DefaultErrorHandler kafkaErrorHandler(
            KafkaTemplate<Object, Object> kafkaTemplate) {

        DeadLetterPublishingRecoverer recoverer =
                new DeadLetterPublishingRecoverer(
                    kafkaTemplate,
                    (record, exception) ->
                        new TopicPartition(
                            "user-events.DLT",
                            record.partition()
                        )
                );

        recoverer.setFailIfSendResultIsError(true);

        FixedBackOff backOff =
                new FixedBackOff(2000L, 2L);

        return new DefaultErrorHandler(recoverer, backOff);
    }
}


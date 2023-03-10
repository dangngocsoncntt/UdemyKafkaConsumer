package com.learnkafka.learnkafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learnkafka.learnkafkaconsumer.service.LibraryEventsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LibraryEventsConsumer {
    @Autowired
    LibraryEventsService libraryEventsService;

    @KafkaListener(topics = "library-events")
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord) throws JsonProcessingException {
        libraryEventsService.processLibraryEvent(consumerRecord);
        log.info("Consumer Record {}", consumerRecord);
    }
}

package com.learnkafka.learnkafkaconsumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.learnkafkaconsumer.entity.LibraryEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LibraryEventsService {
    @Autowired
    ObjectMapper objectMapper;

    public void processLibraryEvent(ConsumerRecord<Integer, String> consumerRecord) throws JsonProcessingException {
        LibraryEvent libraryEvent = objectMapper.readValue(consumerRecord.value(), LibraryEvent.class);
        log.info("libraryEvent object {}", libraryEvent);
        switch (libraryEvent.getLibraryEventType()){
            case NEW:
                //
                save(libraryEvent);
                break;
            case UPDATE:
                //
                validate(libraryEvent);
                break;
            default:
                log.info("Please choose relevant LibraryEventType");
        }
    }

    private void validate(LibraryEvent libraryEvent) {
        //
        log.info("Validate libraryEvent {}", libraryEvent);
        if (libraryEvent.getLibraryEventId() == null){
            throw new IllegalArgumentException("LibraryEventId must be not null");
        }
        log.info("Validate library event complete {}", libraryEvent);
    }

    private void save(LibraryEvent libraryEvent) {
        //
        log.info("Save libraryEvent {}", libraryEvent);
    }
}

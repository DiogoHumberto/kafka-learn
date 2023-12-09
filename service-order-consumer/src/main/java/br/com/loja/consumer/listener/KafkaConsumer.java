package br.com.loja.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaConsumer {
	
    @KafkaListener(topics = "LOJA.NOVO.PEDCOMP-0", groupId = "group_json",
            containerFactory = "pedCompKafkaListenerFactory")
    public void consumeJson(Object pedComp) {
        log.info("------------------------ Consumed JSON Message: " + pedComp);
    }

}

package br.com.loja.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.loja.consumer.dto.PedCompDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaConsumer {
	
    @KafkaListener(topics = "LOJA.NOVO.PEDCOMP", groupId = "group_json",
            containerFactory = "pedCompKafkaListenerFactory")
    public void consumeJson(PedCompDto pedComp) {
        log.info("Consumed JSON Message: " + pedComp);
    }

}

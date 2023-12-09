package br.com.loja.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import br.com.loja.consumer.dto.PedCompDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaConsumer {
	
	@KafkaListener(topics = "NOVO_PEDCOMP", groupId = "group_json", containerFactory = "pedCompKafkaListenerFactory")
	public void consumeJson(@Payload PedCompDto pedComp, @Headers MessageHeaders headers) {
		log.info("-------------------------> Consumed JSON Message, numpeComp {} com o valor {} para emtrega em {}: ",
				pedComp.getNumpedcomp(), pedComp.getVlTotal(), pedComp.getDtEntrega());
	}

}

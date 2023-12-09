package br.com.loja.producer.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.loja.producer.dto.PedCompDto;
import br.com.loja.producer.enums.TopicsEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedCompService {
	
	private final KafkaTemplate<String, PedCompDto> kafkaTemplate;
	
	public PedCompDto savarNovoPedComp(PedCompDto pedCompDto) {
		
		//Notifica Kafka novo pedido
		kafkaTemplate.send(TopicsEnum.LOJA_NOVO_PEDCOMP.getName(), pedCompDto);
		
		return pedCompDto;		
		
	}

}

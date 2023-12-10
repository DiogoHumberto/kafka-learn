package br.com.loja.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import br.com.loja.consumer.dto.PedCompDto;
import lombok.RequiredArgsConstructor;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

	private final KafkaProperties kafkaProperties;

	@Bean
	public ConsumerFactory<String, PedCompDto> pedCompConsumerFactory() {
		
	    JsonDeserializer<PedCompDto> deserializer = new JsonDeserializer<>(PedCompDto.class);
	    
	    deserializer.setRemoveTypeHeaders(false);
	    deserializer.addTrustedPackages("*");
	    deserializer.setUseTypeMapperForKey(true);
		
		Map<String, Object> config = new HashMap<>();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");// pega as msg armazenadas - mesmo antes da aplicação subir
		//config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 100); // configurar pool de dados rede - padrao 500
		//config.put(ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, false); //nao permite auto create de topic 
		config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, PedCompDto> pedCompKafkaListenerFactory() {
		var factory = new ConcurrentKafkaListenerContainerFactory<String, PedCompDto>();
		factory.setConsumerFactory(pedCompConsumerFactory());
		return factory;
	}
	
    @Bean
    public ProducerFactory<String, PedCompDto> producerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, PedCompDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}

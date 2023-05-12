package br.com.loja.producer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import br.com.loja.producer.dto.PedCompDto;
import br.com.loja.producer.enums.TopicsEnum;

@Configuration
public class KakfaConfiguration {
	
	@Autowired
	private KafkaProperties kafkaProperties;
	
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
    
    //Configuração para habilitar criação topic via app
    @Bean
    KafkaAdmin KafkaAdmin() {
    	
    	var configs = new HashMap<String, Object>();
    	configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
    	return new KafkaAdmin(configs);
    }
    
    //Criando novo topic para pedcomp novo
    @Bean
    NewTopic lojaNovoPedCompTopic() {
    	return new NewTopic(TopicsEnum.LOJA_NOVO_PEDCOMP.getName(), 1 , Short.valueOf("1"));
    }

}

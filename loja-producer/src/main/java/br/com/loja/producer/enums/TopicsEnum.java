package br.com.loja.producer.enums;

import lombok.Getter;

public enum TopicsEnum {
	
	LOJA_NOVO_PEDCOMP("NOVO_PEDCOMP");
	
	@Getter
	private String name;
	
	private TopicsEnum(String name) {
		this.name = name;
	}


}

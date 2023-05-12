package br.com.loja.consumer.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PedCompDto {
	
	private UUID uuidPedcomp;
	
	private Integer numpedcomp;

	private Date dtEntrega;
	
	private Date dtPedido;
	
	private BigDecimal vlTotal;

	private Integer status;

}

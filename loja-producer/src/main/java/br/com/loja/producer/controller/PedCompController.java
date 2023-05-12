package br.com.loja.producer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.loja.producer.dto.PedCompDto;
import br.com.loja.producer.service.PedCompService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pedido")
@RequiredArgsConstructor
public class PedCompController {
	
	private final PedCompService pedCompService;
	
	@PostMapping
	public ResponseEntity<PedCompDto> novoPedido(@Valid @RequestBody PedCompDto pedCompDto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(pedCompService.savarNovoPedComp(pedCompDto));

	}

}

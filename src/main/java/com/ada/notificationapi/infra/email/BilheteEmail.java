package com.ada.notificationapi.infra.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ada.notificationapi.domain.model.Bilhete;
import com.ada.notificationapi.domain.model.Passageiro;
import com.ada.notificationapi.domain.model.Voo;
import com.ada.notificationapi.domain.repository.PassageiroRepository;
import com.ada.notificationapi.domain.repository.VooRepository;

import reactor.core.publisher.Mono;

@Component
public class BilheteEmail {

	@Autowired
	private PassageiroRepository passageiroRepository;

	@Autowired
	private VooRepository vooRepository;
	
	public void sendEmail(Bilhete bilhete) {

		Mono<Bilhete> bilheteMono = Mono.just(bilhete);
		Mono<Passageiro> passageiroMono = passageiroRepository.findById(bilhete.getPassageiroId());
		Mono<Voo> vooMono = vooRepository.findById(bilhete.getVooId());
			
		Mono<String> message = Mono.zip(bilheteMono, passageiroMono, vooMono).flatMap(o -> {
			
			StringBuilder sb = new StringBuilder();

			Bilhete _bilhete = o.getT1();
			Passageiro _passageiro = o.getT2();
			Voo _voo = o.getT3();
			
			sb
			.append("\r----------------------- EMAIL -----------------------\r\r")
			.append("\r\rE-mail: " + _passageiro.getNome() + "<" + _passageiro.getEmail() + ">")
			.append("\r")
			.append("\r")
			.append("Mensagem: \r")
			.append("Olá " + _passageiro.getNome() + ",\r\r")
			.append("Segue abaixo os dados da sua próxima viagem\r")
			.append("\rNumero do bilhete: " + _bilhete.getNumeroBilhete())
			.append("\rVoo:")
			.append("\r - Numero do Voo: " + _voo.getNumeroVoo())
			.append("\r - Aeroporto Partida: " + _voo.getCodigoAeroportoPartida())
			.append("\r - Aeroporto Chegada: " + _voo.getCodigoAeroportoChegada())
			.append("\r - Data-hora Partida: " + _voo.getDataHoraPartida())
			.append("\r - Data-hora Chegada: " + _voo.getDataHoraChegada())
			.append("\r - Preço: " + _voo.getPreco())
			.append("\r-------------------------------------------------\r\r");

			return Mono.just(sb.toString());
			
		});
		
		message.subscribe(value -> System.out.println(value));
				
	}
	
}

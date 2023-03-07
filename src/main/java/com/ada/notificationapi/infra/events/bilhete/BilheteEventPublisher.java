package com.ada.notificationapi.infra.events.bilhete;

import org.springframework.stereotype.Component;

import com.ada.notificationapi.domain.model.Bilhete;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class BilheteEventPublisher {

	private Sinks.Many<Bilhete> sinks;
	
	public BilheteEventPublisher() {
		
		this.sinks = Sinks.many().multicast().onBackpressureBuffer();
		
	}
	
	public void publish(Bilhete bilhete) {
		
		this.sinks.tryEmitNext(bilhete);
		
	}
	
	public Flux<Bilhete> getFlux() {
		
		return this.sinks.asFlux();
		
	}
	
}

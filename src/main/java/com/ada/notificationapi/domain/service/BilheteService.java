package com.ada.notificationapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.notificationapi.domain.model.Bilhete;
import com.ada.notificationapi.domain.repository.BilheteRepository;
import com.ada.notificationapi.infra.events.bilhete.BilheteEventHandler;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class BilheteService {

	@Autowired
	private BilheteRepository bilheteRepository;
	
	@Autowired
	private BilheteEventHandler bilheteEventHandler;

	public Mono<Bilhete> sendNotification(long id ) {
		
		Mono<Bilhete> bilheteMono = bilheteRepository.findById(id);

		bilheteMono
			.subscribeOn(Schedulers.newSingle("new thread"))
			.subscribe(bilheteEventHandler::sendEvent);
		
		return bilheteMono;
	}
	
}

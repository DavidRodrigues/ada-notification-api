package com.ada.notificationapi.infra.events.bilhete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ada.notificationapi.domain.model.Bilhete;
import com.ada.notificationapi.infra.email.BilheteEmail;

import reactor.core.scheduler.Schedulers;

@Component
public class BilheteEventHandlerImpl implements BilheteEventHandler {

	@Autowired
	BilheteEventPublisher bilhetePublisher;
	
	@Autowired
	BilheteEmail email;
		
	public BilheteEventHandlerImpl(BilheteEventPublisher publisher) {
		
		publisher
			.getFlux()
			.subscribeOn(Schedulers.newSingle("new thread"))
			.subscribe(bilhete -> {
		
				email.sendEmail(bilhete);
				
			});
		
	}
	
	@Override
	public void sendEvent(Bilhete bilhete) {
		
		if (bilhete != null) {
			
			bilhetePublisher.publish(bilhete);
		
		}
	}

}

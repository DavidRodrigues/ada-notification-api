package com.ada.notificationapi.infra.events.bilhete;

import org.springframework.stereotype.Component;

import com.ada.notificationapi.domain.model.Bilhete;

@Component
public interface BilheteEventHandler {

	void sendEvent(Bilhete bilhete);
	
}

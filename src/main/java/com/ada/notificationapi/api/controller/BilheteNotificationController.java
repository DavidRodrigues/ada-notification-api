package com.ada.notificationapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ada.notificationapi.domain.service.BilheteService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/notifications")
public class BilheteNotificationController {

	@Autowired
	private BilheteService service;
	
	@GetMapping("/bilhetes/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Boolean> publisher(@PathVariable("id") Long id) {
		
		service.sendNotification(id);
		
		return Mono.just(true);
		
	}

}

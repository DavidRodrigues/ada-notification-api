package com.ada.notificationapi.domain.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.ada.notificationapi.domain.model.Bilhete;

@Repository
public interface BilheteRepository extends ReactiveCrudRepository<Bilhete, Long>{
}

package co.com.sofka.model.gateway;

import reactor.core.publisher.Mono;

public interface PersonRepository {

    Mono<String> findNameCustomer(String clientId);
}

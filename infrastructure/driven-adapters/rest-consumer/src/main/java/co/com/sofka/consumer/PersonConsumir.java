package co.com.sofka.consumer;

import co.com.sofka.model.gateway.PersonRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PersonConsumir implements PersonRepository {

    private final WebClient client;
    private final String urlBase;


    public PersonConsumir(@Value("${adapter.restconsumer.url}") String urlBase, WebClient client) {
        this.client = client;
        this.urlBase = urlBase;
    }

    @Override
    public Mono<String> findNameCustomer(String clientId) {
        return client
                .get()
                .uri(urlBase.concat("/v1/clientes/").concat(clientId).concat("/name"))
                .retrieve()
                .bodyToMono(String.class);
    }
}

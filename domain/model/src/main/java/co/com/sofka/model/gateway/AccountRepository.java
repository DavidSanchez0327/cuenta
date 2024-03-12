package co.com.sofka.model.gateway;

import co.com.sofka.model.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRepository {

    Mono<Account> findAccount(String id);

    Flux<Account> findAllByClientId(String clientId);

    Mono<Void> deleteAccount(String id);

    Mono<Account> saveAccount(Account account);

    Mono<Account> updateAccount(Account account);
}

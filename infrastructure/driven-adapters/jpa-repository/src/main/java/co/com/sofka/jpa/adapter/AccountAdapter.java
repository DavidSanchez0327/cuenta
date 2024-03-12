package co.com.sofka.jpa.adapter;

import co.com.sofka.jpa.gateway.AccountAdapterRepository;
import co.com.sofka.jpa.mapper.Mapper;
import co.com.sofka.model.Account;
import co.com.sofka.model.gateway.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountAdapter implements AccountRepository, Mapper {

    private final AccountAdapterRepository adapterRepository;

    @Override
    public Mono<Account> findAccount(String id) {
        return Mono.justOrEmpty(adapterRepository.findById(id))
                .map(this::mapToAccount);
    }

    @Override
    public Flux<Account> findAllByClientId(String clientId) {
        return Flux.fromIterable(adapterRepository.findByClientId(clientId).stream().map(this::mapToAccount).collect(Collectors.toList()));
    }

    @Override
    public Mono<Void> deleteAccount(String id) {
        adapterRepository.deleteById(id);
        return Mono.empty();
    }

    @Override
    public Mono<Account> saveAccount(Account account) {
        return Mono.just(adapterRepository.save(mapToAccountEntity(account)))
                .map(this::mapToAccount);
    }

    @Override
    public Mono<Account> updateAccount(Account account) {
        return Mono.justOrEmpty(adapterRepository.findById(account.id()))
                .map(accountEntity -> adapterRepository.save(mapToAccountEntity(account)))
                .map(this::mapToAccount);
    }
}

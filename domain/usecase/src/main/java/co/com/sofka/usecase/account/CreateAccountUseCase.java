package co.com.sofka.usecase.account;

import co.com.sofka.model.Account;
import co.com.sofka.model.gateway.AccountRepository;
import co.com.sofka.model.gateway.PersonRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateAccountUseCase {

    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;

    public Mono<Account> createAccount(Account account) {
        return personRepository.findNameCustomer(account.clientId())
                .flatMap(nameCustomer -> accountRepository.saveAccount(account.toBuilder().client(nameCustomer).build()));
    }
}

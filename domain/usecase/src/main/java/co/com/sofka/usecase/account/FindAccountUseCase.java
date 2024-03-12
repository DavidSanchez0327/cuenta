package co.com.sofka.usecase.account;

import co.com.sofka.model.Account;
import co.com.sofka.model.exception.ErrorEnum;
import co.com.sofka.model.exception.TechnicalException;
import co.com.sofka.model.gateway.AccountRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindAccountUseCase {

    private final AccountRepository accountRepository;

    public Mono<Account> findAccount(String id) {
        return accountRepository.findAccount(id)
                .map(account -> account)
                .switchIfEmpty(Mono.error(new TechnicalException(ErrorEnum.CUSTOMER_DOESNT_EXIST.getMessage())));
    }
}

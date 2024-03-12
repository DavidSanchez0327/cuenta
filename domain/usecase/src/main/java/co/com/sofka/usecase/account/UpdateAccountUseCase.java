package co.com.sofka.usecase.account;

import co.com.sofka.model.Account;
import co.com.sofka.model.gateway.AccountRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateAccountUseCase {

    private final AccountRepository accountRepository;

    public Mono<Account> updateAccount(Account account) {
        return accountRepository.updateAccount(account);
    }
}

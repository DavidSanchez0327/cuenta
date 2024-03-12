package co.com.sofka.usecase.account;

import co.com.sofka.model.gateway.AccountRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeleteAccountUseCase {

    private final AccountRepository accountRepository;

    public Mono<Boolean> deleteAccount(String id) {
        return accountRepository.deleteAccount(id)
                .thenReturn(Boolean.TRUE);
    }
}

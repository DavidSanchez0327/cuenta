package co.com.sofka.usecase.account;

import co.com.sofka.model.Account;
import co.com.sofka.model.gateway.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UpdateAccountUseCaseTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    UpdateAccountUseCase updateAccountUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        updateAccountUseCase = new UpdateAccountUseCase(accountRepository);
    }


    @Test
    void testDeleteTransaction() {

        Account account = Account.builder()
                .id("asdasd")
                .number("123456789")
                .client("carlos")
                .type("ahorros")
                .initialBalance(12321.00)
                .state(Boolean.TRUE)
                .clientId("asdasdas")
                .build();
        when(accountRepository.updateAccount(any())).thenReturn(Mono.just(account));
        StepVerifier.create(updateAccountUseCase.updateAccount(any())).expectNext(account).verifyComplete();

    }
}

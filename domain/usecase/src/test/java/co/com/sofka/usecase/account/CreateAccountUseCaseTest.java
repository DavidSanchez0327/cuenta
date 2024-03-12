package co.com.sofka.usecase.account;

import co.com.sofka.model.Account;
import co.com.sofka.model.gateway.AccountRepository;
import co.com.sofka.model.gateway.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreateAccountUseCaseTest {


    @Mock
    AccountRepository accountRepository;


    @Mock
    PersonRepository personRepository;

    @InjectMocks
    CreateAccountUseCase CreateAccountUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        CreateAccountUseCase = new CreateAccountUseCase(accountRepository, personRepository);
    }


    @Test
    void testCreateTransaction() {

        Account account = Account.builder()
                .id("asdasd")
                .number("123456789")
                .client("carlos")
                .type("ahorros")
                .initialBalance(12321.00)
                .state(Boolean.TRUE)
                .clientId("asdasdas")
                .build();
        when(accountRepository.saveAccount(any())).thenReturn(Mono.just(account));
        when(personRepository.findNameCustomer(any())).thenReturn(Mono.just("carlos"));

        StepVerifier.create(CreateAccountUseCase.createAccount(account)).expectNext(account).verifyComplete();

    }
}

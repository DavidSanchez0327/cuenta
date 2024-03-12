package co.com.sofka.usecase.account;

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

public class DeleteAccountUseCaseTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    DeleteAccountUseCase deleteAccountUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        deleteAccountUseCase = new DeleteAccountUseCase(accountRepository);
    }


    @Test
    void testDeleteTransaction() {

        when(accountRepository.deleteAccount(any())).thenReturn(Mono.empty());

        StepVerifier.create(deleteAccountUseCase.deleteAccount(any())).expectNext(Boolean.TRUE).verifyComplete();
    }
}
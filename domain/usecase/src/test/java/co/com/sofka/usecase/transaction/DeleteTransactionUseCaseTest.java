package co.com.sofka.usecase.transaction;

import co.com.sofka.model.gateway.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class DeleteTransactionUseCaseTest {


    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    DeleteTransactionUseCase deleteTransactionUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        deleteTransactionUseCase = new DeleteTransactionUseCase(transactionRepository);
    }


    @Test
    void testDeleteTransaction() {

        when(transactionRepository.deleteTransaction(anyString())).thenReturn(Mono.empty());

        StepVerifier.create(deleteTransactionUseCase.deleteTransaction(anyString())).expectNext(Boolean.TRUE).verifyComplete();

    }
}

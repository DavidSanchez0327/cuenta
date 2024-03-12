package co.com.sofka.usecase.transaction;

import co.com.sofka.model.Transaction;
import co.com.sofka.model.gateway.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UpdateTransactionUseCaseTest {
    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    UpdateTransactionUseCase updateTransactionUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        updateTransactionUseCase = new UpdateTransactionUseCase(transactionRepository);
    }


    @Test
    void testCreateTransaction() {
        Transaction transaction = Transaction.builder()
                .id("asdasd")
                .date(LocalDate.now())
                .movementType("ahorros")
                .amount(200.0)
                .balance(200.0)
                .transactionNumber(0)
                .accountId("asdasda")
                .build();

        when(transactionRepository.updateTransaction(any())).thenReturn(Mono.just(transaction));

        StepVerifier.create(updateTransactionUseCase.updateTransaction(transaction)).expectNext(transaction).verifyComplete();

    }
}

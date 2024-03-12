package co.com.sofka.usecase.transaction;

import co.com.sofka.model.Transaction;
import co.com.sofka.model.gateway.FindTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class FindTransactionUseCaseTest {
    @Mock
    FindTransactionRepository transactionRepository;

    @InjectMocks
    FindTransactionUseCase findTransactionUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        findTransactionUseCase = new FindTransactionUseCase(transactionRepository);
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

        when(transactionRepository.findTransaction(anyString())).thenReturn(Mono.just(transaction));

        StepVerifier.create(findTransactionUseCase.findTransaction(anyString())).expectNext(transaction).verifyComplete();

    }
}

package co.com.sofka.usecase.transaction;

import co.com.sofka.model.Transaction;
import co.com.sofka.model.gateway.FindTransactionRepository;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class CreateTransactionUseCaseTest {

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    FindTransactionRepository findTransactionRepository;

    @InjectMocks
    CreateTransactionUseCase createTransactionUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        createTransactionUseCase = new CreateTransactionUseCase(transactionRepository, findTransactionRepository);

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

        Transaction transactionResponse = transaction.toBuilder().transactionNumber(1).build();
        when(findTransactionRepository.findCurrentBalanceByAccountId(anyString())).thenReturn(Mono.just(0.0));
        when(findTransactionRepository.findTransactionsNumber(anyString())).thenReturn(Mono.just(0));
        when(transactionRepository.saveTransaction(any())).thenReturn(Mono.just(transactionResponse));

        StepVerifier.create(createTransactionUseCase.createTransaction(transaction)).expectNext(transactionResponse).verifyComplete();
    }
}

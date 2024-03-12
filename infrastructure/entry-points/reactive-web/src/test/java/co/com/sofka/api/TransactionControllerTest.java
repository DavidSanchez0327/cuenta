package co.com.sofka.api;

import co.com.sofka.api.dto.AccountDto;
import co.com.sofka.api.dto.TransactionDto;
import co.com.sofka.model.Transaction;
import co.com.sofka.usecase.transaction.CreateTransactionUseCase;
import co.com.sofka.usecase.transaction.FindTransactionUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TransactionControllerTest {
    @Mock
    CreateTransactionUseCase createTransactionUseCase;
    @Mock
    FindTransactionUseCase findTransactionUseCase;

    @InjectMocks
    TransactionController controller;

    @BeforeEach
    void setUp() {
        controller = new TransactionController(createTransactionUseCase, findTransactionUseCase, null, null);
    }

    @Test
    void saveTransaction() {

        when(createTransactionUseCase.createTransaction(any())).thenReturn(Mono.just(getTransaction()));


        TransactionDto transactionDto = getTransactionDto();
        StepVerifier.create(controller.saveTransaction(getTransactionDto()))
                .expectNext(transactionDto).verifyComplete();
    }

    @Test
    void findTransaction() {

        when(findTransactionUseCase.findTransaction(any())).thenReturn(Mono.just(getTransaction()));


        TransactionDto transactionDto = getTransactionDto();
        StepVerifier.create(controller.findTransaction(any()))
                .expectNext(transactionDto).verifyComplete();
    }

    private static Transaction getTransaction() {
        return Transaction.builder()
                .id("asdasd")
                .date(LocalDate.now())
                .movementType("ahorros")
                .amount(200.0)
                .balance(200.0)
                .transactionNumber(0)
                .accountId("asdasda")
                .build();
    }

    private static TransactionDto getTransactionDto() {
        return TransactionDto.builder()
                .id("asdasd")
                .date(LocalDate.now())
                .movementType("ahorros")
                .amount(200.0)
                .balance(200.0)
                .accountId("asdasda")
                .build();
    }
}
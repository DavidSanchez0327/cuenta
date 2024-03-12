package co.com.sofka.usecase.transaction;

import co.com.sofka.model.gateway.TransactionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeleteTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public Mono<Boolean> deleteTransaction(String id) {
        return transactionRepository.deleteTransaction(id)
                .thenReturn(Boolean.TRUE);
    }
}

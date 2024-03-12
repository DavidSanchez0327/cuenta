package co.com.sofka.usecase.transaction;

import co.com.sofka.model.Transaction;
import co.com.sofka.model.gateway.TransactionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public Mono<Transaction> updateTransaction(Transaction transaction) {
        return transactionRepository.updateTransaction(transaction);
    }
}

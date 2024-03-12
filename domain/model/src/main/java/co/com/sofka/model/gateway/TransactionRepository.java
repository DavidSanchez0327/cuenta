package co.com.sofka.model.gateway;

import co.com.sofka.model.Transaction;
import reactor.core.publisher.Mono;

public interface TransactionRepository {
    Mono<Void> deleteTransaction(String id);

    Mono<Transaction> saveTransaction(Transaction transaction);

    Mono<Transaction> updateTransaction(Transaction transaction);
}

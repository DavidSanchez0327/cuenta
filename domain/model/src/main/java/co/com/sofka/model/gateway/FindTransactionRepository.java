package co.com.sofka.model.gateway;

import co.com.sofka.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface FindTransactionRepository {

    Mono<Transaction> findTransaction(String id);

    Flux<Transaction> findAllTransactionByAccountId(String accountId, LocalDate initialDate, LocalDate finalDate);

    Mono<Double> findCurrentBalanceByAccountId(String accountId);

    Mono<Integer> findTransactionsNumber(String accountId);
}

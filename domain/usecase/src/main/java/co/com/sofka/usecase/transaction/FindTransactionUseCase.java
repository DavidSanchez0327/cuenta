package co.com.sofka.usecase.transaction;

import co.com.sofka.model.Transaction;
import co.com.sofka.model.gateway.FindTransactionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindTransactionUseCase {


    private final FindTransactionRepository transactionRepository;

    public Mono<Transaction> findTransaction(String id) {
        return transactionRepository.findTransaction(id);
    }
}

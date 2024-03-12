package co.com.sofka.jpa.adapter;


import co.com.sofka.jpa.gateway.TransactionAdapterRepository;
import co.com.sofka.jpa.mapper.Mapper;
import co.com.sofka.model.Transaction;
import co.com.sofka.model.gateway.FindTransactionRepository;
import co.com.sofka.model.gateway.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionAdapter implements TransactionRepository, FindTransactionRepository, Mapper {

    private final TransactionAdapterRepository adapterRepository;

    @Override
    public Mono<Transaction> findTransaction(String id) {
        return Mono.justOrEmpty(adapterRepository.findById(id))
                .map(this::mapToPerson);
    }

    @Override
    public Mono<Double> findCurrentBalanceByAccountId(String accountId) {
        return Mono.justOrEmpty(adapterRepository.findCurrentBalance(accountId))
                .switchIfEmpty(Mono.just(0.0));
    }

    @Override
    public Mono<Integer> findTransactionsNumber(String accountId) {
        return Mono.justOrEmpty(adapterRepository.findTransactionsNumber(accountId))
                .switchIfEmpty(Mono.just(0));
    }

    @Override
    public Flux<Transaction> findAllTransactionByAccountId(String accountId, LocalDate initialDate, LocalDate finalDate) {
        return Flux.fromIterable(adapterRepository.findAllTransactionByAccountId(initialDate, finalDate, accountId)
                .stream().map(this::mapToPerson).collect(Collectors.toList()));
    }

    @Override
    public Mono<Void> deleteTransaction(String id) {
        adapterRepository.deleteById(id);
        return Mono.empty();
    }

    @Override
    public Mono<Transaction> saveTransaction(Transaction transaction) {
        return Mono.just(adapterRepository.save(mapToTransactionEntity(transaction)))
                .map(this::mapToPerson);
    }

    @Override
    public Mono<Transaction> updateTransaction(Transaction transaction) {
        return Mono.justOrEmpty(adapterRepository.findById(transaction.id()))
                .map(accountEntity -> adapterRepository.save(mapToTransactionEntity(transaction)))
                .map(this::mapToPerson);
    }
}

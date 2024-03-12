package co.com.sofka.usecase.transaction;

import co.com.sofka.model.Transaction;
import co.com.sofka.model.exception.ErrorEnum;
import co.com.sofka.model.exception.TechnicalException;
import co.com.sofka.model.gateway.FindTransactionRepository;
import co.com.sofka.model.gateway.TransactionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
public class CreateTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final FindTransactionRepository findTransactionRepository;

    public Mono<Transaction> createTransaction(Transaction transaction) {
        return findTransactionRepository.findCurrentBalanceByAccountId(transaction.accountId())
                .switchIfEmpty(Mono.error(new TechnicalException(ErrorEnum.CUSTOMER_DOESNT_EXIST.getMessage())))
                .map(currentAccount -> calculateBalance(transaction, currentAccount))
                .flatMap(calculatedBalance -> findTransactionRepository.findTransactionsNumber(transaction.accountId())
                        .flatMap(integer -> validateBalance(transaction.toBuilder()
                                .id(UUID.randomUUID().toString())
                                .balance(0.0)
                                .date(LocalDate.now())
                                .transactionNumber(integer + 1).build(), calculatedBalance)));
    }

    private Mono<Transaction> validateBalance(Transaction transaction, Double calculatedBalance) {
        return calculatedBalance < 0
                ? Mono.error(new TechnicalException(ErrorEnum.BALANCE_NOT_AVAILABLE.getMessage()))
                : transactionRepository.saveTransaction(transaction.toBuilder().balance(calculatedBalance).build())
                .switchIfEmpty(Mono.error(new TechnicalException(ErrorEnum.ERROR_GENERATING_TRANSACTION.getMessage())));
    }

    private static double calculateBalance(Transaction transaction, Double currentAccount) {
        return currentAccount + transaction.amount();
    }
}

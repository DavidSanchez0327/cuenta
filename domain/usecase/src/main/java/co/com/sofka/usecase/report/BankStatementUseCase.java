package co.com.sofka.usecase.report;

import co.com.sofka.model.Account;
import co.com.sofka.model.Report;
import co.com.sofka.model.gateway.AccountRepository;
import co.com.sofka.model.gateway.FindTransactionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class BankStatementUseCase {

    private final FindTransactionRepository transactionRepository;

    private final AccountRepository accountRepository;


    public Mono<List<Report>> generateReport(String clientId, LocalDate initialDate, LocalDate finalDate) {
        return accountRepository.findAllByClientId(clientId)
                .flatMap(account -> findTransactions(initialDate, finalDate, account)
                ).collectList();
    }

    private Flux<Report> findTransactions(LocalDate initialDate, LocalDate finalDate, Account account) {
        return transactionRepository.findAllTransactionByAccountId(account.id(), initialDate, finalDate)
                .map(transaction -> Report.builder()
                        .date(transaction.date())
                        .client(account.client())
                        .number(account.number())
                        .type(account.type())
                        .initialBalance(account.initialBalance())
                        .state(account.state())
                        .bankingTransactions(transaction.amount())
                        .balance(transaction.balance())
                        .build());
    }
}

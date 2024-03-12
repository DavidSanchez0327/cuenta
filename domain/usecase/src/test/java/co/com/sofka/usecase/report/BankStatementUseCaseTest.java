package co.com.sofka.usecase.report;

import co.com.sofka.model.Account;
import co.com.sofka.model.Report;
import co.com.sofka.model.Transaction;
import co.com.sofka.model.gateway.AccountRepository;
import co.com.sofka.model.gateway.FindTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class BankStatementUseCaseTest {

    @Mock
    FindTransactionRepository transactionRepository;
    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    BankStatementUseCase bankStatementUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bankStatementUseCase = new BankStatementUseCase(transactionRepository, accountRepository);
    }


    @Test
    void generateReportTest() {
        LocalDate date = LocalDate.now();
        Account account = Account.builder()
                .id("asd312")
                .number("123456789")
                .client("carlos")
                .type("ahorros")
                .initialBalance(12321.00)
                .state(Boolean.TRUE)
                .clientId("client1")
                .build();

        Transaction transaction = Transaction.builder()
                .id("as1234")
                .date(date)
                .movementType("ingreso")
                .amount(200.0)
                .balance(200.0)
                .transactionNumber(0)
                .accountId("asd312")
                .build();

        Transaction transaction2 = Transaction.builder()
                .id("as12345")
                .date(date)
                .movementType("ingreso")
                .amount(200.0)
                .balance(400.0)
                .transactionNumber(0)
                .accountId("asd312")
                .build();

        Transaction transaction3 = Transaction.builder()
                .id("as123456")
                .date(date)
                .movementType("ingreso")
                .amount(200.0)
                .balance(600.0)
                .transactionNumber(0)
                .accountId("asd312")
                .build();

        Account account2 = Account.builder()
                .id("asd123")
                .number("987654321")
                .client("carlos")
                .type("corriente")
                .initialBalance(12321.00)
                .state(Boolean.TRUE)
                .clientId("client1")
                .build();

        Transaction transaction4 = Transaction.builder()
                .id("as1234567")
                .date(date)
                .movementType("ingreso")
                .amount(200.0)
                .balance(200.0)
                .transactionNumber(0)
                .accountId("asd123")
                .build();

        Transaction transaction5 = Transaction.builder()
                .id("as12345678")
                .date(date)
                .movementType("ingreso")
                .amount(200.0)
                .balance(400.0)
                .transactionNumber(0)
                .accountId("asd123")
                .build();

        Transaction transaction6 = Transaction.builder()
                .id("as123456789")
                .date(date)
                .movementType("ingreso")
                .amount(200.0)
                .balance(600.0)
                .transactionNumber(0)
                .accountId("asd123")
                .build();

        List<Report> reports = reportList(date);
        when(accountRepository.findAllByClientId("client1")).thenReturn(Flux.just(account, account2));
        when(transactionRepository.findAllTransactionByAccountId("asd312", date, date)).thenReturn(Flux.just(transaction, transaction2, transaction3));
        when(transactionRepository.findAllTransactionByAccountId("asd123", date, date)).thenReturn(Flux.just(transaction4, transaction5, transaction6));
        StepVerifier.create(bankStatementUseCase.generateReport("client1", date, date)).expectNext(reports).verifyComplete();

    }

    private static List<Report> reportList(LocalDate date) {
        List<Report> reports = new ArrayList<>();
        Report report = Report.builder()
                .date(date)
                .client("carlos")
                .number("123456789")
                .type("ahorros")
                .initialBalance(12321.0)
                .state(Boolean.TRUE)
                .bankingTransactions(200.0)
                .balance(200.0)
                .build();
        Report report2 = Report.builder()
                .date(date)
                .client("carlos")
                .number("123456789")
                .type("ahorros")
                .initialBalance(12321.0)
                .state(Boolean.TRUE)
                .bankingTransactions(200.0)
                .balance(400.0)
                .build();
        Report report3 = Report.builder()
                .date(date)
                .client("carlos")
                .number("123456789")
                .type("ahorros")
                .initialBalance(12321.0)
                .state(Boolean.TRUE)
                .bankingTransactions(200.0)
                .balance(600.0)
                .build();
        Report report4 = Report.builder()
                .date(date)
                .client("carlos")
                .number("987654321")
                .type("corriente")
                .initialBalance(12321.0)
                .state(Boolean.TRUE)
                .bankingTransactions(200.0)
                .balance(200.0)
                .build();
        Report report5 = Report.builder()
                .date(date)
                .client("carlos")
                .number("987654321")
                .type("corriente")
                .initialBalance(12321.0)
                .state(Boolean.TRUE)
                .bankingTransactions(200.0)
                .balance(400.0)
                .build();
        Report report6 = Report.builder()
                .date(date)
                .client("carlos")
                .number("987654321")
                .type("corriente")
                .initialBalance(12321.0)
                .state(Boolean.TRUE)
                .bankingTransactions(200.0)
                .balance(600.0)
                .build();
        reports.add(report);
        reports.add(report2);
        reports.add(report3);
        reports.add(report4);
        reports.add(report5);
        reports.add(report6);
        return reports;
    }


}

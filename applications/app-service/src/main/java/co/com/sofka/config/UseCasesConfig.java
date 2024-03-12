package co.com.sofka.config;

import co.com.sofka.model.gateway.AccountRepository;
import co.com.sofka.model.gateway.FindTransactionRepository;
import co.com.sofka.model.gateway.PersonRepository;
import co.com.sofka.model.gateway.TransactionRepository;
import co.com.sofka.usecase.account.CreateAccountUseCase;
import co.com.sofka.usecase.account.DeleteAccountUseCase;
import co.com.sofka.usecase.account.FindAccountUseCase;
import co.com.sofka.usecase.account.UpdateAccountUseCase;
import co.com.sofka.usecase.report.BankStatementUseCase;
import co.com.sofka.usecase.transaction.CreateTransactionUseCase;
import co.com.sofka.usecase.transaction.DeleteTransactionUseCase;
import co.com.sofka.usecase.transaction.FindTransactionUseCase;
import co.com.sofka.usecase.transaction.UpdateTransactionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class UseCasesConfig {
    @Bean
    public WebClient getWebClient(WebClient.Builder builder) {
        return builder
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
    }

    @Bean
    public CreateTransactionUseCase createTransactionUseCase(FindTransactionRepository findTransactionRepository, TransactionRepository transactionRepository) {
        return new CreateTransactionUseCase(transactionRepository, findTransactionRepository);
    }

    @Bean
    public UpdateTransactionUseCase updateTransactionUseCase(TransactionRepository transactionRepository) {
        return new UpdateTransactionUseCase(transactionRepository);
    }

    @Bean
    public DeleteTransactionUseCase deleteTransactionUseCase(TransactionRepository transactionRepository) {
        return new DeleteTransactionUseCase(transactionRepository);
    }

    @Bean
    public FindTransactionUseCase findTransactionUseCase(FindTransactionRepository transactionRepository) {
        return new FindTransactionUseCase(transactionRepository);
    }

    @Bean
    public CreateAccountUseCase createAccountUseCase(AccountRepository accountRepository, PersonRepository personRepository) {
        return new CreateAccountUseCase(accountRepository, personRepository);
    }

    @Bean
    public UpdateAccountUseCase updateAccountUseCase(AccountRepository accountRepository) {
        return new UpdateAccountUseCase(accountRepository);
    }

    @Bean
    public DeleteAccountUseCase deleteAccountUseCase(AccountRepository accountRepository) {
        return new DeleteAccountUseCase(accountRepository);
    }

    @Bean
    public FindAccountUseCase findAccountUseCase(AccountRepository accountRepository) {
        return new FindAccountUseCase(accountRepository);
    }

    @Bean
    public BankStatementUseCase bankStatementUseCase(FindTransactionRepository transactionRepository, AccountRepository accountRepository) {
        return new BankStatementUseCase(transactionRepository, accountRepository);
    }
}

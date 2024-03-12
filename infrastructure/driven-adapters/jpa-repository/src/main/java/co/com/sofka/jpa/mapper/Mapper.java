package co.com.sofka.jpa.mapper;


import co.com.sofka.jpa.entity.AccountEntity;
import co.com.sofka.jpa.entity.TransactionEntity;
import co.com.sofka.model.Account;
import co.com.sofka.model.Transaction;

public interface Mapper {

    default AccountEntity mapToAccountEntity(Account account) {
        return AccountEntity.builder()
                .id(account.id())
                .state(account.state())
                .type(account.type())
                .clientId(account.clientId())
                .initialBalance(account.initialBalance())
                .number(account.number())
                .client(account.client())
                .build();

    }

    default Account mapToAccount(AccountEntity accountEntity) {
        return Account.builder()
                .id(accountEntity.getId())
                .state(accountEntity.getState())
                .type(accountEntity.getType())
                .clientId(accountEntity.getClientId())
                .initialBalance(accountEntity.getInitialBalance())
                .number(accountEntity.getNumber())
                .client(accountEntity.getClient())
                .build();

    }

    default TransactionEntity mapToTransactionEntity(Transaction transaction) {
        return TransactionEntity.builder()
                .id(transaction.id())
                .accountId(transaction.accountId())
                .amount(transaction.amount())
                .date(transaction.date())
                .balance(transaction.balance())
                .movementType(transaction.movementType())
                .transactionNumber(transaction.transactionNumber())
                .build();

    }

    default Transaction mapToPerson(TransactionEntity transactionEntity) {
        return Transaction.builder()
                .id(transactionEntity.getId())
                .accountId(transactionEntity.getAccountId())
                .amount(transactionEntity.getAmount())
                .date(transactionEntity.getDate())
                .balance(transactionEntity.getBalance())
                .movementType(transactionEntity.getMovementType())
                .transactionNumber(transactionEntity.getTransactionNumber())
                .build();

    }
}

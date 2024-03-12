package co.com.sofka.api.dto;

import co.com.sofka.model.Transaction;
import lombok.Builder;

import java.time.LocalDate;

@Builder(toBuilder = true)
public record TransactionDto(String id, LocalDate date, String movementType, Double amount, Double balance,
                             String accountId) {

    public static TransactionDto mapToTransactionDto(Transaction transaction) {
        return TransactionDto.builder()
                .id(transaction.id())
                .accountId(transaction.accountId())
                .amount(transaction.amount())
                .balance(transaction.balance())
                .date(transaction.date())
                .movementType(transaction.movementType())
                .build();

    }

    public static Transaction mapToTransaction(TransactionDto transactionDto) {
        return Transaction.builder()
                .id(transactionDto.id())
                .accountId(transactionDto.accountId())
                .amount(transactionDto.amount())
                .date(transactionDto.date())
                .balance(transactionDto.balance())
                .movementType(transactionDto.movementType())
                .build();

    }
}

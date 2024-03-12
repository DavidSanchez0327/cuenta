package co.com.sofka.model;

import lombok.Builder;

import java.time.LocalDate;

@Builder(toBuilder = true)
public record Transaction(String id,
                          LocalDate date,
                          String movementType,
                          Double amount,
                          Double balance,
                          Integer transactionNumber,
                          String accountId) {

}

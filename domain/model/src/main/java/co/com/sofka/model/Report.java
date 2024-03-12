package co.com.sofka.model;

import lombok.Builder;

import java.time.LocalDate;

@Builder(toBuilder = true)
public record Report(LocalDate date, String client, String number, String type, Double initialBalance, Boolean state,
                     Double bankingTransactions,
                     Double balance) {

}

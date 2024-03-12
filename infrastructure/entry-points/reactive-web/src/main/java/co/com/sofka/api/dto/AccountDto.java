package co.com.sofka.api.dto;

import co.com.sofka.model.Account;
import lombok.Builder;

@Builder(toBuilder = true)
public record AccountDto(String id, String number, String client, String type, Double initialBalance, Boolean state,
                         String clientId) {

    public static AccountDto mapToAccountDto(Account account) {
        return AccountDto.builder()
                .id(account.id())
                .state(account.state())
                .type(account.type())
                .clientId(account.clientId())
                .initialBalance(account.initialBalance())
                .number(account.number())
                .client(account.client())
                .build();

    }

    public static Account mapToAccount (AccountDto accountDto) {
        return Account.builder()
                .id(accountDto.id())
                .state(accountDto.state())
                .type(accountDto.type())
                .clientId(accountDto.clientId())
                .initialBalance(accountDto.initialBalance())
                .number(accountDto.number())
                .client(accountDto.client())
                .build();

    }
}

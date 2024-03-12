package co.com.sofka.api;

import co.com.sofka.api.dto.AccountDto;
import co.com.sofka.usecase.account.CreateAccountUseCase;
import co.com.sofka.usecase.account.DeleteAccountUseCase;
import co.com.sofka.usecase.account.FindAccountUseCase;
import co.com.sofka.usecase.account.UpdateAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static co.com.sofka.api.dto.AccountDto.mapToAccount;

@RestController
@RequestMapping(value = "/v1/cuentas")
@RequiredArgsConstructor
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;
    private final FindAccountUseCase findAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;

    @PostMapping()
    public Mono<AccountDto> saveAccount(@RequestBody AccountDto accountDto) {
        return createAccountUseCase.createAccount(mapToAccount(accountDto).toBuilder()
                        .id(UUID.randomUUID().toString())
                        .build())
                .map(AccountDto::mapToAccountDto);
    }

    @PutMapping()
    public Mono<AccountDto> updateAccount(@RequestBody AccountDto accountDto) {

        return updateAccountUseCase.updateAccount(mapToAccount(accountDto))
                .map(AccountDto::mapToAccountDto);
    }

    @GetMapping("/{id}")
    public Mono<AccountDto> findAccount(@PathVariable("id") String id) {

        return findAccountUseCase.findAccount(id)
                .map(AccountDto::mapToAccountDto);
    }

    @DeleteMapping("/{id}")
    public Mono<Boolean> deleteAccount(@PathVariable("id") String id) {

        return deleteAccountUseCase.deleteAccount(id);
    }
}

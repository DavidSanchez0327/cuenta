package co.com.sofka.api;

import co.com.sofka.api.dto.AccountDto;
import co.com.sofka.model.Account;
import co.com.sofka.usecase.account.CreateAccountUseCase;
import co.com.sofka.usecase.account.FindAccountUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AccountControllerTest {

    @Mock
    CreateAccountUseCase createAccountUseCase;
    @Mock
    FindAccountUseCase findAccountUseCase;

    @InjectMocks
    AccountController controller;

    @BeforeEach
    void setUp() {
        controller = new AccountController(createAccountUseCase, findAccountUseCase, null, null);
    }

    @Test
    void saveAccount() {

        when(createAccountUseCase.createAccount(any())).thenReturn(Mono.just(getAccount()));

        AccountDto customerDto = getAccountDto();
        StepVerifier.create(controller.saveAccount(customerDto))
                .expectNext(customerDto).verifyComplete();

    }

    @Test
    void findAccount() {

        when(findAccountUseCase.findAccount(any())).thenReturn(Mono.just(getAccount()));

        AccountDto customerDto = getAccountDto();
        StepVerifier.create(controller.findAccount(any()))
                .expectNext(customerDto).verifyComplete();
    }

    private static Account getAccount() {


        return Account.builder()
                .id("asdasd")
                .number("123456789")
                .client("carlos")
                .type("ahorros")
                .initialBalance(12321.00)
                .state(Boolean.TRUE)
                .clientId("asdasdas")
                .build();
    }

    private static AccountDto getAccountDto() {


        return AccountDto.builder()
                .id("asdasd")
                .number("123456789")
                .client("carlos")
                .type("ahorros")
                .initialBalance(12321.00)
                .state(Boolean.TRUE)
                .clientId("asdasdas")
                .build();
    }
}
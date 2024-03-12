package co.com.sofka.api;

import co.com.sofka.api.dto.TransactionDto;
import co.com.sofka.usecase.transaction.CreateTransactionUseCase;
import co.com.sofka.usecase.transaction.DeleteTransactionUseCase;
import co.com.sofka.usecase.transaction.FindTransactionUseCase;
import co.com.sofka.usecase.transaction.UpdateTransactionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static co.com.sofka.api.dto.TransactionDto.mapToTransaction;

@RestController
@RequestMapping(value = "/v1/movimientos")
@RequiredArgsConstructor
@ControllerAdvice
public class TransactionController {


    private final CreateTransactionUseCase createTransactionUseCase;
    private final FindTransactionUseCase findTransactionUseCase;
    private final DeleteTransactionUseCase deleteTransactionUseCase;
    private final UpdateTransactionUseCase updateTransactionUseCase;

    @PostMapping()
    public Mono<TransactionDto> saveTransaction(@RequestBody TransactionDto transactionDto) {
        return createTransactionUseCase.createTransaction(mapToTransaction(transactionDto))
                .map(TransactionDto::mapToTransactionDto);
    }

    @PutMapping()
    public Mono<TransactionDto> updateTransaction(@RequestBody TransactionDto transactionDto) {

        return updateTransactionUseCase.updateTransaction(mapToTransaction(transactionDto))
                .map(TransactionDto::mapToTransactionDto);

    }

    @GetMapping("/{id}")
    public Mono<TransactionDto> findTransaction(@PathVariable("id") String id) {

        return findTransactionUseCase.findTransaction(id)
                .map(TransactionDto::mapToTransactionDto);
    }

    @DeleteMapping("/{id}")
    public Mono<Boolean> deleteTransaction(@PathVariable("id") String id) {

        return deleteTransactionUseCase.deleteTransaction(id);
    }
}

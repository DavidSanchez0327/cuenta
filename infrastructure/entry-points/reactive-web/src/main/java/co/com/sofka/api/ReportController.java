package co.com.sofka.api;

import co.com.sofka.api.dto.ReportDto;
import co.com.sofka.usecase.report.BankStatementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class ReportController {

    private final BankStatementUseCase bankStatementUseCase;

    @GetMapping("/accounts/{clientId}/reports")
    public Mono<List<ReportDto>> findAccount(@RequestParam("initialDate") LocalDate initialDate,
                                             @RequestParam("finalDate") LocalDate finalDate,
                                             @PathVariable("clientId") String clientId) {

        return bankStatementUseCase.generateReport(clientId, initialDate, finalDate)
                .map(transactions -> transactions.stream().map(ReportDto::mapToReportDto).collect(Collectors.toList()));
    }
}

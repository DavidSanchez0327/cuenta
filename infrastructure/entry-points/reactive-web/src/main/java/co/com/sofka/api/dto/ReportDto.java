package co.com.sofka.api.dto;

import co.com.sofka.model.Report;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;

@Builder(toBuilder = true)
public record ReportDto(LocalDate fecha, String cliente, String numeroCuenta, String tipo, Double saldoInicial, Boolean estado, Double movimiento,
                        Double saldoDisponible) {

    public static ReportDto mapToReportDto (Report report) {
        return ReportDto.builder()
                .fecha(report.date())
                .cliente(report.client())
                .numeroCuenta(report.number())
                .tipo(report.type())
                .saldoInicial(report.initialBalance())
                .estado(report.state())
                .movimiento(report.bankingTransactions())
                .saldoDisponible(report.balance())
                .build();

    }
}

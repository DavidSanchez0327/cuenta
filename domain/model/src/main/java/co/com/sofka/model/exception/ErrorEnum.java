package co.com.sofka.model.exception;

import lombok.Getter;

@Getter
public enum ErrorEnum {

    BALANCE_NOT_AVAILABLE("Saldo no disponible para su transaccion"),
    ERROR_GENERATING_TRANSACTION("Error al generar una transacciono"),
    CUSTOMER_DOESNT_EXIST("El cliente no existe");

    private final String message;

    ErrorEnum(String message) {
        this.message = message;
    }
}

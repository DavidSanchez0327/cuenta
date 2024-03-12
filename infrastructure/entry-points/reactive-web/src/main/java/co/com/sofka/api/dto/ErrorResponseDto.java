package co.com.sofka.api.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record ErrorResponseDto(Integer code,String message) {
}

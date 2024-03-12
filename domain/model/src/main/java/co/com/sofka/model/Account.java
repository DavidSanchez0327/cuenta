package co.com.sofka.model;

import lombok.Builder;

@Builder(toBuilder = true)
public record Account(String id, String number, String client,
                      String type, Double initialBalance, Boolean state,
                      String clientId) {

}

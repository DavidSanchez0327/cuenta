package co.com.sofka.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
@Entity
public class AccountEntity {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "client")
    private String client;
    @Column(name = "number")
    private String number;
    @Column(name = "type")
    private String type;
    @Column(name = "initialbalance")
    private Double initialBalance;
    @Column(name = "state")
    private Boolean state;
    @Column(name = "client_id")
    private String clientId;
}

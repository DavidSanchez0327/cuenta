package co.com.sofka.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "banking_transaction")
@Entity
public class TransactionEntity {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Column(name = "transactionnumber")
    private Integer transactionNumber;
    @Column(name = "movementtype")
    private String movementType;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "balance")
    private Double balance;
    @Column(name = "account_id")
    private String accountId;
}

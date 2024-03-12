package co.com.sofka.jpa.gateway;

import co.com.sofka.jpa.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionAdapterRepository extends JpaRepository<TransactionEntity, String> {

    @Query(nativeQuery = true, value = "SELECT balance FROM banking_transaction WHERE account_id = ?1 AND transactionnumber = (SELECT MAX(transactionnumber) from banking_transaction)")
    Double findCurrentBalance(String accountId);

    @Query(nativeQuery = true, value = "select count(*) from banking_transaction WHERE account_id = ?1")
    Integer findTransactionsNumber(String accountId);

    @Query(nativeQuery = true, value = "SELECT * FROM banking_transaction WHERE ((date  >= ?1 )  AND  (date <= ?2) AND account_id = ?3)")
    List<TransactionEntity> findAllTransactionByAccountId(LocalDate initialDate, LocalDate finalDate, String accountId);
}

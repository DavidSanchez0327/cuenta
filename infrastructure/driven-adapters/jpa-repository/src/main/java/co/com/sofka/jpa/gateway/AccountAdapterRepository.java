package co.com.sofka.jpa.gateway;

import co.com.sofka.jpa.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountAdapterRepository extends JpaRepository<AccountEntity, String> {

    List<AccountEntity> findByClientId(String clientId);
}

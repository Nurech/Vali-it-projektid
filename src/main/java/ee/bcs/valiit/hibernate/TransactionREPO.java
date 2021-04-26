package ee.bcs.valiit.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionREPO extends JpaRepository<TransactionEntity, Integer> {
    List<TransactionEntity> findAllByFromAccountContaining(String accountNr);
}

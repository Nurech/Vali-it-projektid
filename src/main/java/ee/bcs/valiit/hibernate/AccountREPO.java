package ee.bcs.valiit.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountREPO extends JpaRepository<AccountDTO, String> {
}

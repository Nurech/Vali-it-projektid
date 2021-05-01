package ee.bcs.valiit.security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserREPO extends JpaRepository<User, String> {
    List<User> findAllByUsername(String username);
}

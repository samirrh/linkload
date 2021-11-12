package io.sh.linkload.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import io.sh.linkload.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}

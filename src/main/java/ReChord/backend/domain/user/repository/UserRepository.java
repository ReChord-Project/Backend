package ReChord.backend.domain.user.repository;

import ReChord.backend.domain.user.repository.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);

    @Query("""
            SELECT u
            FROM User u
            WHERE u.name LIKE %:keyword% 
                OR u.loginId Like %:keyword%""")
    Page<User> searchUsers(@Param("keyword") String keyword, Pageable pageable);
}

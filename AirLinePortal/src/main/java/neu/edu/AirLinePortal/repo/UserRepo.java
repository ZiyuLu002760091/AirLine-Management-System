package neu.edu.AirLinePortal.repo;

import neu.edu.AirLinePortal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YUlia
 * @version 1.0
 */

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    @Query(value = "select * from users where email = ?1 and password = md5(?2)", nativeQuery = true)
    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);
}

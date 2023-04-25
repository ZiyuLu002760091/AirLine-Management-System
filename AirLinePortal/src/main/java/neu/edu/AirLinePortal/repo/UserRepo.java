package neu.edu.AirLinePortal.repo;

import neu.edu.AirLinePortal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YUlia
 * @version 1.0
 * @see User
 */

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    /**
     * This method is actually used for user login
     * We find user by his user name and password, if find, means user exist
     */
    @Query(value = "select * from users where email = ?1 and password = md5(?2)", nativeQuery = true)
    User findByEmailAndPassword(String email, String password);

    /**
     * Find an unique user by email
     */
    User findByEmail(String email);
}

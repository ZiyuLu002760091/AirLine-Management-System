package neu.edu.AirToronto.repo;

import neu.edu.AirToronto.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author YUlia
 * @version 1.0
 */
public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findAllByUserid(String userid);

    Optional<Book> findByFlightid(String flightid);
}

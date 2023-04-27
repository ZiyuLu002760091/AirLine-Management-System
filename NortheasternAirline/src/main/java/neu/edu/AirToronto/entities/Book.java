package neu.edu.AirToronto.entities;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author YUlia
 * @version 1.0
 */
@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String userid;

    private String flightid;

    private String date;

    private String server;

    private String bookStatus;
}

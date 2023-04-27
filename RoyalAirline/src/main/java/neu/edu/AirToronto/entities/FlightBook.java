package neu.edu.AirToronto.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author YUlia
 * @version 1.0
 */
@Data
@Entity
public class FlightBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String uuid;

    private String flightNumber;

    private String companyName;

    private String estimatedDepartureDatetime;

    private String estimatedArrivalDatetime;

    private String origin;

    private String destination;

    @Enumerated(EnumType.STRING)
    private Flight.Status status;

    private String actualDepartureDatetime;

    private String actualArrivalDatetime;

    private Double price;

    private String memberDiscount;

    private String userid;

    private String date;

    private String server;

    private String bookStatus;
}

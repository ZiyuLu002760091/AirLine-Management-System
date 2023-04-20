package neu.edu.AirLinePortal.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author YUlia
 * @version 1.0
 */
@Data
@Entity
@Table(name = "flightsinfo")
public class Flight implements Serializable {

    @Id
    private String uuid;

    private String flight_number;

    private String company_name;

    private String estimated_departure_datetime;

    private String estimated_arrival_datetime;

    private String origin;

    private String destination;

    private String actual_departure_datetime;

    private String actual_arrival_datetime;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        planning, cancelled, delay,in_advance_on_time
    }

}

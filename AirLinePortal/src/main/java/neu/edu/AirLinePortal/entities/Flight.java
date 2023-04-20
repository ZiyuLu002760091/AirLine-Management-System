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
@Table(name = "flightsInfo")
public class Flight implements Serializable {

    @Id
    private String uuid;

    private String flight_number;

    private String company_name;

    private Date date;

    private String estimated_departure_time;

    private String estimated_arrival_time;

    private String origin;

    private String destination;

    private String actual_departure_time;

    private String actual_arrival_time;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        planning, cancelled, delay
    }

}

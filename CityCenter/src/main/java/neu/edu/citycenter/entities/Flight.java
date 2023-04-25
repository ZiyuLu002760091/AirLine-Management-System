package neu.edu.citycenter.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author YUlia
 * @version 1.0
 */
@Document(collection = "cities")
public class Flight {
    @Id
    private ObjectId id;
    private String icao;
    private String iata;
    private String name;
    private String city;
    private String state;
    private String country;
    private int elevation;
    private double lat;
    private double lon;
    private String tz;
    // getters and setters

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", icao='" + icao + '\'' +
                ", iata='" + iata + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", elevation=" + elevation +
                ", lat=" + lat +
                ", lon=" + lon +
                ", tz='" + tz + '\'' +
                '}';
    }
}


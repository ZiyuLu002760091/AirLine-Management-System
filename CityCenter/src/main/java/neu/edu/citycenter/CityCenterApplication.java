package neu.edu.citycenter;

import neu.edu.citycenter.entities.Flight;
import neu.edu.citycenter.entities.FlightSearch;
import neu.edu.citycenter.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CityCenterApplication implements CommandLineRunner {

	@Autowired
	private FlightService testFlightService;

	public static void main(String[] args) {
		SpringApplication.run(CityCenterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("Start Running");
//		System.out.println("Start Finding City and Country");
//		List<Flight> flights = testFlightService.getFlightsByCityAndCountry("Toronto", "CA");
//		flights.forEach(f -> {
//			System.out.println(f.toString());
//		});
//		System.out.println("Start Finding All in Country");
//		List<Flight> flights2 = testFlightService.getFlightsByCountry("CA");
//		flights2.forEach(f -> {
//			System.out.println(f.toString());
//		});

		FlightSearch flightSearch = new FlightSearch();
		flightSearch.setCity("Toron");
		List<Flight> flights3 = testFlightService.searchFlights(flightSearch, "CA");
		flights3.forEach(f -> {
			System.out.println(f.toString());
		});

		flightSearch.setCity("");
		flightSearch.setAirport("YYZ");
		List<Flight> flights4 = testFlightService.searchFlights(flightSearch, "CA");
		flights4.forEach(f -> {
			System.out.println(f.toString());
		});
	}
}

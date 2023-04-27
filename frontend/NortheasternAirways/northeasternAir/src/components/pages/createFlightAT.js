import { useState } from 'react';
import sendRequest from "../services/ApiService";
import {checkLogin, getLoginUser} from "../services/loginService";
import './formStyle.css'

function NewFlight() {

    let isLogin = checkLogin();
    let user = isLogin ? getLoginUser() : null;
    let isAdmin = user ? user.role === 'admin' : false;

    if (!isAdmin) {
        alert("You are not allowed to visit this page!");
        window.location.href = "/";
    }

    const [formData, setFormData] = useState({
        uuid: '',
        flight_number: '',
        company_name: 'northeastern-air',
        estimated_departure_datetime: '',
        estimated_arrival_datetime: '',
        origin: '',
        destination: '',
        status: 'planning',
        actual_departure_datetime: '',
        actual_arrival_datetime: '',
        price: '',
        member_discount: ''
    });

    const createFlight = async () => {
        const resp = await sendRequest({
           path : "flight/create",
            method: "POST",
            body : formData
        });
        console.log(resp);
        alert("success!")
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(formData);
        createFlight();
    };

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData((prevState) => ({ ...prevState, [name]: value }));
    };


    const handleOriginSearch = (event) => {
        event.preventDefault();
        // TODO: Handle origin airport search and update searchResults state
        // setSearchResults(['YYZ', 'JFK', 'LHR']); // Example search results for testing
    };

    const handleDestinationSearch = (event) => {
        event.preventDefault();
        // TODO: Handle destination airport search and update searchResults state
        // setSearchResults(['LAX', 'CDG', 'HKG']); // Example search results for testing
    };


    // find airports by any filter
    const searchAirports = async ({city, airport, element}) => {
        const data = await sendRequest({
            path: 'airports/search',
            method: 'POST',
            port: '8088', // city port
            server: 'cities',
            body: {
                city: city,
                airport: airport
            }
        })
        console.log(data)

        const airports = data.data;

        // Get the dropdown list element
        const dropdown = document.getElementById(element);

        while (dropdown.options.length > 1) {
            dropdown.removeChild(dropdown.lastChild);
        }

        // Loop through each airport and add it to the dropdown as an option
        airports.forEach(airport => {
            const option = document.createElement("option");
            option.value = `${airport.icao}`
            option.text = `${airport.icao} - ${airport.name}, ${airport.city}, ${airport.state} (${airport.iata === "" ? "N/A" : airport.iata})`;
            dropdown.add(option);
        });

    }

    const [fromCity, setFromCity] = useState("");
    const [toCity, setToCity] = useState("");

    // hit from city button
    const findFromCity = () => {
        searchAirports({
            city: fromCity,
            airport: "",
            element: "origin"
        });
    }

    // hit to city button
    const findToCity = () => {
        searchAirports({
            city: toCity,
            airport: "",
            element: "destination"
        });
    }

    return (
        <div>
            <h2>Create a New Flight</h2>
            <form onSubmit={handleSubmit}>
                <label htmlFor="flight_number">Flight Number</label>
                <input type="text" id="flight_number" name="flight_number" value={formData.flight_number} onChange={handleChange} required />

                <label htmlFor="company_name">Company Name</label>
                <input type="text" id="company_name" name="company_name" value="northeastern-air" disabled='ture' onChange={handleChange} required />

                <label htmlFor="estimated_departure_datetime">Estimated Departure Datetime</label>
                <input type="datetime-local" id="estimated_departure_datetime" name="estimated_departure_datetime" value={formData.estimated_departure_datetime} onChange={handleChange} required />

                <label htmlFor="estimated_arrival_datetime">Estimated Arrival Datetime</label>
                <input type="datetime-local" id="estimated_arrival_datetime" name="estimated_arrival_datetime" value={formData.estimated_arrival_datetime} onChange={handleChange} required />

                <label htmlFor="originSearch">Origin</label>
                <div>
                    <input type="text" id="originSearch" name="originSearch" onChange={(e) => {setFromCity(e.target.value)}} required />
                    <button type="button" onClick={findFromCity}>Search</button>
                </div>

                <select id="origin" name="origin" value={formData.origin} onChange={handleChange}>
                    <option value="">Select an airport</option>
                </select>

                <label htmlFor="destinationSearch">Destination</label>
                <div>
                    <input type="text" id="destinationSearch" name="destinationSearch" onChange={(e) => {setToCity(e.target.value)}} required />
                    <button type="button" onClick={findToCity}>Search</button>
                </div>


                <select id="destination" name="destination" value={formData.destination} onChange={handleChange}>
                    <option value="">Select an airport</option>
                </select>

                <label htmlFor="price">Price</label>
                <input type="number" id="price" name="price" value={formData.price} onChange={handleChange} required />

                <label htmlFor="member_discount">Member Discount</label>
                <input type="text" id="member_discount" name="member_discount" value={formData.member_discount} onChange={handleChange} />
                <button type="submit">Create Flight</button>
            </form>
        </div>
    );
}

export default NewFlight;

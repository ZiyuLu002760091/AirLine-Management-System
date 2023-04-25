import React, { useState, useEffect } from "react";
import sendRequest from "../services/ApiService";
import FlightData from "../widgets/airlineList";
import BookFlightList from "../widgets/airlineListForBooking";


function SearchFlightsPage() {
    const searchParams = new URLSearchParams(window.location.search);
    const from = searchParams.get("from");
    const to = searchParams.get("to");
    const date = searchParams.get("date");
    const [flightData, setFlightData] = useState([]);

    const searchFlight = async () => {
        const res = await sendRequest({
            path: `apis/airlines/search?from=${from}&to=${to}&date=${date}`,
        })
        console.log(res);
        setFlightData(res);
    }

    useEffect(() => {
        searchFlight();
    }, [])

    return (
        <div>
            <h1>Search Results</h1>
            {flightData ? <BookFlightList data={flightData} /> : <p>Loading flight data...</p>}
        </div>
    );
}

export default SearchFlightsPage;

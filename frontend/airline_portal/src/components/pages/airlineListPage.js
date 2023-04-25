import React, { useState, useEffect } from 'react';
import FlightData from '../widgets/airlineList';
import sendRequest from "../services/ApiService";

function AirlineListPage() {
    const [flightData, setFlightData] = useState([]);

    const fetchFlights = async () => {
        const data = await sendRequest({
            path: 'apis/airlines/all'
        });
        console.log(data);
        setFlightData(data);
    }

    useEffect(() => {
        // fetch('http://localhost:8081/portal/apis/airlines/all')
        //     .then((response) => response.json())
        //     .then((data) => setFlightData(data));
        fetchFlights();
    }, []);

    return (
        <div>
            <h1>Flight Data</h1>
            {flightData ? <FlightData data={flightData} /> : <p>Loading flight data...</p>}
        </div>
    );
}


export default AirlineListPage;

import React, { useState, useEffect } from 'react';
import FlightData from '../widgets/airlineList';

function AirlineListPage() {
    const [flightData, setFlightData] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8081/portal/apis/airlines/all')
            .then((response) => response.json())
            .then((data) => setFlightData(data));
    }, []);

    return (
        <div>
            <h1>Flight Data</h1>
            {flightData ? <FlightData data={flightData} /> : <p>Loading flight data...</p>}
        </div>
    );
}


export default AirlineListPage;

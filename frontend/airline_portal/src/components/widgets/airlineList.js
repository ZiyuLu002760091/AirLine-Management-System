import React, { useState,useEffect } from 'react';
import '../../FlightData.css';

function formatDateTime(datetimeString) {
    const date = new Date(datetimeString);
    const formattedDate = date.toLocaleDateString();
    const formattedTime = date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
    return `${formattedDate} ${formattedTime}`;
}

function FlightData({ data }) {
    const [sortBy, setSortBy] = useState("time");
    const [sortOrder, setSortOrder] = useState(0); // 0 for ascending, 1 for descending
    const [timeBtnStr, setTimeBtn] = useState("Sort by Time Ascend");
    const [priceBtnStr, setPriceBtn] = useState("Sort by Price Ascend");

    const handleSortByTime = () => {
        if (sortOrder === 0) {
            setTimeBtn("Sort by Time Descend");
            setSortOrder(1);
        } else {
            setTimeBtn("Sort by Time Ascend");
            setSortOrder(0);
        }
        setSortBy("time");
    };

    const handleSortByPrice = () => {
        if (sortOrder === 0) {
            setPriceBtn("Sort by Price Descend");
            setSortOrder(1);
        } else {
            setPriceBtn("Sort by Price Ascend");
            setSortOrder(0);
        }
        setSortBy("price");
    };

    const [flights, setFlights] = useState([]);

    useEffect(() => {
        if (data && data['data'] && data['data']['air-toronto']) {
            setFlights(data['data']['air-toronto'].data);
        }
    }, [data]);

    if (flights.length === 0) {
        return <p>No flights available.</p >;
    }

    const sortedFlights = flights.slice().sort((a, b) => {
        if (sortBy === "time") {
            if (sortOrder === 0) {
                return new Date(a.estimated_departure_datetime) - new Date(b.estimated_departure_datetime);
            } else {
                return new Date(b.estimated_departure_datetime) - new Date(a.estimated_departure_datetime);
            }
        } else if (sortBy === "price") {
            if (sortOrder === 0) {
                return a.price - b.price;
            } else {
                return b.price - a.price;
            }
        }
    });


    return (
        <div>
            <div>
                <button onClick={handleSortByTime}>{timeBtnStr}</button>
                <button onClick={handleSortByPrice}>{priceBtnStr}</button>
            </div>
            <table className="flight-table">
                <thead>
                <tr>
                    <th>Company Name</th>
                    <th>Flight</th>
                    <th>Origin</th>
                    <th>Destination</th>
                    <th>Es Departure</th>
                    <th>Es Arrival</th>
                    <th>Ac Departure</th>
                    <th>Ac Arrival</th>
                    <th>Price</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                {sortedFlights.map((flight) => (
                    <tr key={flight.uuid}>
                        <td>{`${flight.company_name}`}</td>
                        <td>{`${flight.flight_number}`}</td>
                        <td>{flight.origin}</td>
                        <td>{flight.destination}</td>
                        <td>{formatDateTime(flight.estimated_departure_datetime)}</td>
                        <td>{formatDateTime(flight.estimated_arrival_datetime)}</td>
                        <td>{flight.actual_departure_datetime ? formatDateTime(flight.actual_departure_datetime) : "N/A"}</td>
                        <td>{flight.actual_arrival_datetime ? formatDateTime(flight.actual_arrival_datetime) : "N/A"}</td>
                        <td>{flight.price}</td>
                        <td>{flight.status}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default FlightData;
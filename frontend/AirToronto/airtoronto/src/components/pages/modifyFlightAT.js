import sendRequest from "../services/ApiService";
import {useState} from "react";

function ModifyFlightPage() {

    const searchParams = new URLSearchParams(window.location.search);
    const id = searchParams.get("id");

    const [formData, setFormData] = useState({
        uuid: id,
        flight_number: '',
        company_name: 'air-toronto',
        estimated_departure_datetime: '',
        estimated_arrival_datetime: '',
        origin: '',
        destination: '',
        status: '',
        actual_departure_datetime: '',
        actual_arrival_datetime: '',
        price: '',
        member_discount: ''
    });

    const handleSubmit = async (e) => {
        e.preventDefault();
        const resp = await sendRequest({
            path : "flight/update",
            method: "POST",
            body : formData
        });
        console.log(resp);
        alert("success!")
    }

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData((prevState) => ({ ...prevState, [name]: value }));
    };

    return (
        <div>
            <h2>Update Flight</h2>
            <form onSubmit={handleSubmit}>
                <label htmlFor="actual_departure_datetime">Actual Departure Datetime</label>
                <input type="datetime-local" id="actual_departure_datetime" name="actual_departure_datetime" value={formData.actual_departure_datetime} onChange={handleChange} />
                <label htmlFor="actual_arrival_datetime">Actual Arrival Datetime</label>
                <input type="datetime-local" id="actual_arrival_datetime" name="actual_arrival_datetime" value={formData.actual_arrival_datetime} onChange={handleChange} />
                <label htmlFor="status">Status</label>
                <select id="status" name="status" value={formData.status} onChange={handleChange}>
                    <option value="planning">Planning</option>
                    <option value="cancelled">Cancelled</option>
                    <option value="delay">Delay</option>
                    <option value="in_advance_on_time">In Advance On Time</option>
                </select>
                <input type="submit" value="Update"/>
            </form>
        </div>
    )
}

export default ModifyFlightPage;
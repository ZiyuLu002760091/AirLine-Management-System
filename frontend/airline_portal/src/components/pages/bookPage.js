import React, { useState, useEffect } from "react";
import axios from "axios";
import sendRequest from "../services/ApiService";
import {checkLogin, getLoginUser} from "../services/loginService";
import './tableStyle.css'

const AllBooks = () => {
    const [books, setBooks] = useState([]);

    useEffect(() => {
        axios.get("/books").then((response) => {
            setBooks(response.data);
        });
    }, []);

    return (
        <div>
            <h1>All Books</h1>
            <ul>
                {books.map((book) => (
                    <li key={book.id}>
                        <p>UUID: {book.uuid}</p>
                        <p>User ID: {book.userId}</p>
                        <p>Flight ID: {book.flightId}</p>
                        <p>Date: {book.date}</p>
                        <p>Server: {book.server}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

const BooksForUser = ({ userId }) => {
    const [books, setBooks] = useState([]);

    if (!checkLogin()) {
        alert("not login");
        window.location.href = '/login';
    }

    userId = getLoginUser().uuid;

    const getBookByUser = async () => {
        console.log("abc");
        const data = await sendRequest({
            path: `api/all/FIND_BOOK_BY_USER?userId=${userId}`
        })
        let innerData = data.data;
        let dataList = Object.keys(innerData).flatMap((key) => innerData[key].data);;
        setBooks(dataList)
    }

    const handleCancelBook = async ({book}) => {
        const data = await sendRequest({
            path: `api/specific/${book.companyName}/CANCEL_BOOK`,
            method: 'POST',
            body: {
                uuid: book.uuid
            }
        })
        if (data.success) {
            alert("cancelled success!");
            window.location.href = window.location.href;
        } else {

        }
    }

    useEffect(() => {
        getBookByUser();
    }, []);

    return (
        <div>
            <h1>Your Books</h1>
            <table>
                <thead>
                <tr>
                    <th>Flight Number</th>
                    <th>Company Name</th>
                    <th>Origin</th>
                    <th>Destination</th>
                    <th>Status</th>
                    <th>Estimated Departure Datetime</th>
                    <th>Estimated Arrival Datetime</th>
                    <th>Actual Departure Datetime</th>
                    <th>Actual Arrival Datetime</th>
                    <th>Price</th>
                    <th>Date</th>
                    <th>Server</th>
                    <th>Book Status</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                {books.map((book) => (
                    <tr key={book.uuid}>
                        <td>{book.flightNumber}</td>
                        <td>{book.companyName}</td>
                        <td>{book.origin}</td>
                        <td>{book.destination}</td>
                        <td>{book.status}</td>
                        <td>{book.estimatedDepartureDatetime}</td>
                        <td>{book.estimatedArrivalDatetime}</td>
                        <td>{book.actualDepartureDatetime}</td>
                        <td>{book.actualArrivalDatetime}</td>
                        <td>{book.price}</td>
                        <td>{book.date.substring(0,10)}</td>
                        <td>{book.server}</td>
                        <td>{book.bookStatus}</td>
                        <td>{book.bookStatus === 'booked' && (
                            <button onClick={() => {handleCancelBook({book:book})}}>Cancel</button>
                        )}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>

    );

};

export { AllBooks, BooksForUser };

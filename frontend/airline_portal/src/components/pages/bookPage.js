import React, { useState, useEffect } from "react";
import axios from "axios";
import sendRequest from "../services/ApiService";
import {checkLogin, getLoginUser} from "../services/loginService";

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

    useEffect(() => {
        getBookByUser();
    }, []);

    return (
        <div>
            <h1>Your Books</h1>
            <table>
                <thead>
                <tr>
                    <th>UUID</th>
                    {/*<th>User ID</th>*/}
                    <th>Flight ID</th>
                    <th>Date</th>
                    <th>Server</th>
                </tr>
                </thead>
                <tbody>
                {books.map((book) => (
                    <tr key={book.id}>
                        <td>{book.uuid}</td>
                        {/*<td>{book.userid}</td>*/}
                        <td>{book.flightid}</td>
                        <td>{book.date}</td>
                        <td>{book.server}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );

};

export { AllBooks, BooksForUser };

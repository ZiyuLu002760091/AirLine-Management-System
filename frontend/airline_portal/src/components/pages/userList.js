import React, { useState, useEffect } from 'react';
import sendRequest from "../services/ApiService";
import {checkLogin, getLoginUser} from "../services/loginService";

function UserList() {
    const [users, setUsers] = useState([]);

    let isLogin = checkLogin();
    let user = isLogin ? getLoginUser() : null;
    let isAdmin = user ? user.role === 'admin' : false;

    if (!isAdmin) {
        alert("You are not allowed to visit this page!");
        window.location.href = "/";
    }

    const getAllUsers = async () => {
        const data = await sendRequest({
            path: 'user/all'
        });
        setUsers(data.data);
    }

    useEffect(() => {
        getAllUsers();
    }, []);

    const handleEditUser = (user) => {
        let email = user.email;
        const params = new URLSearchParams();
        params.append("email", email);
        window.location.href = `profileAdmin?${params.toString()}`;
    }

    return (
        <div>
            <h1>User List</h1>
            <table>
                <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Date of Birth</th>
                    <th>Email</th>
                    <th>Gender</th>
                    <th>Status</th>
                    <th>Role</th>
                    <th>Phone Number</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                {users.map(user => (
                    <tr key={user.uuid}>
                        <td>{user.fname}</td>
                        <td>{user.lname}</td>
                        <td>{new Date(user.dob).toISOString().substring(0,10)}</td>
                        <td>{user.email}</td>
                        <td>{user.gender}</td>
                        <td>{user.status}</td>
                        <td>{user.role}</td>
                        <td>{user.phoneno}</td>
                        {
                            user.role !== 'admin' && (
                                <td><button onClick={() => {handleEditUser(user)}}>Edit</button></td>
                            )
                        }
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default UserList;

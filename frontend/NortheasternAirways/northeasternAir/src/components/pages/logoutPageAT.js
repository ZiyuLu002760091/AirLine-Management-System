import {checkLogin, logout} from "../services/loginService";
import {useEffect} from "react";

function Logout() {

    useEffect(() => {
        if (checkLogin()) {
            logout();
            window.alert("You have successfully logged out!");
            window.location.href = "/";
        }
    })

    return (
        <div>
            <br/>
            <p>You have successfully logged out.</p>
        </div>
    );
}

export default Logout;

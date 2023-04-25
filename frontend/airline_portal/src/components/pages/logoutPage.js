import {logout} from "../services/loginService";

function Logout() {
    logout();

    return (
        <div>
            <br/>
            <p>You have successfully logged out.</p>
        </div>
    );
}

export default Logout;

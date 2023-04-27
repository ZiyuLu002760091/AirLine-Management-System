import './styleHeader.css'
import {checkLogin, getLoginUser} from "../services/loginService";

function HeadingAT() {
    let isLogin = checkLogin();
    let user = isLogin ? getLoginUser() : null;
    return (
        <header>
            <h2>Welcome to Northeastern Airline</h2>
            <nav>
                <ul>
                    <li><a href="/">Home</a></li>
                    <li><a href={!isLogin ? "/login" : "/logout"}>{!isLogin ? "Login" : "Logout"}</a></li>
                </ul>
            </nav>
        </header>
    );
}

export default HeadingAT;
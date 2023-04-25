import { checkLogin, getLoginUser } from "../services/loginService";

function Heading() {
    const isLoggedIn = checkLogin();
    const loginUser = isLoggedIn ? getLoginUser() : null;
    const isAdmin = isLoggedIn ? loginUser.role === 'admin' : false;

    return (
        <header>
            <h2>Welcome to Flights Booking Agency</h2>
            <nav>
                <ul>
                    <li>
                        <a href="/">Home</a>
                    </li>
                    <li>
                        <a href={isLoggedIn ? "/logout" : "/login"}>
                            {isLoggedIn ? "Logout" : "Login"}
                        </a>
                    </li>
                    <li>
                        {!isAdmin && <a href={isLoggedIn ? "/profile" : "/register"}>
                            {isLoggedIn ? "Profile" : "Register"}
                        </a>}
                    </li>
                    {isLoggedIn && (
                        <li>
                        {/*  functions as login only here  */}
                        </li>
                    )}
                </ul>
            </nav>
        </header>
    );
}

export default Heading;

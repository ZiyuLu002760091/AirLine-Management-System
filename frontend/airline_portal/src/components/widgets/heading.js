import { checkLogin, getLoginUser } from "../services/loginService";

function Heading() {
    const isLoggedIn = checkLogin();
    const loginUser = isLoggedIn ? getLoginUser() : null;
    const isAdmin = isLoggedIn ? loginUser.role === 'admin' : false;

    const myBooksUrl = '/books/' + (isLoggedIn ? getLoginUser().uuid : null);

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
                    {isLoggedIn && !isAdmin && (
                        <li>
                        {/*  functions as login only here  */}
                            <a href={myBooksUrl}>My Books</a>
                        </li>
                    )}
                    {isAdmin && (
                        <li>
                            {/*  functions as admin only here  */}
                            <a href="/userList">All Users</a>
                        </li>
                    )
                    }

                    {isAdmin && (
                            <li>
                                {/*  functions as admin only here  */}
                                <a href="/companies">All Companies</a>
                            </li>
                        )
                    }

                    {isAdmin && (
                        <li>
                            {/*  functions as admin only here  */}
                            <a href="/airlineListPage">All Flights</a>
                        </li>
                    )
                    }
                </ul>
            </nav>
        </header>
    );
}

export default Heading;

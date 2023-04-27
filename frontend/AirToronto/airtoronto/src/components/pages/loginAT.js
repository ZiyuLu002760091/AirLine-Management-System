import {useState} from "react";
import sendRequest from "../services/ApiService";
import {login} from "../services/loginService";

function Login() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    }

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    }


    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await sendRequest({
                path: "airline/login",
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: { email, password },
            });

            const data = response;
            if (data.success) {
                // Redirect to the homepage
                login(data)
                alert("success");
                window.location.href = "/";
            } else {
                setErrorMessage(data.message);
            }
        } catch (error) {
            console.error("Error:", error);
            setErrorMessage("An unexpected error occurred. Please try again later.");
        }
    };


    return (
        <div>
            <h1>Login</h1>
            <form onSubmit={handleLogin} >
                <label htmlFor="Email">Email:</label>
                <input type="text" id="Email" name="Email" onChange={handleEmailChange} required/>

                <label htmlFor="password">Password:</label>
                <input type="password" id="password" name="password"  onChange={handlePasswordChange} required/>

                <input type="submit" value="Login"/>
                <p>{errorMessage}</p>
            </form>
        </div>
)
}

export default Login;
import {useState} from "react";

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
            const response = await fetch("http://localhost:8081/portal/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ email, password }),
            });

            const data = await response.json();
            if (data.success) {
                // Redirect to the homepage
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
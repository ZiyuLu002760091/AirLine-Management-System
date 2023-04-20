import {useState} from "react";

function Register() {


    const [fname, setFname] = useState("");
    const [lname, setLname] = useState("");
    const [dob, setDOB] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [gender, setGender] = useState("");
    const [phoneno, setPhone] = useState("");
    const [fnameErr, setFnameErr] = useState("");
    const [lNameErr, setLnameErr] = useState("");
    const [dobErr, setDobErr] = useState("");
    const [pwdErr, setPwdErr] = useState("");
    const [emailErr, setEmailErr] = useState("");
    const [genderErr, setGenderErr] = useState("");
    const [phoneErr, setPhoneErr] = useState("");
    const [error, setErrorMessage] = useState("");


    const handleFnameEnter = (event) => {
        setFname(event.target.value);
    }
    const handleLnameEnter = (event) => {
        setLname(event.target.value);
    }
    const handleDOBEnter = (event) => {
        setDOB(event.target.value);
    }

    const handlePwdEnter = (event) => {
        setPassword(event.target.value);
    }

    const handleEmailEnter = (event) => {
        setEmail(event.target.value);
    }
    const handleGenderEnter = (event) => {
        setGender(event.target.value);
    }
    const handlePhoneEnter = (event) => {
        setPhone(event.target.value);
    }


    const handleRegister = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch("http://localhost:8081/portal/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ fname, lname, dob, password, email, gender, phoneno}),
            });

            const data = await response.json();
            if (data.success) {
                // Redirect to the homepage
                alert("success");
                window.location.href = "/";
            } else {
                console.error(data.message)
                let databody = data.body;
                setFnameErr("");
                setLnameErr("");
                setDobErr("");
                setPwdErr("");
                setEmailErr("");
                setGenderErr("");
                setPhoneErr("");

                if (databody != null && databody !== "") {
                    if (null != databody.fname) {
                        setFnameErr(databody.fname);
                    }

                    if (null != databody.lname) {
                        setLnameErr(databody.lname);
                    }

                    if (null != databody.dob) {
                        setDobErr(databody.dob);
                    } else {
                        setFnameErr("");
                    }

                    if (null != databody.password) {
                        setPwdErr(databody.password);
                    }

                    if (null != databody.email) {
                        setEmailErr(databody.email);
                    }

                    if (null != databody.gender) {
                        setGenderErr(databody.gender);
                    }

                    if (null != databody.phoneno) {
                        setPhoneErr(databody.phoneno);
                    }

                } else {
                    console.log("nobody");
                }
                if (data.message !== "") {
                    window.alert(data.message);
                }
            }
        } catch (error) {
            console.error("Error:", error);
            setErrorMessage("An unexpected error occurred. Please try again later.");
        }
    };

    return (
        <div>
            <form onSubmit={handleRegister}>
                <br/>
                <label htmlFor="fname">First Name:</label>
                <p className="errorMsg">{fnameErr}</p>
                <input type="text" id="fname" name="fname" onChange={handleFnameEnter} required/>
                <br/>

                <label htmlFor="lname">Last Name:</label>
                <p className="errorMsg">{lNameErr}</p>
                <input type="text" id="lname" name="lname" onChange={handleLnameEnter} required/>
                <br/>

                <label htmlFor="dob">Date of Birth:</label>
                <p className="errorMsg">{dobErr}</p>
                <input type="date" id="dob" name="dob" onChange={handleDOBEnter} required/>
                <br/>

                <label htmlFor="password">Password:</label>
                <p className="errorMsg">{pwdErr}</p>
                <input type="password" id="password" name="password" onChange={handlePwdEnter} required/>
                <br/>

                <label htmlFor="email">Email Address:</label>
                <p className="errorMsg">{emailErr}</p>
                <input type="email" id="email" name="email" onChange={handleEmailEnter} required/>
                <br/>

                <label htmlFor="phoneno">Phone Number</label>
                <p className="errorMsg">{phoneErr}</p>
                <input type="text" id="phoneno" name="phoneno" onChange={handlePhoneEnter} required/>
                <br/>

                <label htmlFor="gender">Gender:</label>
                <p className="errorMsg">{genderErr}</p>
                <select id="gender" name="gender" onChange={handleGenderEnter} required>
                    <option value="">Select your gender</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="other">Other</option>
                </select>

                <input type="submit" value="Register"/>
            </form>
            {/*<p className="errorMsg">{error}</p>*/}
        </div>
    )
}

export default Register;
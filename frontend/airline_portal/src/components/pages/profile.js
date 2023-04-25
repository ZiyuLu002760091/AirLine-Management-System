import {useState} from "react";
import {getLoginUser} from "../services/loginService";

function ChangeProfile() {

    const [fnameErr, setFnameErr] = useState("");
    const [lNameErr, setLnameErr] = useState("");
    const [dobErr, setDobErr] = useState("");
    const [pwdErr, setPwdErr] = useState("");
    const [emailErr, setEmailErr] = useState("");
    const [genderErr, setGenderErr] = useState("");
    const [phoneErr, setPhoneErr] = useState("");
    const [error, setErrorMessage] = useState("");


    const handleRegister = async (e) => {
        e.preventDefault();
        const form = e.target;
        const formData = new FormData(form);
        const data = {};
        formData.forEach((value, key) => {
            data[key] = value;
        });
        try {
            const response = await fetch("http://localhost:8081/portal/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
            });

            let res = await response.json();
            if (res.success) {
                // Redirect to the homepage
                alert("success");
                window.location.href = "/";
            } else {
                console.error(res.message)
                let databody = res.data;
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
                if (res.message !== "") {
                    window.alert(res.message);
                }
            }
        } catch (error) {
            console.error("Error:", error);
            setErrorMessage("An unexpected error occurred. Please try again later.");
        }
    };

    const user = getLoginUser();// Convert Java Date to string

    if (user.role === "admin") {
        window.alert("Admin profile cannot be changed");
        window.location.href = "/";
        return;
    }

    var javaDateString = user.dob.toString();

// Create new JavaScript Date object from string
    var jsDate = new Date(javaDateString);


    return (
        <div>
            <form onSubmit={handleRegister}>
                <br/>
                <label htmlFor="fname">First Name:</label>
                <p className="errorMsg">{fnameErr}</p>
                <input type="text" id="fname" name="fname"  defaultValue={user.fname} required/>
                <br/>

                <label htmlFor="lname">Last Name:</label>
                <p className="errorMsg">{lNameErr}</p>
                <input type="text" id="lname" name="lname"  defaultValue={user.lname} required/>
                <br/>

                <label htmlFor="dob">Date of Birth:</label>
                <p className="errorMsg">{dobErr}</p>
                <input type="date" id="dob" name="dob"  value={jsDate.toISOString().substring(0, 10)} required/>
                <br/>

                {/* Password and Email should not be updated here */}
                {/*<label htmlFor="password">Password:</label>*/}
                {/*<p className="errorMsg">{pwdErr}</p>*/}
                {/*<input type="password" id="password" name="password" required/>*/}
                {/*<br/>*/}

                {/*<label htmlFor="email">Email Address:</label>*/}
                {/*<p className="errorMsg">{emailErr}</p>*/}
                {/*<input type="email" id="email" name="email"  defaultValue={user.email} required/>*/}
                {/*<br/>*/}

                <label htmlFor="phoneno">Phone Number</label>
                <p className="errorMsg">{phoneErr}</p>
                <input type="text" id="phoneno" name="phoneno"  defaultValue={user.phoneno} required/>
                <br/>

                <label htmlFor="gender">Gender:</label>
                <p className="errorMsg">{genderErr}</p>
                <select id="gender" name="gender" value={user.gender.toLowerCase()} required>
                    <option value="">Select your gender</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="other">Other</option>
                </select>

                <input type="submit" value="Update Profile"/>
            </form>
            {/*<p className="errorMsg">{error}</p>*/}
        </div>
    )
}

export default ChangeProfile;
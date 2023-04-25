import {useEffect, useState} from "react";
import {checkLogin, getLoginUser} from "../services/loginService";

function MainPage() {

    const [greeting, setGreeting] = useState("Hello, you are viewing as guest, would you like to login?");

    useEffect(() => {
        if (checkLogin()) {
            setGreeting("Welcome, " + getLoginUser()["fname"]);
        } else {
            setGreeting("Hello, you are viewing as guest, would you like to login?");
        }
    }, [checkLogin]);

    return (
        <div>
            <main>
                <section>
                    <h2>Flights Search</h2>
                    <p>{greeting}</p>
                    <label htmlFor="from">From:</label>
                    <input type="text" id="from" name="from"/>
                    <label htmlFor="to">To:</label>
                    <input type="text" id="to" name="to"/>
                    <label htmlFor="date">Date:</label>
                    <input type="date" id="date" name="date"/>
                    {/*<label htmlFor="fromlight-company">Flight Company:</label>*/}
                    {/*<select id="flight-company" name="flight-company">*/}
                    {/*    <option value="">Select a flight company</option>*/}
                    {/*    <option value="company1">Company 1</option>*/}
                    {/*    <option value="company2">Company 2</option>*/}
                    {/*    <option value="company3">Company 3</option>*/}
                    {/*</select>*/}
                    <input type="submit" value="Search Flights"/>

                </section>
            </main>

        </div>
    );
}

export default MainPage;
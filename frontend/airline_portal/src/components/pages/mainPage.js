function MainPage() {
    return (
        <div>
            <main>
                <section>
                    <h2>Flights Search</h2>
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
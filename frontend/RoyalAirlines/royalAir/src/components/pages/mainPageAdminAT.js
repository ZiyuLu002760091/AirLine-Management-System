function MainPageAdmin() {
    return (
        <div>
            <main>
                <section>
                    <h1>Welcome to Air Toronto!</h1>
                    <h3>A flight company like no other</h3>
                    <p>You are logged in as admin, what would you like to do?</p>
                    <ul>
                        <li>You can:</li>
                        <li>1. <a href="/createFlight">Create Flight</a></li>
                        <li>2. <a href="/viewFlight">View Flight</a></li>
                    </ul>
                </section>
            </main>
        </div>
    );
}

export default MainPageAdmin;
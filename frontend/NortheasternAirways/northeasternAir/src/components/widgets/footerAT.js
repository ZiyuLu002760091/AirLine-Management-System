function FooterAT() {
    const footerMargin = { marginTop: '20px' }
    const path = window.location.pathname;

    return (
        <div>
            {
                path !== '/' && (
                    // eslint-disable-next-line no-restricted-globals
                    <button onClick={() => {history.back()}}>Go Back</button>)
            }
            <footer>
                <div>
                    <nav>
                        <ul>
                            <li><a href="#">About Us</a></li>
                            <li><a href="#">Contact Us</a></li>
                        </ul>
                    </nav>
                </div>
                <div style={footerMargin}>
                    <p>© 2023 Northeastern Air</p>
                </div>
            </footer>
        </div>
    )
}

export default FooterAT;
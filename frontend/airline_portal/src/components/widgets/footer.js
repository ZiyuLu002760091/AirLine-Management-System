function Footer() {
    const footerMargin = { marginTop: '20px' }

    return (
        <div>
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
                    <p>© 2023 Flights Booking Agency</p>
                </div>
            </footer>
        </div>
    )
}

export default Footer;
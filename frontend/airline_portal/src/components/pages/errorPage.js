import '../../errorPage.css';

function ErrorPage() {
    return (
        <div>
            <h1>404 Not Found</h1>
            <p>The page you requested could not be found.</p>
            <p>
                <a href="/">Go to the Home Page</a>
            </p>
        </div>
    )
}

export default ErrorPage;
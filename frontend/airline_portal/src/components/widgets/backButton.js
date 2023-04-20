function BackButton() {
    const goBack = () => {
        window.history.back();
        console.log("back button clicked");
    }

    return (
        <div>
            <button onClick={goBack}>Back</button>
        </div>
    )
}
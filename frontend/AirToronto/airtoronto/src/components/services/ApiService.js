function sendRequest({ path, method = "GET", port = "8082", server = "air-toronto", headers = {
    "Content-Type": "application/json",
}, body}) {
    // this is a delegate method of fetch, so that we can handle all http requests inside one place
    const url = `http://localhost:${port}/${server}/${path}`;
    return fetch(url, {
        method,
        headers,
        body: JSON.stringify(body),
    })
        .then(response => {
            let responseJson = response.json();
            console.log(responseJson);
            return responseJson;
        })
        .catch(error => console.error(error));
}


export default sendRequest;
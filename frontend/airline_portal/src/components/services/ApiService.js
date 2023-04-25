function sendRequest({ path, method, headers, body }) {
    // this is a delegate method of fetch, so that we can handle all http requests inside one place
    const url = `http://localhost:8081/${path}`;
    return fetch(url, {
        method,
        headers,
        body: JSON.stringify(body),
    })
        .then(response => response.json())
        .catch(error => console.error(error));
}


export default sendRequest;
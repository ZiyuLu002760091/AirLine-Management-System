function login(userProps) {
    // setLogin is the use state setter, we change it to trigger other events
    changeLoginState(true);
    // console.log(userProps['data']);
    localStorage['user'] = JSON.stringify(userProps['data']);
}

function logout() {
    changeLoginState(false);
    localStorage['user'] = null;
}

function getLoginUser() {
    return JSON.parse(localStorage['user']);
}

function changeLoginState(state) {
    localStorage['login-state'] = state;
}

function checkLogin() {
    return localStorage['login-state'] === 'true';
}

export  {login, logout, checkLogin, getLoginUser};
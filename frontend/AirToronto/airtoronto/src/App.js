import logo from './logo.svg';
import './App.css';
import HeadingAT from "./components/widgets/headingAT";
import MainPageforAT from "./components/pages/mainPageforAT";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginAT from "./components/pages/loginAT";
import {checkLogin, getLoginUser} from "./components/services/loginService";
import MainPageAdmin from "./components/pages/mainPageAdminAT";
import Logout from "./components/pages/logoutPageAT";
import NewFlight from "./components/pages/createFlightAT";
import ErrorPage from "./components/pages/errorPage";
import FooterAT from "./components/widgets/footerAT";

function App() {

    let isLogin = checkLogin();
    let user = isLogin ? getLoginUser() : null;
    let isAdmin = user ? user.role === 'admin' : false;

  return (
    <div className="App">
      <HeadingAT />

        <BrowserRouter>
            <Routes>
                <Route path="/" element={isAdmin ? <MainPageAdmin /> : <MainPageforAT />} />
                <Route path="/login" element={<LoginAT/>} />
                <Route path="/logout" element={<Logout/>} />
                <Route path="/createFlight" element={isAdmin ? <NewFlight/> : <ErrorPage/>} />
            </Routes>
        </BrowserRouter>

        <FooterAT/>
    </div>
  );
}

export default App;

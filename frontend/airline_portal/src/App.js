
import './App.css';
import Heading from './components/widgets/heading';
import MainPage from "./components/pages/mainPage";
import './mainStyle.css';
import Footer from "./components/widgets/footer";
import {Route, Router, Routes} from "react-router";
import ErrorPage from "./components/pages/errorPage";
import {BrowserRouter} from "react-router-dom";
import Login from "./components/pages/login";
import Register from "./components/pages/register";


import AirlineListPage from "./components/pages/airlineListPage";



function App() {
  return (
    <div className="App">
        <Heading />
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<MainPage />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/airlineListPage" element={<AirlineListPage />} />
                <Route path="*" element={<ErrorPage />} />
            </Routes>
        </BrowserRouter>
        <Footer />
    </div>
  );
}

export default App;

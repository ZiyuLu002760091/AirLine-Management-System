import logo from './logo.svg';
import './App.css';
import HeadingAT from "./components/widgets/headingAT";
import MainPageforAT from "./components/pages/mainPageforAT";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginAT from "./components/pages/loginAT";

function App() {
  return (
    <div className="App">
      <HeadingAT />

        <BrowserRouter>
            <Routes>
                <Route path="/" element={<MainPageforAT />} />
                <Route path="/login" element={<LoginAT/>} />
            </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;

import './App.css';
import PlayerList from "./PlayerList.tsx";
import {Route, Routes} from "react-router-dom";
import WingerList from "./pages/WingerList.tsx";
import StrikerList from "./pages/StrikerList.tsx";
import MiddlefieldList from "./pages/MiddlefieldList.tsx";
import DefenderList from "./pages/DefenderList.tsx";
import GoalkeeperList from "./pages/GoalkeeperList.tsx";
import PlayerManagement from "./pages/PlayerManagement.tsx";
import {Navbar} from "./Navbar.tsx";


const App = () => {
return (
    <div>
        <div className="navbar"> {<Navbar />} </div>
        <Routes>
            <Route path={"/"} element={<PlayerList/>}/>
            <Route path={"/striker"} element={<StrikerList/>}/>
            <Route path={"/winger"} element={<WingerList/>}/>
            <Route path={"/middlefield"} element={<MiddlefieldList/>}/>
            <Route path={"/defender"} element={<DefenderList/>}/>
            <Route path={"/goalkeeper"} element={<GoalkeeperList/>}/>
            <Route path={"/playermanagement"} element={<PlayerManagement/>}/>
        </Routes>
    </div>
  )
}

export default App;

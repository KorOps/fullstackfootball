import {Route, Routes} from "react-router-dom";
import {Navbar} from "./Navbar.tsx";
import Striker from "./pages/StrikerList.tsx";
import Middlefielder from "./pages/MiddlefieldList.tsx";
import Defender from "./pages/DefenderList.tsx";
import Goalkeeper from "./pages/GoalkeeperList.tsx";

function Navigation() {

    return (
    <>

        <div className="navbar"> {<Navbar />} </div>
    <Routes>
        <Route path={"/"} element={<AllPlayers/>}/>
        <Route path={"./pages/StrikerList"} element={<Striker/>}/>
        <Route path={"./pages/WingerList"} element={<Winger/>}/>
        <Route path={"./pages/MiddlefieldList"} element={<Middlefielder/>}/>
        <Route path={"./pages/DefenderList"} element={<Defender/>}/>
        <Route path={"./pages/GoalkeeperList"} element={<Goalkeeper/>}/>
    </Routes>
    </>
    )
}

export default Navigation
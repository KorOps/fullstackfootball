import {Link, NavLink, Route, Routes} from "react-router-dom";
import "./Navbar.css"

export const Navbar = () => {
    return (
        <div>

        <nav>
            <ul>
                <li><Link to="/">All Players</Link></li>
                <li><NavLink to={"/striker"}>Striker</NavLink></li>
                <li><NavLink to={"/winger"}>Winger</NavLink></li>
                <li><NavLink to={"/middlefield"}>Middlefielder</NavLink></li>
                <li><NavLink to={"/defender"}>Defender</NavLink></li>
                <li><NavLink to={"/goalkeeper"}>Goalkeeper</NavLink></li>
                <li><NavLink to={"/playermanagement"}>PlayerManagement</NavLink></li>
            </ul>
        </nav>
        </div>
    )
};
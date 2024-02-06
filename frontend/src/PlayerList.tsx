import { useEffect, useState} from "react";
import axios from "axios";
import {Navbar} from "./Navbar.tsx";

const PlayerList = () => {
    const [players, setPlayers] = useState([]);

    useEffect(() => {
        axios.get("/api/player")
            .then(response => setPlayers(response.data))
            .catch(error => console.error("Error fetching data:", error));
    }, []);


    return (

        <div>

            <div className="navbar"> {<Navbar />} </div>

            <h2>Ballers</h2>
            <ul>
                {players.map(player => (
                    <li key={player["id"]}>
                        <h4> Firstname: {player["firstname"]} Lastname: {player["lastname"]}</h4>
                        <p>Nationality: {player["nationality"]}</p>
                        <p>Age: {player["age"]}</p>
                        <p>Height: {player["height"]} </p>
                        <p>Foot: {player["foot"]}</p>
                        <p>Position: {player["position"]}</p>
                        <p>Current Team: {player["team"]}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default PlayerList;

import React, { useEffect, useState } from "react";
import axios from "axios";

const WingerList = () => {
    const [players, setPlayers] = useState([]);

    useEffect(() => {
        axios.get("/api/player")
            .then(response => setPlayers(response.data))
            .catch(error => console.error("Error fetching data:", error));
    }, []);

    const filteredWingers = players.filter(player => player.position.toLowerCase() === "winger");

    return (
        <div>

            <h2>Wingers</h2>
            <ul>
                {filteredWingers.map(player => (
                    <li key={player["id"]}>
                        <h4> Firstname: {player["firstname"]} Lastname: {player["lastname"]}</h4>
                        <p>Nationality: {player["nationality"]}</p>
                        <p>Age: {player["age"]}</p>
                        <p>Height: {player["height"]}</p>
                        <p>Foot: {player["foot"]}</p>
                        <p>Position: {player["position"]}</p>
                        <p>Current Team: {player["team"]}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default WingerList;

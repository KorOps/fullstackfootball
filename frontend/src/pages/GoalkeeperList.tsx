import React, { useEffect, useState } from "react";
import axios from "axios";

const GoalkeeperList = () => {
    const [players, setPlayers] = useState([]);

    useEffect(() => {
        axios.get("/api/player")
            .then(response => setPlayers(response.data))
            .catch(error => console.error("Error fetching data:", error));
    }, []);

    const filteredGoalkeepers = players.filter(player => player.position.toLowerCase() === "goalkeeper");

    return (
        <div>

            <h2>Strikers</h2>
            <ul>
                {filteredGoalkeepers.map(player => (
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

export default GoalkeeperList;

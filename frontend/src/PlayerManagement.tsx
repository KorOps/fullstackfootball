import React, { useState, useEffect } from 'react';
import axios from 'axios';
import "./App.css"

const PlayerManagement = () => {
    const [players, setPlayers] = useState([]);
    const [newPlayer, setNewPlayer] = useState({
        firstname: '',
        lastname: '',
        nationality: '',
        age: 0,
        height: 0,
        foot: '',
        position: '',
        team: ''
    });

    useEffect(() => {
        fetchPlayers();
    }, []);

    const fetchPlayers = async () => {
        try {
            const response = await axios.get('/api/player');
            setPlayers(response.data);
        } catch (error) {
            console.error('Error fetching players:', error);
        }
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewPlayer((prevPlayer) => ({ ...prevPlayer, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await axios.post('/api/player', newPlayer);
            setNewPlayer({
                firstname: '',
                lastname: '',
                nationality: '',
                age: 0,
                height: 0,
                foot: '',
                position: '',
                team: ''
            });
            fetchPlayers();
        } catch (error) {
            console.error('Error saving player:', error);
        }
    };

    const handleDelete = async (id) => {
        try {
            await axios.delete(`/api/player/${id}`);
            fetchPlayers();
        } catch (error) {
            console.error('Error deleting player:', error);
        }
    };

    const handleLogout = () => {
        window.location.href = '/index.html';
    };

    return (
        <div className="admin-players">
            <h2>Player Management Panel</h2>

            {/* Form for adding new player */}
            <form onSubmit={handleSubmit}>
                <label>
                    Firstname:
                    <input type="text" name="firstname" value={newPlayer.firstname} onChange={handleInputChange} />
                </label>

                <label>
                    Lastname:
                    <input type="text" name="lastname" value={newPlayer.lastname} onChange={handleInputChange} />
                </label>

                <label>
                    Nationality:
                    <input type="text" name="nationaliy" value={newPlayer.nationality} onChange={handleInputChange} />
                </label>

                <label>
                    Age:
                    <input type="text" name="age" value={newPlayer.age} onChange={handleInputChange} />
                </label>

                <label>
                    Height:
                    <input type="text" name="height" value={newPlayer.height} onChange={handleInputChange} />
                </label>

                <label>
                    Foot:
                    <input type="text" name="foot" value={newPlayer.foot} onChange={handleInputChange} />
                </label>

                <label>
                    Position:
                    <input type="text" name="position" value={newPlayer.position} onChange={handleInputChange} />
                </label>

                <label>
                    Team:
                    <input type="text" name="team" value={newPlayer.team} onChange={handleInputChange} />
                </label>

                <button type="submit">Add Player</button>
            </form>


            <ul>
                {players.map((player) => (
                    <li key={player.id}>
                        {player.firstname} {player.lastname} - {player.position}
                        <button onClick={() => handleDelete(player.id)}>Delete</button>
                    </li>
                ))}
            </ul>
            <div className="admin-logout"><button onClick={handleLogout}>Logout</button></div>
        </div>
    );
};
export default PlayerManagement;
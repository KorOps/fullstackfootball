import React, { useState, useEffect } from 'react';
import axios from 'axios';

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

    const [editingPlayerId, setEditingPlayerId] = useState(null);

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


    {/*this is to edit a player - just testing*/}
    const handleEdit = (playerId) => {
        setEditingPlayerId(playerId);
        const playerToEdit = players.find(player => player["id"] === playerId);
        setNewPlayer({ ...playerToEdit });
    };

    const handleUpdate = async () => {
        try {
            await axios.put(`/api/player/${editingPlayerId}`, newPlayer);
            setEditingPlayerId(null);
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
            console.error('Error updating player:', error);
        }
    };
    {/*this is to edit a player - just testing*/}

    const handleDelete = async (id) => {
        try {
            await axios.delete(`/api/player/${id}`);
            await fetchPlayers();
        } catch (error) {
            console.error('Error deleting player:', error);
        }
    };



    return (
        <div className="admin-players">
            <h2>Administrator Panel</h2>

            <form onSubmit={editingPlayerId ? handleUpdate : handleSubmit}>
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
                    <input type="text" name="nationality" value={newPlayer.nationality} onChange={handleInputChange} />
                </label>

                <label>
                    Age:
                    <input type="number" name="age" value={newPlayer.age} onChange={handleInputChange} />
                </label>

                <label>
                    Height:
                    <input type="number" name="height" value={newPlayer.height} onChange={handleInputChange} />
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

                <button type="submit">{editingPlayerId ? 'Update Player' : 'Add Player'}</button>
            </form>


            <ul>
                {players.map((player) => (
                    <li key={player["id"]}>
                        {player["firstname"]} {player["lastname"]} - {player["position"]}
                        <button onClick={() => handleEdit(player["id"])}>Edit</button>
                        <button onClick={() => handleDelete(player["id"])}>Delete</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};
export default PlayerManagement;
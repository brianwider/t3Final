package edu.ort.t3.player.pattern;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jca.cci.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import edu.ort.t3.player.model.Player;

@Component
public class PlayerPattern implements PlayerPatternDAO {
	@Autowired
    private JdbcTemplate jdbcTemplate;

	// Find all players 
    public List<Player> findAll() {
        List<Player> result = jdbcTemplate.query(
                "SELECT id, name FROM player",
                (rs, rowNum) -> new Player(rs.getInt("id"), rs.getString("name"))
        );

        return result;
    }

	// Add new Player
    public int addPlayer(String name) {
    	try {
    		return jdbcTemplate.update("INSERT INTO player(name) VALUES (?)", name);
    	} catch (InvalidResultSetAccessException e) {
    	    throw new RuntimeException(e);
    	} catch (DataAccessException e) {
    	    throw new RuntimeException(e);
    	}
    }

	// Delete Player
    public int deletePlayer(int id) {
    	try {
    		return jdbcTemplate.update("DELETE FROM player WHERE id = ?", id);
    	} catch (InvalidResultSetAccessException e) {
    	    throw new RuntimeException(e);
    	} catch (DataAccessException e) {
    	    throw new RuntimeException(e);
    	}
    }
    
    // Find all winners 
    public List<Player> findAllWinners() {
        List<Player> result = jdbcTemplate.query(
                "SELECT player_id, name FROM winners",
                (rs, rowNum) -> new Player(rs.getInt("player_id"), rs.getString("name"))
        );

        return result;
    }
    
	// Add new winner
    public int addWinner(int id, String name) {
    	try {
    		return jdbcTemplate.update("INSERT INTO winners(name, player_id) VALUES (?, ?)", name, id);
    	} catch (InvalidResultSetAccessException e) {
    	    throw new RuntimeException(e);
    	} catch (DataAccessException e) {
    	    throw new RuntimeException(e);
    	}
    }

	public Player getPlayerById(int id) {
        String query = "SELECT * FROM player WHERE id = ?"; 
        Object[] inputs = new Object[] {id};
        Player player = jdbcTemplate.queryForObject(query, inputs, (rs, rowNum) -> new Player(rs.getInt("id"), rs.getString("name")));

        return player;
    }
}
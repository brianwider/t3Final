package edu.ort.t3.player.pattern;

import java.util.List;

import edu.ort.t3.player.model.Player;

public interface PlayerPatternDAO {
	public int addPlayer(String name);
	public List<Player> findAll();
	public List<Player> findAllWinners();
	public int addWinner(int id, String name);
}

package edu.ort.t3.finaltp;

import java.io.OutputStream;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ort.t3.player.model.Player;
import edu.ort.t3.player.pattern.PlayerPattern;

@EnableScheduling
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("edu.ort.t3.player.pattern")
@RestController
@RequestMapping("/api")
public class PlayerController {
	@Autowired
    private PlayerPattern playerPattern;
	
	@RequestMapping(value = "/winners/csv", method = RequestMethod.GET)
	public void generateCSV(HttpServletRequest request, HttpServletResponse response)
	{
	    response.setContentType("text/csv");
	    response.setHeader("Content-Disposition", "attachment; filename=\"winners.csv\"");
	    try {
	        OutputStream outputStream = response.getOutputStream();
	        List<Player> winners = playerPattern.findAllWinners();
	        String outputResult = "ID, NAME\n";
	        if (winners.size() > 0) {
	            StringBuilder nameBuilder = new StringBuilder();

	            for (Player winner : winners) {
	                nameBuilder.append(winner.getId()).append(",").append(winner.getName()).append("\n");
	            }

	            nameBuilder.deleteCharAt(nameBuilder.length() - 1);
	            outputResult += nameBuilder.toString();
	        }
	        outputStream.write(outputResult.getBytes());
	        outputStream.flush();
	        outputStream.close();
	    } catch(Exception e) {
	        System.out.println(e.toString());
	    }
	}
	
    @RequestMapping(value = "/players", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<Player> getPlayers() {
        return playerPattern.findAll();
    }
    
    @RequestMapping(value = "/players/add", method = RequestMethod.POST)
    public int addPlayer(@RequestParam(value="name") String name) {
    	if (name.isEmpty()) {
    		return 0;
    	}
    	return playerPattern.addPlayer(name);
    }
    
    @RequestMapping(value = "/players/{id}", method = RequestMethod.DELETE)
    public int deletePlayer(@PathVariable(value="id") int id) {
    	return playerPattern.deletePlayer(id);
    }
    
    @RequestMapping(value = "/players/{id}", method = RequestMethod.GET)
    public Player getPlayerById(@PathVariable(value="id") int id) {
    	return playerPattern.getPlayerById(id);
    }
    
    @RequestMapping(value = "/winners", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<Player> getWinners() {
        return playerPattern.findAllWinners();
    }
    
    @RequestMapping(value = "/winners/random", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public Player generateRandom() {
    	List<Player> players = playerPattern.findAll();
    	Random randomizer = new Random();
    	Player winner = players.get(randomizer.nextInt(players.size()));
    	playerPattern.addWinner(winner.getId(), winner.getName());
    	return winner;
    }
}

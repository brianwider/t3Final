package edu.ort.t3.player.model;

public class Player 
{
	int id;
	String name;
	public Player(int id, String name) {
		this.id = id;
		this.name = name;
	}

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
	

    public void setId(int id) {
        this.id = id;
    }
    
	public int getId() {
		return this.id;
	}

    public void setName(String name) {
        this.name = name;
    }
	
	public String getName() {
		return this.name;
	}
}
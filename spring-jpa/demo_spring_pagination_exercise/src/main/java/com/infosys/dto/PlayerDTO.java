package com.infosys.dto;

public class PlayerDTO {
	
	private Integer playerID;
	private String playerName;
	private Integer ranking;
	private String battingStyle;
	private String bowlingStyle;
	private String debutDate;
	private String country;
	
	
	public PlayerDTO(Integer playerID, String playerName, Integer ranking, String battingStyle, String bowlingStyle,
			String debutDate, String country) {
		super();
		this.playerID = playerID;
		this.playerName = playerName;
		this.ranking = ranking;
		this.battingStyle = battingStyle;
		this.bowlingStyle = bowlingStyle;
		this.debutDate = debutDate;
		this.country = country;
	}


	public Integer getPlayerID() {
		return playerID;
	}


	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}


	public String getPlayerName() {
		return playerName;
	}


	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	public Integer getRanking() {
		return ranking;
	}


	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}


	public String getBattingStyle() {
		return battingStyle;
	}


	public void setBattingStyle(String battingStyle) {
		this.battingStyle = battingStyle;
	}


	public String getBowlingStyle() {
		return bowlingStyle;
	}


	public void setBowlingStyle(String bowlingStyle) {
		this.bowlingStyle = bowlingStyle;
	}


	public String getDebutDate() {
		return debutDate;
	}


	public void setDebutDate(String debutDate) {
		this.debutDate = debutDate;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	@Override
	public String toString() {
		return "PlayerDTO [playerID=" + playerID + ", playerName=" + playerName + ", ranking=" + ranking
				+ ", battingStyle=" + battingStyle + ", bowlingStyle=" + bowlingStyle + ", debutDate=" + debutDate
				+ ", country=" + country + "]";
	}
	
	
}

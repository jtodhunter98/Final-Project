package application;

import javafx.beans.property.SimpleStringProperty;

public class MatchData 
{
	private SimpleStringProperty hero, player, kills, deaths, assists, netWorth, lastHits, denies, gpm, xpm;
	
	public MatchData(String hero, String player, String kills, String deaths, String assists, String netWorth, String lastHits, String denies, String gpm, String xpm)
	{
		this.hero = new SimpleStringProperty(hero);
		this.player = new SimpleStringProperty(player);
		this.kills = new SimpleStringProperty(kills);
		this.deaths = new SimpleStringProperty(deaths);
		this.assists = new SimpleStringProperty(assists);
		this.netWorth = new SimpleStringProperty(netWorth);
		this.lastHits = new SimpleStringProperty(lastHits);
		this.denies = new SimpleStringProperty(denies);
		this.gpm = new SimpleStringProperty(gpm);
		this.xpm =new SimpleStringProperty(xpm);
	}
	
	public SimpleStringProperty getHero()
	{
		return hero;		
	}
	public void setHero(SimpleStringProperty hero)
	{
		this.hero = hero;
	}
	
	public SimpleStringProperty getPlayer()
	{
		return player;		
	}
	public void setPlayer(SimpleStringProperty player)
	{
		this.player = player;
	}
	
	public SimpleStringProperty getKills()
	{
		return kills;		
	}
	public void setKills(SimpleStringProperty kills)
	{
		this.kills = kills;
	}
	
	public SimpleStringProperty getDeaths()
	{
		return deaths;		
	}
	public void setDeaths(SimpleStringProperty deaths)
	{
		this.deaths = deaths;
	}
	
	public SimpleStringProperty getAssists()
	{
		return assists;		
	}
	public void setAssists(SimpleStringProperty assists)
	{
		this.assists = assists;
	}
	
	public SimpleStringProperty getNetWorth()
	{
		return netWorth;		
	}
	public void setNetWorth(SimpleStringProperty netWorth)
	{
		this.netWorth = netWorth;
	}
	
	public SimpleStringProperty getLastHits()
	{
		return lastHits;		
	}
	public void setLastHits(SimpleStringProperty lastHits)
	{
		this.lastHits = lastHits;
	}
	
	public SimpleStringProperty getDenies()
	{
		return denies;		
	}
	public void setDenies(SimpleStringProperty denies)
	{
		this.denies = denies;
	}
	
	public SimpleStringProperty getGPM()
	{
		return gpm;		
	}
	public void setGPM(SimpleStringProperty gpm)
	{
		this.gpm = gpm;
	}
	
	public SimpleStringProperty getXPM()
	{
		return xpm;		
	}
	public void setXPM(SimpleStringProperty xpm)
	{
		this.xpm = xpm;
	}
}

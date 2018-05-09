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
	
	public String getHero()
	{
		return hero.get();		
	}
	public void setHero(SimpleStringProperty hero)
	{
		this.hero = hero;
	}
	
	public String getPlayer()
	{
		return player.get();	
	}
	public void setPlayer(SimpleStringProperty player)
	{
		this.player = player;
	}
	
	public String getKills()
	{
		return kills.get();		
	}
	public void setKills(SimpleStringProperty kills)
	{
		this.kills = kills;
	}
	
	public String getDeaths()
	{
		return deaths.get();		
	}
	public void setDeaths(SimpleStringProperty deaths)
	{
		this.deaths = deaths;
	}
	
	public String getAssists()
	{
		return assists.get();		
	}
	public void setAssists(SimpleStringProperty assists)
	{
		this.assists = assists;
	}
	
	public String getNetWorth()
	{
		return netWorth.get();		
	}
	public void setNetWorth(SimpleStringProperty netWorth)
	{
		this.netWorth = netWorth;
	}
	
	public String getLastHits()
	{
		return lastHits.get();		
	}
	public void setLastHits(SimpleStringProperty lastHits)
	{
		this.lastHits = lastHits;
	}
	
	public String getDenies()
	{
		return denies.get();		
	}
	public void setDenies(SimpleStringProperty denies)
	{
		this.denies = denies;
	}
	
	public String getGPM()
	{
		return gpm.get();	
	}
	public void setGPM(SimpleStringProperty gpm)
	{
		this.gpm = gpm;
	}
	
	public String getXPM()
	{
		return xpm.get();		
	}
	public void setXPM(SimpleStringProperty xpm)
	{
		this.xpm = xpm;
	}
}

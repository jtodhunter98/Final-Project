package application;

import javafx.beans.property.SimpleStringProperty;
/**
 * This class is for constructing the table that contains the Dire team's information, such as player names, and statistics
 * that have been recorded in the specified match in-game.
 * @author todjord
 *
 */
public class Dire 
{
	private SimpleStringProperty hero, player, kills, deaths, assists, netWorth, lastHits, denies, GPM, XPM;
	
	public Dire(String hero, String player, String kills, String deaths, String assists, String netWorth, String lastHits, String denies, String gpm, String xpm)
	{
		this.hero = new SimpleStringProperty(hero);
		this.player = new SimpleStringProperty(player);
		this.kills = new SimpleStringProperty(kills);
		this.deaths = new SimpleStringProperty(deaths);
		this.assists = new SimpleStringProperty(assists);
		this.netWorth = new SimpleStringProperty(netWorth);
		this.lastHits = new SimpleStringProperty(lastHits);
		this.denies = new SimpleStringProperty(denies);
		this.GPM = new SimpleStringProperty(gpm);
		this.XPM =new SimpleStringProperty(xpm);
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
		return GPM.get();	
	}
	public void setGPM(SimpleStringProperty gpm)
	{
		this.GPM = gpm;
	}
	
	public String getXPM()
	{
		return XPM.get();		
	}
	public void setXPM(SimpleStringProperty xpm)
	{
		this.XPM = xpm;
	}
}

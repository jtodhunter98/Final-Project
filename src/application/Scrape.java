package application;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * The Scrape class is dedicated to connecting to dotabuff.com and retrieving
 * the correct information so it can be displayed in the GUI.
 * @author todjord
 *
 */
public class Scrape 
{	
	/**
	 * This method will find the user name of the player that corresponds with the player ID
	 * that has been entered by the user in the GUI.
	 * @param site is passed in from the enterID() method in the Controller class. It is used to connect
	 * to the URL that holds the player name.
	 * @return Returns a string that will be assigned to a label object in the Controller class
	 * to display the text that is returned here.
	 * @throws IOException
	 */
	public String listHeader(Document site) throws IOException
	{		
		Elements playerElement = site.getElementsByClass("header-content-title").select("h1");
		String player = playerElement.first().text();
		int find = player.indexOf("M");
		player = player.substring(0, find);
		String header = "Most recent 10 matches for " + player;
		return header;
	}//end of listHeader method
	/**
	 * This method is almost an exact duplicate of the listHeader(Document) method, except it
	 * returns specifically the player's name alone, and nothing else in the string.
	 * @param site is passed from the enterID() method.
	 * @return Returns the player's name as a string to be assigned to a label in the Controller class.
	 */
	public String playerName(Document site)
	{
		Elements playerElement = site.getElementsByClass("header-content-title").select("h1");
		String player = playerElement.first().text();
		int find = player.indexOf("M");
		player = player.substring(0, find);
		return player;
	}//end of playerName method
	/**
	 * Will connect to the URL and find the player's total wins.
	 * @param player_id is passed from the enterID() method so it can connect to the correct URL.
	 * @return Returns a string that will be set to the winsLabel object in the Controller class.
	 * @throws IOException
	 */
	public String playerWins(String player_id) throws IOException
	{
		String url = "https://www.dotabuff.com/players/" + player_id;
		Document site = Jsoup.connect(url).get();
		Elements collectWins = site.getElementsByClass("wins");
		String wins = collectWins.text();
		String line = "Wins: " + wins;
		return line;
	}
	
	/**
	 * Will connect to the URL and find the player's total losses.
	 * @param player_id passed from the enterID() method so it can connect to the correct URL.
	 * @return Returns a string that will be set to the lossesLabel object in the Controller class.
	 * @throws IOException
	 */
	public String playerLosses(String player_id) throws IOException
	{
		String url = "https://www.dotabuff.com/players/" + player_id;
		Document site = Jsoup.connect(url).get();
		Elements collectLosses = site.getElementsByClass("losses");
		String losses = collectLosses.text();
		String line = "Losses: " + losses;
		return line;
	}
	/**
	 * Will connect to the URL and find the player's win rate.
	 * @param player_id passed from the enterID() method so it can connect to the correct URL.
	 * @return Returns a string that will be set to the winRateLabel object in the Controller class.
	 * @throws IOException
	 */
	public String playerWinRate(String player_id) throws IOException
	{
		String url = "https://www.dotabuff.com/players/" + player_id;
		Document site = Jsoup.connect(url).get();
		
		int tag = 2;
		Elements collectWinRate = site.getElementsByTag("dt");
		Element winRateElement = collectWinRate.get(tag);
		String winRate = winRateElement.text();
		if(!winRate.equals("Win Rate"))
		{
			tag = 4;
			winRateElement = collectWinRate.get(tag);
			winRate = winRateElement.text();
		}
		collectWinRate = site.getElementsByTag("dd");
		winRateElement = collectWinRate.get(tag);
		winRate = winRateElement.text();
		String line = "Win Rate: " + winRate;
		return line;
	}
	/**
	 * Used to return a string to display player rank in the GUI
	 * @param player_id
	 * @return Returns the player's rank in a string
	 * @throws IOException
	 */
	public String playerRank(String player_id) throws IOException
	{
		String url = "https://www.dotabuff.com/players/" + player_id;
		Document site = Jsoup.connect(url).get();
		Elements collectRank = site.getElementsByClass("rank-tier-wrapper");		
		String rank = collectRank.toString();
		int find1 = rank.indexOf('"', 50) + 1;
		int find2 = rank.indexOf('"', 52);		
		rank = rank.substring(find1, find2);
		return rank;
	}
	
	/**
	 * Used to return a string to display the most played hero in the GUI
	 * @param player_id
	 * @return Returns the hero name in a string
	 * @throws IOException
	 */
	public String firstHero(String player_id) throws IOException
	{
		String url = "https://www.dotabuff.com/players/" + player_id;
		Document site = Jsoup.connect(url).get();
		Elements elements = site.getElementsByClass("r-none-mobile").select("a");
		Element element = elements.get(0);
		String hero = element.text();
		
		Elements elements1 = site.getElementsByClass("r-body");
		Element element1 = elements1.get(1);
		String wins = element1.text();
		
		Elements elements2 = site.getElementsByClass("r-body");
		Element element2 = elements2.get(2);
		String winRate = element2.text();
		String line = hero + " (Wins: " + wins + ", Win Rate: " + winRate + ")";
		return line;
	}
	
	/**
	 * Used to return a string to display the second most played hero in the GUI
	 * @param player_id
	 * @return Returns the hero name in a string
	 * @throws IOException
	 */
	public String secondHero(String player_id) throws IOException
	{
		String url = "https://www.dotabuff.com/players/" + player_id;
		Document site = Jsoup.connect(url).get();
		Elements elements = site.getElementsByClass("r-none-mobile").select("a");
		Element element = elements.get(2);
		String hero = element.text();
		
		Elements elements1 = site.getElementsByClass("r-body");
		Element element1 = elements1.get(7);
		String wins = element1.text();
		
		Elements elements2 = site.getElementsByClass("r-body");
		Element element2 = elements2.get(8);
		String winRate = element2.text();
		String line = hero + " (Wins: " + wins + ", Win Rate: " + winRate + ")";
		return line;
	}
	
	/**
	 * Used to return a string to display the third most played hero in the GUI
	 * @param player_id
	 * @return Returns the hero name in a string
	 * @throws IOException
	 */
	public String thirdHero(String player_id) throws IOException
	{
		String url = "https://www.dotabuff.com/players/" + player_id;
		Document site = Jsoup.connect(url).get();
		Elements elements = site.getElementsByClass("r-none-mobile").select("a");
		Element element = elements.get(4);
		String hero = element.text();
		
		Elements elements1 = site.getElementsByClass("r-body");
		Element element1 = elements1.get(13);
		String wins = element1.text();
		
		Elements elements2 = site.getElementsByClass("r-body");
		Element element2 = elements2.get(14);
		String winRate = element2.text();
		String line = hero + " (Wins: " + wins + ", Win Rate: " + winRate + ")";
		return line;
	}
	
	/**
	 * Used to create a list of the most recent 10 matches for the player so it can later be
	 * displayed in the GUI.
	 * @param site is passed in to get the correct URL to connect to.
	 * @return Returns an ArrayList that will contain the past 10 matches and their outcomes,
	 * which will later be displayed on multiple labels in the GUI.
	 * @throws IOException
	 */
	public ArrayList<String> displayMatches(Document site) throws IOException
	{		
		Elements playerElement = site.getElementsByClass("header-content-title").select("h1");
		Elements heroElement = site.getElementsByClass("cell-large").select("a");
		Elements findDate = site.getElementsByClass("subtext").select("time");
		Elements collectOutcome = site.getElementsByTag("td").select("td");
		
		String player = playerElement.first().text();
		int find = player.indexOf("M");
		player = player.substring(0, find);		
		
		ArrayList<String> displayList = new ArrayList<String>(10);
		//loop to display most recent matches
		int tag = 9;
		for(int i = 0; i < 10; i++)
		{
			String hero = heroElement.get(i).text();
			Element dateElement = findDate.get(i);
			Element outcomeElement = collectOutcome.get(tag);
			String date = dateElement.toString();
			int date1 = date.indexOf("title=") + 7;
			int date2 = date.indexOf(" ", 65);		
			date = date.substring(date1,date2);
			String outcome = outcomeElement.text();
			outcome = outcome.substring(0, outcome.indexOf(" "));
			String line = i+1 + ") " + hero + " (" + date + ") - " + outcome;			
			displayList.add(line);
			tag = tag + 8;
		}
		return displayList;
	}
	/**
	 * Creates a list of the match IDs, which are needed in the URL to point the web site to
	 * a specific match, which will later be displayed in the GUI's table.
	 * @param site is passed in to get the correct URL to connect to.
	 * @return Returns an ArrayList of the most recent 10 matches' ID numbers, which will be selected
	 * from in the selectMatch() method from the Controller class.
	 * @throws IOException
	 */
	public ArrayList<Long> collectMatches(Document site) throws IOException
	{		
		//collect match IDs
		Elements win = site.getElementsByClass("won").select("a[href]");
		Elements loss = site.getElementsByClass("lost").select("a[href]");
		ArrayList<Long> matchList = new ArrayList<Long>();
		
		//winning IDs loop
		for(int i = 0; i < 10; i++)
		{
			Element wLoop = win.get(i);
			String wHref = wLoop.toString();
			int findFirstW = wHref.indexOf("/", 22);
			int findLastW = wHref.indexOf('"', 21);
			long matchID = Long.parseLong(wHref.substring(findFirstW+1, findLastW));
			matchList.add(matchID);
		}
		
		//losing IDs loop
		for(int i = 0; i < 10; i++)
		{
			Element lLoop = loss.get(i);
			String lHref = lLoop.toString();
			int findFirstL = lHref.indexOf("/", 23);
			int findLastL = lHref.indexOf('"', 22);
			long matchID = Long.parseLong(lHref.substring(findFirstL+1, findLastL));
			matchList.add(matchID);
		}
		
		Collections.sort(matchList);
		Collections.reverse(matchList);
		ArrayList<Long> matchIDs = new ArrayList<Long>(10);
		
		//get correct matches
		for(int i = 0; i < 10; i++)
		{
			long correctID = matchList.get(i);
			matchIDs.add(correctID);
		}
		return matchIDs;
	}
	
	/**
	 * Connects to the URL and creates a multidimensional array that holds all of the information from the
	 * radiant team.
	 * @param match_url is passed from the selectMatch() method in the Controller class, so it
	 * can connect to the match's URL.
	 * @return Returns a multidimensional array that contains the radiant team's info, such as player
	 * names, hero names, and their in game statistics
	 * @throws IOException
	 */
	public String[][] radiantArray(String match_url) throws IOException
	{
		String url = match_url;
		Document matchInfo = Jsoup.connect(url).get();
		String [][] radiant = new String [5][10];
		
		//values to find correct element
		int tag = 5;
		int next = 20;
		
		//check if match was analyzed by truesight beta
		boolean truesight = true;
		int findClass = 3;
		Elements collectClass = matchInfo.getElementsByTag("tr").select("td");
		Element classElement = collectClass.get(findClass);
		String classString = classElement.toString();
		int correct1 = classString.indexOf('"') + 1;
		int correct2 = classString.indexOf('"',11);
		classString = classString.substring(correct1, correct2);
		if(!"tf-pl single-lines".equals(classString))
		{
			tag = 3;
			next = 18;
			truesight = false;
		}
		
		if(truesight == false)
		{
			int findCell = 16;
			Elements collectCell = matchInfo.getElementsByTag("tr").select("td");
			Element cellElement = collectCell.get(findCell);
			String cellString = cellElement.toString();
			int collect3 = cellString.indexOf('"', 32) + 1;
			int collect4 = cellString.indexOf('"', 47);
			cellString = cellString.substring(collect3, collect4);
			if(!"color-item-observer-ward".equals(cellString))
			{
				next = 17;
			}
		}
		
		
		
		//add hero names
		for(int i = 0; i < 5; i++)
		{
			Elements collectHeroes = matchInfo.getElementsByClass("image-hero image-icon image-overlay");
			Element heroElement = collectHeroes.get(i);
			String hero = heroElement.toString();
			int find1 = hero.indexOf('"', 76) + 1;
			int find2 = hero.indexOf('"', 77);
			hero = hero.substring(find1, find2);
			radiant [i][0] = hero;
		}//end of hero name loop
		
		//add player names
		for(int i = 0; i < 5; i++)
		{
			Elements collectPlayers = matchInfo.getElementsByClass("tf-pl single-lines");
			Element playerElement = collectPlayers.get(i);
			String playerLong = playerElement.toString();						
			String regex = "esports";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(playerLong);
			boolean registered = false;
			while(matcher.find())
			{
				registered = true;
			}
			if(registered)
			{
				int find1 = playerLong.indexOf(">", 32) + 1;
				int find2 = playerLong.indexOf("<", 116);
				String player = playerLong.substring(find1, find2);
				radiant [i][1] = player;
			}
			else
			{
				radiant [i][1] = "Anonymous";
			}			
		}//end of player name loop
		
		
		//add kills
		for(int i = 0; i < 5; i++)
		{
			Elements collectKills = matchInfo.getElementsByTag("tr").select("td");
			Element killsElement = collectKills.get(tag);
			String kills = killsElement.toString();
			int find1 = kills.indexOf(">") + 1;
			int find2 = kills.indexOf("<",1);			
			if(find2 == 33)
			{
				find1 = kills.indexOf(">", 34) + 1;
				find2 = kills.indexOf("<", 34);
				kills = kills.substring(find1, find2);
				radiant [i][2] = kills;
			}
			else
			{
				kills = kills.substring(find1, find2);
				radiant [i][2] = kills;
			}
			tag = tag + next;
			
		
		}//end of kills loop
		
		//correct tag variable
		if(truesight == true)
		{
			tag = 5;			
		}
		if(truesight == false)
		{
			tag = 3;			
		}
		
		//add deaths
		for(int i = 0; i < 5; i++)
		{
			Elements collectDeaths = matchInfo.getElementsByTag("tr").select("td");
			Element deathsElement = collectDeaths.get(tag + 1);
			String deaths = deathsElement.toString();
			int find1 = deaths.indexOf(">") + 1;
			int find2 = deaths.indexOf("<",1);
			if(find2 == 44)
			{
				find1 = deaths.indexOf(">", 45) + 1;
				find2 = deaths.indexOf("<", 45);
				deaths = deaths.substring(find1, find2);
				radiant [i][3] = deaths;
			}
			else
			{
				deaths = deaths.substring(find1, find2);
				radiant [i][3] = deaths;
			}
			tag = tag + next;
		}//end of deaths loop		
		
		//correct tag variable
				if(truesight == true)
				{
					tag = 5;			
				}
				if(truesight == false)
				{
					tag = 3;			
				}
		
		//add assists loop
		for(int i = 0; i < 5; i++)
		{
			Elements collectAssists = matchInfo.getElementsByTag("tr").select("td");
			Element assistsElement = collectAssists.get(tag + 2);
			String assists = assistsElement.toString();
			int find1 = assists.indexOf(">") + 1;
			int find2 = assists.indexOf("<",1);	
			if(find2 == 33)
			{
				find1 = assists.indexOf(">", 34) + 1;
				find2 = assists.indexOf("<", 34);
				assists = assists.substring(find1, find2);
				radiant [i][4] = assists;
			}
			else
			{
				assists = assists.substring(find1, find2);
				radiant [i][4] = assists;
			}
			tag = tag + next;
		}//end of assists loop
		
		//correct tag variable
				if(truesight == true)
				{
					tag = 5;			
				}
				if(truesight == false)
				{
					tag = 3;			
				}
		
		//add player net worth loop
		for(int i = 0; i < 5; i++)
		{
			Elements collectNet = matchInfo.getElementsByTag("tr").select("td");
			Element netElement = collectNet.get(tag + 3);
			String net = netElement.toString();
			int find1 = net.indexOf(">",50) + 1;
			int find2 = net.indexOf("<",50);
			
			if(find2 == 104)
			{
				find1 = net.indexOf(">", 105) + 1;
				find2 = net.indexOf("<", 105);
				net = net.substring(find1, find2);
				radiant [i][5] = net;
			}
			else
			{
				net = net.substring(find1, find2);
				radiant [i][5] = net;
			}
			tag = tag + next;
		}//end of net worth loop
		
		//correct tag variable
				if(truesight == true)
				{
					tag = 5;			
				}
				if(truesight == false)
				{
					tag = 3;			
				}
		
		//add total last hits loop
		for(int i = 0; i < 5; i++)
		{
			Elements collectLH = matchInfo.getElementsByTag("tr").select("td");
			Element lhElement = collectLH.get(tag + 4);
			String lh = lhElement.toString();
			int find1 = lh.indexOf(">") + 1;
			int find2 = lh.indexOf("<",1);			
			if(find2 == 44)
			{
				find1 = lh.indexOf(">", 45) + 1;
				find2 = lh.indexOf("<", 45);
				lh = lh.substring(find1, find2);
				radiant [i][6] = lh;
			}
			else
			{
				lh = lh.substring(find1, find2);
				radiant [i][6] = lh;
			}
			tag = tag + next;
		}//end of last hit loop
		
		//correct tag variable
				if(truesight == true)
				{
					tag = 5;			
				}
				if(truesight == false)
				{
					tag = 3;			
				}
		
		//add denies loop
		for(int i = 0; i < 5; i++)
		{
			Elements collectdenies = matchInfo.getElementsByTag("tr").select("td");
			Element deniesElement = collectdenies.get(tag + 6);
			String denies = deniesElement.toString();
			int find1 = denies.indexOf(">") + 1;
			int find2 = denies.indexOf("<",1);			
			if(find2 == 45)
			{
				find1 = denies.indexOf(">", 46) + 1;
				find2 = denies.indexOf("<", 46);
				denies = denies.substring(find1, find2);
				radiant [i][7] = denies;
			}
			else
			{
				denies = denies.substring(find1, find2);
				radiant [i][7] = denies;
			}
			tag = tag + next;
		}//end of denies loop
		
		//correct tag variable
				if(truesight == true)
				{
					tag = 5;			
				}
				if(truesight == false)
				{
					tag = 3;			
				}
		
		//add gpm loop
		for(int i = 0; i < 5; i++)
		{
			Elements collectGPM = matchInfo.getElementsByTag("tr").select("td");
			Element gpmElement = collectGPM.get(tag + 7);
			String gpm = gpmElement.toString();
			int find1 = gpm.indexOf(">") + 1;
			int find2 = gpm.indexOf("<",1);			
			if(find2 == 44)
			{
				find1 = gpm.indexOf(">", 45) + 1;
				find2 = gpm.indexOf("<", 45);
				gpm = gpm.substring(find1, find2);
				radiant [i][8] = gpm;
			}
			else
			{
				gpm = gpm.substring(find1, find2);
				radiant [i][8] = gpm;
			}
			tag = tag + next;
		}//end of gpm loop
		
		//correct tag variable
				if(truesight == true)
				{
					tag = 5;			
				}
				if(truesight == false)
				{
					tag = 3;			
				}
		
		//add xpm loop
		for(int i = 0; i < 5; i++)
		{
			Elements collectXPM = matchInfo.getElementsByTag("tr").select("td");
			Element xpmElement = collectXPM.get(tag + 9);
			String xpm = xpmElement.toString();
			int find1 = xpm.indexOf(">") + 1;
			int find2 = xpm.indexOf("<",1);			
			if(find2 == 45)
			{
				find1 = xpm.indexOf(">", 46) + 1;
				find2 = xpm.indexOf("<", 46);
				xpm = xpm.substring(find1, find2);
				radiant [i][9] = xpm;
			}
			else
			{
				xpm = xpm.substring(find1, find2);
				radiant [i][9] = xpm;
			}
			tag = tag + next;
		}//end of xpm loop
		return radiant;
	}//end of radiantArray() method
	/**
	 * Connects to the URL and creates a multidimensional array that holds all of the information from the
	 * dire team.
	 * match_url is passed from the selectMatch() method in the Controller class, so it
	 * can connect to the match's URL.
	 * @return Returns a multidimensional array that contains the dire team's info, such as player
	 * names, hero names, and their in game statistics
	 * @throws IOException
	 */
	public String[][] direArray(String match_url) throws IOException
	{		
		String url = match_url;
		Document matchInfo = Jsoup.connect(url).get();
		String [][] dire = new String [5][10];
		
		int tag = 125;
		int next = 20;
		
		//check if match was analyzed by truesight beta
		boolean truesight = true;
		boolean wards = true;
		int findClass = 3;		
		Elements collectClass = matchInfo.getElementsByTag("tr").select("td");
		Element classElement = collectClass.get(findClass);
		String classString = classElement.toString();
		int correct1 = classString.indexOf('"') + 1;
		int correct2 = classString.indexOf('"',11);
		classString = classString.substring(correct1, correct2);
		if(!"tf-pl single-lines".equals(classString))
		{
			tag = 110;
			next = 18;
			truesight = false;
		}
		
		if(truesight == false)
		{
			int findCell = 16;
			Elements collectCell = matchInfo.getElementsByTag("tr").select("td");
			Element cellElement = collectCell.get(findCell);
			String cellString = cellElement.toString();
			int collect3 = cellString.indexOf('"', 32) + 1;
			int collect4 = cellString.indexOf('"', 47);
			cellString = cellString.substring(collect3, collect4);
			if(!"color-item-observer-ward".equals(cellString))
			{
				tag = 105;
				next = 17;
				wards = false;
			}
		}		
		
		//add hero names
		int heroRow = 0;
		for(int i = 5; i < 10; i++)
		{
			Elements collectHeroes = matchInfo.getElementsByClass("image-hero image-icon image-overlay");
			Element heroElement = collectHeroes.get(i);
			String hero = heroElement.toString();
			int find1 = hero.indexOf('"', 76) + 1;
			int find2 = hero.indexOf('"', 77);
			hero = hero.substring(find1, find2);
			dire [heroRow][0] = hero;
			heroRow++;
		}//end of hero name loop
		
		//add player names
		int nameRow = 0;
		for(int i = 5; i < 10; i++)
		{
			Elements collectPlayers = matchInfo.getElementsByClass("tf-pl single-lines");
			Element playerElement = collectPlayers.get(i);
			String playerLong = playerElement.toString();
			String regex = "esports";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(playerLong);
			
			boolean registered = false;
			while(matcher.find())
			{
				registered = true;
			}
			if(registered)
			{
				int find1 = playerLong.indexOf(">", 32) + 1;
				int find2 = playerLong.indexOf("<", 116);
				String player = playerLong.substring(find1, find2);
				dire [nameRow][1] = player;
			}
			else
			{
				dire [nameRow][1] = "Anonymous";
			}
			nameRow++;
		}//end of player name loop
		
		
		//add kills
		for(int i = 0; i < 5; i++)
		{
			Elements collectKills = matchInfo.getElementsByTag("tr").select("td");
			Element killsElement = collectKills.get(tag);
			String kills = killsElement.toString();
			int find1 = kills.indexOf(">") + 1;
			int find2 = kills.indexOf("<",1);		
			if(find2 == 33)
			{
				find1 = kills.indexOf(">", 34) + 1;
				find2 = kills.indexOf("<", 34);
				kills = kills.substring(find1, find2);
				dire [i][2] = kills;
			}
			else
			{
				kills = kills.substring(find1, find2);
				dire [i][2] = kills;
			}
			tag = tag + next;
		}//end of kills loop
		
		//correct tag variable
		if(truesight == true)
		{
			tag = 125;			
		}
		if(truesight == false)
		{
			if(wards)
			{
				tag = 110;
			}
			if(wards == false)
			{
				tag= 105;
			}
						
		}
		
		//add deaths
		for(int i = 0; i < 5; i++)
		{
			Elements collectDeaths = matchInfo.getElementsByTag("tr").select("td");
			Element deathsElement = collectDeaths.get(tag + 1);
			String deaths = deathsElement.toString();
			int find1 = deaths.indexOf(">") + 1;
			int find2 = deaths.indexOf("<",1);
			if(find2 == 44)
			{
				find1 = deaths.indexOf(">", 45) + 1;
				find2 = deaths.indexOf("<", 45);
				deaths = deaths.substring(find1, find2);
				dire [i][3] = deaths;
			}
			else
			{
				deaths = deaths.substring(find1, find2);
				dire [i][3] = deaths;
			}
			tag = tag + next;
		}//end of deaths loop
		
		//correct tag variable
				if(truesight == true)
				{
					tag = 125;			
				}
				if(truesight == false)
				{
					if(wards)
					{
						tag = 110;
					}
					if(wards == false)
					{
						tag = 105;
					}
								
				}
		
		//add assists loop
		for(int i = 0; i < 5; i++)
		{
			Elements collectAssists = matchInfo.getElementsByTag("tr").select("td");
			Element assistsElement = collectAssists.get(tag + 2);
			String assists = assistsElement.toString();
			int find1 = assists.indexOf(">") + 1;
			int find2 = assists.indexOf("<",1);			
			if(find2 == 33)
			{
				find1 = assists.indexOf(">", 34) + 1;
				find2 = assists.indexOf("<", 34);
				assists = assists.substring(find1, find2);
				dire [i][4] = assists;
			}
			else
			{
				assists = assists.substring(find1, find2);
				dire [i][4] = assists;
			}
			tag = tag + next;
		}//end of assists loop
		
		//correct tag variable
				if(truesight == true)
				{
					tag = 125;			
				}
				if(truesight == false)
				{
					if(wards)
					{
						tag = 110;
					}
					if(wards == false)
					{
						tag = 105;
					}
								
				}
		
		//add player net worth loop
		for(int i = 0; i < 5; i++)
		{
			Elements collectNet = matchInfo.getElementsByTag("tr").select("td");
			Element netElement = collectNet.get(tag + 3);
			String net = netElement.toString();
			int find1 = net.indexOf(">",50) + 1;
			int find2 = net.indexOf("<",50);
			if(find2 == 104)
			{
				find1 = net.indexOf(">", 105) + 1;
				find2 = net.indexOf("<", 105);
				net = net.substring(find1, find2);
				dire [i][5] = net;
			}
			else
			{
				net = net.substring(find1, find2);
				dire [i][5] = net;
			}
			tag = tag + next;
		}//end of net worth loop
		
		//correct tag variable
				if(truesight == true)
				{
					tag = 125;			
				}
				if(truesight == false)
				{
					if(wards)
					{
						tag = 110;
					}
					if(wards == false)
					{
						tag = 105;
					}
								
				}

		//add total last hits loop
		for(int i = 0; i < 5; i++)
		{
			Elements collectLH = matchInfo.getElementsByTag("tr").select("td");
			Element lhElement = collectLH.get(tag + 4);
			String lh = lhElement.toString();
			int find1 = lh.indexOf(">") + 1;
			int find2 = lh.indexOf("<",1);			
			if(find2 == 44)
			{
				find1 = lh.indexOf(">", 45) + 1;
				find2 = lh.indexOf("<", 45);
				lh = lh.substring(find1, find2);
				dire [i][6] = lh;
			}
			else
			{
				lh = lh.substring(find1, find2);
				dire [i][6] = lh;
			}
			tag = tag + next;
		}//end of last hit loop
		
		//correct tag variable
				if(truesight == true)
				{
					tag = 125;			
				}
				if(truesight == false)
				{
					if(wards)
					{
						tag = 110;
					}
					if(wards == false)
					{
						tag = 105;
					}
								
				}

		//add denies loop
		for(int i = 0; i < 5; i++)
		{
			Elements collectdenies = matchInfo.getElementsByTag("tr").select("td");
			Element deniesElement = collectdenies.get(tag + 6);
			String denies = deniesElement.toString();
			int find1 = denies.indexOf(">") + 1;
			int find2 = denies.indexOf("<",1);			
			if(find2 == 45)
			{
				find1 = denies.indexOf(">", 46) + 1;
				find2 = denies.indexOf("<", 46);
				denies = denies.substring(find1, find2);
				dire [i][7] = denies;
			}
			else
			{
				denies = denies.substring(find1, find2);
				dire [i][7] = denies;
			}
			tag = tag + next;
		}//end of denies loop
		
		//correct tag variable
				if(truesight == true)
				{
					tag = 125;			
				}
				if(truesight == false)
				{
					if(wards)
					{
						tag = 110;
					}
					if(wards == false)
					{
						tag = 105;
					}
								
				}

		//add gpm loop
		for(int i = 0; i < 5; i++)
		{
			Elements collectGPM = matchInfo.getElementsByTag("tr").select("td");
			Element gpmElement = collectGPM.get(tag + 7);
			String gpm = gpmElement.toString();
			int find1 = gpm.indexOf(">") + 1;
			int find2 = gpm.indexOf("<",1);			
			if(find2 == 44)
			{
				find1 = gpm.indexOf(">", 45) + 1;
				find2 = gpm.indexOf("<", 45);
				gpm = gpm.substring(find1, find2);
				dire [i][8] = gpm;
			}
			else
			{
				gpm = gpm.substring(find1, find2);
				dire [i][8] = gpm;
			}
			tag = tag + next;
		}//end of gpm loop
		
		//correct tag variable
				if(truesight == true)
				{
					tag = 125;			
				}
				if(truesight == false)
				{
					if(wards)
					{
						tag = 110;
					}
					if(wards == false)
					{
						tag= 105;
					}
								
				}

		//add xpm loop
		for(int i = 0; i < 5; i++)
		{
			Elements collectXPM = matchInfo.getElementsByTag("tr").select("td");
			Element xpmElement = collectXPM.get(tag + 9);
			String xpm = xpmElement.toString();
			int find1 = xpm.indexOf(">") + 1;
			int find2 = xpm.indexOf("<",1);			
			if(find2 == 45)
			{
				find1 = xpm.indexOf(">", 46) + 1;
				find2 = xpm.indexOf("<", 46);
				xpm = xpm.substring(find1, find2);
				dire [i][9] = xpm;
			}
			else
			{
				xpm = xpm.substring(find1, find2);
				dire [i][9] = xpm;
			}
		}//end of xpm loop
		return dire;		
	}//end of direArray() method
}



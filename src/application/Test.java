package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Test 
{

	public static void main(String[] args) throws IOException 
	{
		playerStats();
	}
	
	public static void collectMatches() throws IOException
	{
		String url = "https://www.dotabuff.com/players/151912302/matches";
		Document site = Jsoup.connect(url).get();
		
		int tag = 9;
		for(int i = 0; i < 10; i++)
		{
			Elements collectOutcome = site.getElementsByTag("td").select("td");
			Element outcomeElement = collectOutcome.get(tag);
			String outcome = outcomeElement.text();
			outcome = outcome.substring(0, outcome.indexOf("h")+1);
			System.out.println(outcome);
			tag = tag + 8;
		}		
		
	}
	
	public static void playerStats() throws IOException
	{
		int tag = 2;
		String url = "https://www.dotabuff.com/players/183078297";
		Document site = Jsoup.connect(url).get();
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
		
		System.out.println(winRate);
	}
	

}

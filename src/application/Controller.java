package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller
{
	@FXML
    private TextField idField;	
	
	@FXML
    private Label label1;
	
	@FXML
    private TextField txtField;
	
	@FXML
	private Label displayUser;
	
	@FXML
	private Label match0;

	@FXML
	private Label match1;

	@FXML
	private Label match2;

	@FXML
	private Label match3;

	@FXML
	private Label match4;

	@FXML
	private Label match5;

	@FXML
	private Label match6;

	@FXML
	private Label match7;

	@FXML
	private Label match8;

	@FXML
	private Label match9;
	
	@FXML
    private TextField matchField;
	
	@FXML
    private TextField titleField;
	
	@FXML private TableView<MatchData> matchTable;
	@FXML private TableColumn<MatchData, String> heroColumn;
	@FXML private TableColumn<MatchData, String> playerColumn;
	@FXML private TableColumn<MatchData, String> killsColumn;
	@FXML private TableColumn<MatchData, String> deathsColumn;
	@FXML private TableColumn<MatchData, String> assistsColumn;
	@FXML private TableColumn<MatchData, String> netWorthColumn;
	@FXML private TableColumn<MatchData, String> lastHitsColumn;
	@FXML private TableColumn<MatchData, String> deniesColumn;
	@FXML private TableColumn<MatchData, String> gpmColumn;
	@FXML private TableColumn<MatchData, String> xpmColumn;
	
	//triggers when the user pastes an ID into the first text field
	@FXML
	public String enterID() throws IOException
	{
		Scrape scrape = new Scrape();
		String id = idField.getText();
		String player_url = "https://www.dotabuff.com/players/"+id+"/matches";
		Document site = Jsoup.connect(player_url).get();
		String displayPlayer = scrape.playerName(site);
		ArrayList<String> displayList = scrape.displayMatches(site);
		displayUser.setText(displayPlayer);
		match0.setText(displayList.get(0));
		match1.setText(displayList.get(1));
		match2.setText(displayList.get(2));
		match3.setText(displayList.get(3));
		match4.setText(displayList.get(4));
		match5.setText(displayList.get(5));
		match6.setText(displayList.get(6));
		match7.setText(displayList.get(7));
		match8.setText(displayList.get(8));
		match9.setText(displayList.get(9));
		return player_url;
	}	
	
	//triggers when the user enters the number of a match to select
	@FXML
	public String selectMatch() throws IOException, SQLException
	{
		Scrape scrape = new Scrape();
		String player_url = enterID();
		Document site = Jsoup.connect(player_url).get();
		ArrayList<Long> idList = scrape.collectMatches(site);
		int input = Integer.parseInt(matchField.getText()) - 1;
		long selection = idList.get(input);		
		String matchID = Long.toString(selection);
		String match_url = "https://www.dotabuff.com/matches/"+matchID;
		
		String [][] matchArray = new String [10][10];
		String [][] radiant = scrape.radiantArray(match_url);
		String [][] dire = scrape.direArray(match_url);
		
		//loop to create 2d array that contains both teams' info
		for(int c = 0; c < 10; c++)
		{
			for(int r = 0; r < 10; r++)
			{
				if(r < 5)
				{
					matchArray[r][c] = radiant[r][c];					
				}
				else
				{
					matchArray[r][c] = dire[r-5][c];				
				}
			}
		}
		
		heroColumn.setCellValueFactory(new PropertyValueFactory<MatchData, String>("hero"));
		playerColumn.setCellValueFactory(new PropertyValueFactory<MatchData, String>("player"));
		killsColumn.setCellValueFactory(new PropertyValueFactory<MatchData, String>("kills"));
		deathsColumn.setCellValueFactory(new PropertyValueFactory<MatchData, String>("deaths"));
		assistsColumn.setCellValueFactory(new PropertyValueFactory<MatchData, String>("assists"));
		netWorthColumn.setCellValueFactory(new PropertyValueFactory<MatchData, String>("netWorth"));
		lastHitsColumn.setCellValueFactory(new PropertyValueFactory<MatchData, String>("lastHits"));
		deniesColumn.setCellValueFactory(new PropertyValueFactory<MatchData, String>("denies"));
		gpmColumn.setCellValueFactory(new PropertyValueFactory<MatchData, String>("gpm"));
		xpmColumn.setCellValueFactory(new PropertyValueFactory<MatchData, String>("xpm"));
		
		ObservableList<MatchData> data = FXCollections.observableArrayList();
		String hero, player, kills, deaths, assists, netWorth, lastHits, denies, gpm, xpm;
		for(int r = 0; r < 10; r++)
		{
			hero = matchArray[r][0];
			player = matchArray[r][1];
			kills = matchArray[r][2];
			deaths = matchArray[r][3];
			assists = matchArray[r][4];
			netWorth = matchArray[r][5];
			lastHits = matchArray[r][6];
			denies = matchArray[r][7];
			gpm = matchArray[r][8];
			xpm = matchArray[r][9];
			
			data.add(new MatchData(hero,player,kills,deaths,assists,netWorth,lastHits,denies, gpm, xpm));
		}
		
		matchTable.setItems(data);
		return match_url;
	}
	
	//button to save match to the database	
	@FXML
	public void saveMatch() throws IOException, SQLException 
	{
		String [][] matchArray = getMatchArray();		
		Save database = new Save();
		String title = titleField.getText();
		database.create(title, matchArray);
		System.out.println(title);
	}	
	
	public String[][] getMatchArray() throws IOException, SQLException
	{
		Scrape scrape = new Scrape();
		String [][] matchArray = new String [10][10];
		String match_url = selectMatch();
		String [][] radiant = scrape.radiantArray(match_url);
		String [][] dire = scrape.direArray(match_url);
		
		//loop to create 2d array that contains both teams' info
		for(int c = 0; c < 10; c++)
		{
			for(int r = 0; r < 10; r++)
			{
				if(r < 5)
				{
					matchArray[r][c] = radiant[r][c];					
				}
				else
				{
					matchArray[r][c] = dire[r-5][c];				
				}
			}
		}
		return matchArray;
	}	
}
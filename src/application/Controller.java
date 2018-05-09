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
    private Label playerLabel;
	
	@FXML
    private Label winsLabel;

    @FXML
    private Label lossesLabel;

    @FXML
    private Label winRateLabel;
	
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
	
	@FXML
	private Label saveSuccessfulLabel;
	
	@FXML private TableView<Radiant> radiantTable;
	@FXML private TableColumn<Radiant, String> heroColumn;
	@FXML private TableColumn<Radiant, String> playerColumn;
	@FXML private TableColumn<Radiant, String> killsColumn;
	@FXML private TableColumn<Radiant, String> deathsColumn;
	@FXML private TableColumn<Radiant, String> assistsColumn;
	@FXML private TableColumn<Radiant, String> netWorthColumn;
	@FXML private TableColumn<Radiant, String> lastHitsColumn;
	@FXML private TableColumn<Radiant, String> deniesColumn;
	@FXML private TableColumn<Radiant, String> gpmColumn;
	@FXML private TableColumn<Radiant, String> xpmColumn;
	
	@FXML private TableView<Dire> direTable;
	@FXML private TableColumn<Dire, String> direHeroColumn;
	@FXML private TableColumn<Dire, String> direPlayerColumn;
	@FXML private TableColumn<Dire, String> direKillsColumn;
	@FXML private TableColumn<Dire, String> direDeathsColumn;
	@FXML private TableColumn<Dire, String> direAssistsColumn;
	@FXML private TableColumn<Dire, String> direNetWorthColumn;
	@FXML private TableColumn<Dire, String> direLastHitsColumn;
	@FXML private TableColumn<Dire, String> direDeniesColumn;
	@FXML private TableColumn<Dire, String> dire_gpmColumn;
	@FXML private TableColumn<Dire, String> dire_xpmColumn;
	
	//triggers when the user pastes an ID into the first text field
	@FXML
	public String enterID() throws IOException
	{
		Scrape scrape = new Scrape();
		String player_id = idField.getText();
		String player_url = "https://www.dotabuff.com/players/"+player_id+"/matches";
		Document site = Jsoup.connect(player_url).get();
		String player = scrape.playerName(site);
		String wins = scrape.playerWins(player_id);
		String losses = scrape.playerLosses(player_id);
		String winRate = scrape.playerWinRate(player_id);
		String displayPlayer = scrape.listHeader(site);
		ArrayList<String> displayList = scrape.displayMatches(site);
		playerLabel.setText(player);
		winsLabel.setText(wins);
		lossesLabel.setText(losses);
		winRateLabel.setText(winRate);
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
		
		String [][] radiant = scrape.radiantArray(match_url);
		String [][] dire = scrape.direArray(match_url);		
		
		heroColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("hero"));
		playerColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("player"));
		killsColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("kills"));
		deathsColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("deaths"));
		assistsColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("assists"));
		netWorthColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("netWorth"));
		lastHitsColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("lastHits"));
		deniesColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("denies"));
		gpmColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("GPM"));
		xpmColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("XPM"));
		
		ObservableList<Radiant> radiantData = FXCollections.observableArrayList();
		String hero, player, kills, deaths, assists, netWorth, lastHits, denies, gpm, xpm;
		for(int r = 0; r < 5; r++)
		{
			hero = radiant[r][0];
			player = radiant[r][1];
			kills = radiant[r][2];
			deaths = radiant[r][3];
			assists = radiant[r][4];
			netWorth = radiant[r][5];
			lastHits = radiant[r][6];
			denies = radiant[r][7];
			gpm = radiant[r][8];
			xpm = radiant[r][9];
			
			radiantData.add(new Radiant(hero,player,kills,deaths,assists,netWorth,lastHits,denies, gpm, xpm));
		}
		
		radiantTable.setItems(radiantData);
		
		
		direHeroColumn.setCellValueFactory(new PropertyValueFactory<Dire, String>("hero"));
		direPlayerColumn.setCellValueFactory(new PropertyValueFactory<Dire, String>("player"));
		direKillsColumn.setCellValueFactory(new PropertyValueFactory<Dire, String>("kills"));
		direDeathsColumn.setCellValueFactory(new PropertyValueFactory<Dire, String>("deaths"));
		direAssistsColumn.setCellValueFactory(new PropertyValueFactory<Dire, String>("assists"));
		direNetWorthColumn.setCellValueFactory(new PropertyValueFactory<Dire, String>("netWorth"));
		direLastHitsColumn.setCellValueFactory(new PropertyValueFactory<Dire, String>("lastHits"));
		direDeniesColumn.setCellValueFactory(new PropertyValueFactory<Dire, String>("denies"));
		dire_gpmColumn.setCellValueFactory(new PropertyValueFactory<Dire, String>("GPM"));
		dire_xpmColumn.setCellValueFactory(new PropertyValueFactory<Dire, String>("XPM"));
		
		ObservableList<Dire> direData = FXCollections.observableArrayList();
		String direHero, direPlayer, direKills, direDeaths, direAssists, direNetWorth, direLastHits, direDenies, dire_gpm, dire_xpm;
		for(int r = 0; r < 5; r++)
		{
			direHero = dire[r][0];
			direPlayer = dire[r][1];
			direKills = dire[r][2];
			direDeaths = dire[r][3];
			direAssists = dire[r][4];
			direNetWorth = dire[r][5];
			direLastHits = dire[r][6];
			direDenies = dire[r][7];
			dire_gpm = dire[r][8];
			dire_xpm = dire[r][9];
			
			direData.add(new Dire(direHero,direPlayer,direKills,direDeaths,direAssists,direNetWorth,direLastHits,direDenies, dire_gpm, dire_xpm));
		}
		
		direTable.setItems(direData);
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
		saveSuccessfulLabel.setText("Save successful");
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
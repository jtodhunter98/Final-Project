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

/**
 * The Controller class is used to define what each button and text box will do
 * within the GUI. This class is also where all of the GUI's objects are created, 
 * such as the "Display Info" button, the "Select Match" button. Everything that shows up
 * in the GUI is created in the Controller class.
 * @author todjord
 *
 */
public class Controller
{
	@FXML
    private TextField idField;	
	
	@FXML
    private Label playerLabel;
	
	@FXML
    private Label winsLabel;

    @FXML
    private Label lossesLabel;

    @FXML
    private Label winRateLabel;
    
    @FXML
    private Label rankLabel;
    
    @FXML
    private Label playedHeroesLabel;

    @FXML
    private Label firstHeroLabel;

    @FXML
    private Label secondHeroLabel;

    @FXML
    private Label thirdHeroLabel;
	
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
	@FXML private TableColumn<Radiant, String> radiantHeroColumn;
	@FXML private TableColumn<Radiant, String> radiantPlayerColumn;
	@FXML private TableColumn<Radiant, String> radiantKillsColumn;
	@FXML private TableColumn<Radiant, String> radiantDeathsColumn;
	@FXML private TableColumn<Radiant, String> radiantAssistsColumn;
	@FXML private TableColumn<Radiant, String> radiantNetWorthColumn;
	@FXML private TableColumn<Radiant, String> radiantLastHitsColumn;
	@FXML private TableColumn<Radiant, String> radiantDeniesColumn;
	@FXML private TableColumn<Radiant, String> radiant_gpmColumn;
	@FXML private TableColumn<Radiant, String> radiant_xpmColumn;
	
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
	
	/**
	 * The enterID() method is used to get the URL from the user which will be used to display
	 * the the profile that corresponds with whatever ID the user has entered.
	 * @return Returns the URL of the player's dotabuff.com profile. This URL will be used
	 * in the selectMatch() method. It will be used in the "site" object, which will be passed
	 * to the collectMatches() method within the Scrape class.
	 * @throws IOException in case the URL that the program needs is not found. This will be
	 * thrown if the user does not enter a valid player ID into the first text field.
	 */
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
		String rank = scrape.playerRank(player_id);
		String firstHero = scrape.firstHero(player_id);
		String secondHero = scrape.secondHero(player_id);
		String thirdHero = scrape.thirdHero(player_id);
		String matchListHeader = scrape.listHeader(site);
		ArrayList<String> displayList = scrape.displayMatches(site);
		playerLabel.setText(player);
		winsLabel.setText(wins);
		lossesLabel.setText(losses);
		winRateLabel.setText(winRate);
		rankLabel.setText(rank);
		playedHeroesLabel.setText("Most Played Heroes:");
		firstHeroLabel.setText(firstHero);
		secondHeroLabel.setText(secondHero);
		thirdHeroLabel.setText(thirdHero);
		displayUser.setText(matchListHeader);
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
	
	/**
	 * The selectMatch() method will be used to analyze one of the matches that has been
	 * displayed within the GUI. It will accept a user's number input from 1-10, and display
	 * the match information in the table on the right side of the GUI.
	 * @return Returns the match URL, which will point to the specific match that the user has selected.
	 * This will be used in the getMatchArray() method, so it can later be saved to the database.
	 * @throws IOException This exception will trigger if the user does not enter a number between 1-10 before
	 * they press the "Select Match" button.
	 * @throws SQLException
	 */
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
		
		radiantHeroColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("hero"));
		radiantPlayerColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("player"));
		radiantKillsColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("kills"));
		radiantDeathsColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("deaths"));
		radiantAssistsColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("assists"));
		radiantNetWorthColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("netWorth"));
		radiantLastHitsColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("lastHits"));
		radiantDeniesColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("denies"));
		radiant_gpmColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("GPM"));
		radiant_xpmColumn.setCellValueFactory(new PropertyValueFactory<Radiant, String>("XPM"));
		
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
	
	/**
	 * The saveMatch() method will accept a table name from the user that will be the
	 * title of the table that is stored to the database.
	 * @throws IOException Will most likely occur if the user does not enter a title.
	 * @throws SQLException
	 */
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
	
	/**
	 * The getMatchArray() method exists only for the saveMatch() method, so it can provide a
	 * parameter for the create(String, String[][]) method, which is located within the Save class.
	 * @return Returns both of the teams' info as one multidimensional array.
	 * @throws IOException
	 * @throws SQLException
	 */
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
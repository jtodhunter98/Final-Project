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
	
	
	//triggers when the user pastes an ID into the first text field
	@FXML
	public ArrayList<Long> enterID() throws IOException
	{
		String id = idField.getText();
		String url = "https://www.dotabuff.com/players/"+id+"/matches";
		Document site = Jsoup.connect(url).get();
		Scrape info = new Scrape();
		String displayPlayer = info.playerName(site);
		ArrayList<String> displayList = info.displayMatches(site);
		ArrayList<Long> matchList = info.collectMatches(site);
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
		return matchList;
	}	
	
	
	//triggers when the user enters the number of a match to select
	@FXML
	public String selectMatch() throws IOException
	{
		ArrayList<Long> matchList = enterID();
		int input = Integer.parseInt(matchField.getText()) - 1;
		long selection = matchList.get(input);		
		String matchID = Long.toString(selection);
		String url = "https://www.dotabuff.com/matches/"+matchID;
		return url;
	}
	
	public String[][] getMatchArray() throws IOException
	{
		Scrape info = new Scrape();
		String [][] matchArray = new String [10][10];
		ArrayList<Long> matchList = enterID();
		String matchURL = selectMatch();
		String [][] radiant = info.radiantArray(matchList, matchURL);
		String [][] dire = info.direArray(matchList, matchURL);
		
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
	
	@FXML
	public void saveMatch() throws IOException, SQLException 
	{		
		String [][] matchArray = getMatchArray();		
		Save database = new Save();
		String title = titleField.getText();
		database.create(title, matchArray);
		System.out.println(title);
	}
	
}
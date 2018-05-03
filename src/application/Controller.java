package application;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
	public void enterID() throws IOException
	{
		String id = idField.getText();
		String url = "https://www.dotabuff.com/players/"+id+"/matches";
		Document site = Jsoup.connect(url).get();
		Scrape info = new Scrape();
		
		String displayPlayer = info.playerName(site);
		ArrayList<String> displayList = info.displayMatches(site);		
		
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
	}

	public void selectMatch()
	{
		
	}
}
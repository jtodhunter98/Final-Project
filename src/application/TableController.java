package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableController implements Initializable
{
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
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
		
		try {
			matchTable.setItems(getMatchData(null));
		} catch (IOException e) {
			// 
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ObservableList<MatchData> getMatchData(String[][] matchArray) throws IOException, SQLException
	{
		Controller info = new Controller();
		matchArray = null;
		ObservableList<MatchData> data = FXCollections.observableArrayList();
		for(int r = 0; r < 10; r++)
		{
			data.add(new MatchData(matchArray[r][0], matchArray[r][1],matchArray[r][2],matchArray[r][3],matchArray[r][4],matchArray[r][5],matchArray[r][6],matchArray[r][7],matchArray[r][8],matchArray[r][9]));
		}
		return data;
	}
	
}

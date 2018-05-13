package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * The Main class is where the GUI window size and title is set.
 * Then the window will launch as soon as the program is started.
 * @author todjord
 *
 */
public class Main extends Application 
{
	/**
	 * The start method overrides the Main class so it starts the GUI application once the Main class
	 * is launched.
	 * @param primaryStage is used to define properties if the application window.
	 */
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			//set up window
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root,1500,900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Dota Stats");
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{		
		launch(args);//display window		
	}
	
}

package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Save 
{	
	public void create(String title, String[][] matchTable) throws SQLException, IOException
	{
		String DB_URL = "jdbc:mysql://db4free.net:3306/match_data";
		String USERNAME = "todjord";
		String PASSWORD = "Password01";
		Connection MyConn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
		Statement stmt = MyConn.createStatement();
		
		//Create new table		
        String create = "CREATE TABLE " + title + " " +
        				" (Num INTEGER not NULL, " +
                     	" Hero VARCHAR(255), " +
                     	" Player VARCHAR(255), " +
                     	" K VARCHAR(255), " +
                     	" D VARCHAR(255), " +
                     	" A VARCHAR(255), " +
                     	" NET VARCHAR(255), " +
                     	" LH VARCHAR(255), " +
                     	" DN VARCHAR(255), " +
                     	" GPM VARCHAR(255), " +
                     	" XPM VARCHAR(255)," +
                     	" PRIMARY KEY ( Num ))";
        stmt.executeUpdate(create);
        System.out.println("Created table in given database");
        
        //Add data to the table
        int row = 0;
        for(int r = 0; r < 12; r++)
        {
        	if(r == 0)
        	{
        		String insert = "INSERT INTO " + title +  " VALUES('"+r+"','RADIANT','-','-','-','-','-','-','-','-','-')";
        		stmt.executeUpdate(insert);
        	}
        	if(r == 6)
        	{
        		String insert = "INSERT INTO " + title +  " VALUES('"+r+"','DIRE','-','-','-','-','-','-','-','-','-')";
        		stmt.executeUpdate(insert);
        	}
        	if(r > 0)
        	{
        		if(r != 6)
        		{
            		String Hero = matchTable[row][0];
                	String Player = matchTable[row][1];
                	String K = matchTable[row][2];
                	String D = matchTable[row][3];
                	String A = matchTable[row][4];
                	String NET = matchTable[row][5];
                	String LH = matchTable[row][6];
                	String DN = matchTable[row][7];
                	String GPM = matchTable[row][8];
                	String XPM = matchTable[row][9];
                	
                	String insert = "INSERT INTO "+title+" VALUES('"+r+"','"+Hero+"','"+Player+"','"+K+"','"+D+"','"+A+"','"+NET+"','"+LH+"','"+DN+"','"+GPM+"','"+XPM+"' )";
                	stmt.executeUpdate(insert);
                	row++;
        		}        		
        	}
        }//end of data loop
	}

}

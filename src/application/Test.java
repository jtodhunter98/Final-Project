package application;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test 
{

	public static void main(String[] args) throws IOException 
	{
		String input = "foasdv iadj iod jfsoi tjds jio esports=stsda s ta tsa";
		String regex = " esports=";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		while(matcher.find())
		{
			System.out.print("found");
		}
		if(matcher.matches())
		{
			System.out.print("match");
		}
	}

}

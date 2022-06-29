package smart_serv;

import java.io.IOException;
import java.net.*;
import java.util.*;



import smart_serv.utility;
//this comment is for better understanding of git command.

public class task1 {
	
	public static void main(String[] args) throws IOException
	{
		
	final String url="https://s3.amazonaws.com/open-to-cors/assignment.json";
	
	URL link=utility.createUrl(url);
	
	String json=utility.makehttp_request(link);
	
	ArrayList<product> data=new ArrayList<>();
	data=utility.extractFromJSON(json);
	Iterator<product> i=data.iterator();
	while(i.hasNext())
	System.out.print(i.next());
	
	
		
	}

}

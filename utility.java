package smart_serv;
import java.io.*;
import java.net.*;
import java.util.*;
import org.json.*;



public class utility {
	
	 public static URL createUrl(String url) {
	        URL myURL = null;
	        try {
	            myURL = new URL(url);
	        } catch (MalformedURLException e) {
	           System.out.println("error in url creation");
	        }
	        return myURL;
	    }
	 
	 
	 
	 public static String makehttp_request(URL url) throws IOException {
		 
		 String JSONresponse = " ";

	        if (url == null) {
	            return JSONresponse;
	        }
	        HttpURLConnection urlConnection = null;
	        InputStream inputStream = null;

	        try {
	            urlConnection = (HttpURLConnection) url.openConnection();
	            urlConnection.setRequestMethod("GET");
	            urlConnection.setReadTimeout(10000);
	            urlConnection.setConnectTimeout(15000);
	            urlConnection.connect();

	            if (urlConnection.getResponseCode() == 200) {
	                inputStream = urlConnection.getInputStream();
	                JSONresponse = readFromStream(inputStream);
	            } else {
	            	System.out.println("error response code  " + urlConnection.getResponseCode());
	   
	            }

	        } catch (IOException e) {
	        	System.out.println("Problem in retriving json result"+ e);
	        } finally {
	            if (urlConnection != null) {
	                urlConnection.disconnect();
	            }
	            if (inputStream != null) {
	                // Closing the input stream could throw an IOException, which is why
	                // the makeHttpRequest(URL url) method signature specifies than an IOException
	                // could be thrown.
	                inputStream.close();
	            }


	        }
	        return JSONresponse;
	    }
	 
	  public static String readFromStream(InputStream inputStream) {
	        StringBuilder stringBuilder = new StringBuilder();
	        if (inputStream != null) {
	            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	            String line;

	            try {
	                while ((line = bufferedReader.readLine()) != null)
	                    stringBuilder.append(line);
	            } catch (IOException e) {
	            	System.out.println("error in retriving info from inputStream "+ e);
	               
	            }
	        }

	        return stringBuilder.toString();
	    }

	    public static ArrayList<product> extractFromJSON(String JSONresponse)
	    {


	        // If the JSON string is empty or null, then return early.
	        if (JSONresponse.isEmpty()) {
	            return null;
	        }


	        ArrayList<product> product=new ArrayList<>();
	        try {
	            JSONObject root=new JSONObject(JSONresponse);
	            JSONObject products=root.getJSONObject("products");
	            
	            Iterator<String> i=products.keys();
	            while(i.hasNext())
	             {
	            	String key=i.next();
	                JSONObject details = products.getJSONObject(key);
	               
	                
	                String sub=details.getString("subcategory");
	                String title=details.getString("title");
	                String price=details.getString("price");
	                String popularity=details.getString("popularity");
	                
	                product.add(new product(sub,title,price,popularity));
	            }
	        } catch (JSONException e) {
	        	
	        	System.out.println("Problem parsing the earthquake JSON results"+ e);
	        }
	        return product;
	    }

}

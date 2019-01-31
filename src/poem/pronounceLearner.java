package poem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;

public class pronounceLearner implements Runnable {

	private int start,finish;
	public pronounceLearner(int s, int f) {
		// TODO Auto-generated constructor stub
		start=s;
		finish=f;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		readAndLearn(start, finish);
	}
	void readAndLearn(int start,int finish){
		// TODO Auto-generated method stub
		
		CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
		HttpURLConnection.setFollowRedirects(true);
		PronounceFinder pronounceFinder = new PronounceFinder();
		
		URL url;
		HttpURLConnection httpConnection;
		HttpURLConnection.setFollowRedirects(true);
		for (int i=start ; i<=finish ; i++)
		{
			
			
			System.out.println("<<<<****$$$ Now I'm on <"+ i +">... $$$****>>>>");
//				for(int i=1; i<=poemLength[n];i++)
//				{
				try {
					url = new URL("http://www.khabaronline.ir/news/"+i);
					
					httpConnection = (HttpURLConnection) url.openConnection();
					
					if(httpConnection.getResponseCode() != HttpURLConnection.HTTP_OK){
						String newUrl = httpConnection.getHeaderField("Location");
						httpConnection = (HttpURLConnection) new URL(newUrl).openConnection();
					}
					
					System.out.println(httpConnection.getResponseCode());
					BufferedReader in = new BufferedReader(
					        new InputStreamReader( httpConnection.getInputStream() ));
					
					        String inputLine;
//						        while (! ( ( inputLine = in.readLine() ) .equals("") ) )
//						        {	
//						        	System.out.println(inputLine);
//						        }
					        inputLine = in.readLine();
//						        System.out.println(inputLine);
					        String [] words;
					        while (! ( (inputLine = in.readLine()) == null) ) //.equals("") )
					        {
//						        	System.out.println(inputLine);
					        	words=inputLine.split("[^آ-ی]");
					        	for (String word : words) {
									if(!word.equals(""))
									{
										pronounceFinder.findPronounce(word);
									}
								}
//						        	
					        }
					        
				}catch(FileNotFoundException e) {
					
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					i--;
				}
//				}
			System.out.println("wordsRhyme hit: "+pronounceFinder.getWordsrhymeHit());
			System.out.println("forbiddens hit: "+pronounceFinder.getForbiddenwordsHit());
			pronounceFinder.clearForbiddenwordsHit();
			pronounceFinder.clearWordsrhymeHit();
			System.out.println("getting ready for next...");
			pronounceFinder.cleanWordsRhyme();
		}
	
	}
}
